import java.util.Arrays;
import java.util.Scanner;

public class Recherche  extends Game {

    static Scanner entree = new Scanner(System.in);

    @Override
    public void challengerMode() {
        super.challengerMode();
        System.out.println("Bienvenue sur le jeu : Recherche +/-");
        System.out.println("Tu dois trouver la combinaison secrète. A chaque proposition des indices te seront donnés");
        System.out.println("les indices sont : '+' '-' et '=' ");
        System.out.println("Tu as le droit à " + getNbMaxEssais() + " essais pour y arriver !");
        System.out.println("Pour t'aider sache que la combinaison secrète est à " + getNbChiffreCpu() + " chiffres.");

        System.out.println("Entrez votre proposition : "); //Pense à catcher une exception afin que nbReponse ne soit pas inferieur ou superieur nbChiffre
        String nbReponse = entree.next();
        String[] tabReponse = nbReponse.split("(?<=.)");

        while (tabReponse.length != getCpuTabMystere().length) {
            System.out.println("Votre réponse ne fait pas la meme taille que la combinaison secrète !!");
            System.out.println("Veuillez rentrer une reponse qui fait la taille de " + nbChiffreCpu + " chiffre(s)");
            nbReponse = entree.next();
            tabReponse = nbReponse.split("(?<=.)");
        }

        while (!Arrays.equals(tabReponse, getCpuTabMystere()) && getNombreEssais() < getNbMaxEssais()) {
            String[] compare = new String[getCpuTabMystere().length];
            for (int i = 0; i < getCpuTabMystere().length; i++) {
                int valeur = getCpuTabMystere()[i].compareTo(tabReponse[i]);
                if (valeur == 0) {
                    compare[i] = "=";
                } else if (valeur > 0) {
                    compare[i] = "+";
                } else if (valeur < 0) {
                    compare[i] = "-";
                }
            }
            essaiRestant = essaiRestant - 1;
            System.out.println("Vous avez proposé : " + nbReponse + " -> Voici des indices pour vous aider : " + Arrays.toString(compare));
            System.out.println("Il te reste " + essaiRestant + " essais !");
            System.out.println("Entrez votre nouvelle proposition : ");
            nbReponse = entree.next();
            tabReponse = nbReponse.split("(?<=.)");
            while (tabReponse.length != getCpuTabMystere().length) {
                System.out.println("Votre réponse ne fait pas la meme taille que la combinaison secrète !!");
                System.out.println("Veuillez rentrer une reponse qui fait la taille de " + nbChiffreCpu);
                nbReponse = entree.next();
                tabReponse = nbReponse.split("(?<=.)");
            }
            nombreEssais = nombreEssais + 1;
        }
        if (nombreEssais >= getNbMaxEssais() && !Arrays.equals(tabReponse, getCpuTabMystere())) {
            String[] compare = new String[getCpuTabMystere().length];
            for (int i = 0; i < getCpuTabMystere().length; i++) {
                int valeur = getCpuTabMystere()[i].compareTo(tabReponse[i]);
                if (valeur == 0) {
                    compare[i] = "=";
                } else if (valeur > 0) {
                    compare[i] = "+";
                } else if (valeur < 0) {
                    compare[i] = "-";
                }
            }
            System.out.println("Vous avez proposé : " + nbReponse + " -> Voici des indices pour vous aider : " + Arrays.toString(compare));
            System.out.println("Mais tu as dépassé le nombre d'essais autorisé qui était de " + getNbMaxEssais() + "  essai(s)");
            System.out.println("Voici la solution : " + getCpuNbMystere());
        } else {
            System.out.println("Félicitations !!! Tu as reussi avec " + nombreEssais + " essai(s)");
        }
    }


    @Override
    public void defenseMode() {
        super.defenseMode();
        String nbReponse;
        System.out.println("Bienvenue sur le jeu : Recherche +/-");
        System.out.println("L'ordinateur doit trouver ta combinaison secrète.");
        System.out.println("Il a le droit à " + getNbMaxEssais() + " essais pour y arriver !");
        System.out.println("Ta combinaison secrète est à " + getNbChiffreJoueur() + " chiffres.");

        System.out.println("L'ordinateur entre sa proposition : ");
        for (int i = 0; i < getNbChiffreJoueur(); i++) {
            getPremierePropositionTab()[i] = String.valueOf(5);
            premiereProposition = getPremiereProposition() + getPremierePropositionTab()[i];
        }
        nbReponse = premiereProposition;
        String[] tabReponse = getPremiereProposition().split("(?<=.)");
        while (!Arrays.equals(tabReponse, getJoueurTabMystere()) && getNombreEssais() < getNbMaxEssais()) {
            String[] compare = new String[getJoueurTabMystere().length];
            for (int i = 0; i < getJoueurTabMystere().length; i++) {
                int valeur = getJoueurTabMystere()[i].compareTo(tabReponse[i]);
                if (valeur == 0) {
                    compare[i] = "=";
                } else if (valeur > 0) {
                    compare[i] = "+";
                } else if (valeur < 0) {
                    compare[i] = "-";
                }
            }
            essaiRestant = essaiRestant - 1;
            nombreEssais = nombreEssais + 1;
            System.out.println("Vous avez proposé : " + nbReponse + " -> Voici des indices pour vous aider : " + Arrays.toString(compare));
            System.out.println("Il te reste " + essaiRestant + " essais !");
            System.out.println("Entrez votre nouvelle proposition : ");
        }

    }

        @Override
        protected void duelMode() {
            super.duelMode();
        }
}



