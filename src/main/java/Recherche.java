import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Arrays;

public class Recherche  {

    static Scanner entree = new Scanner(System.in);

    public static void main(String[] args) {
        /**
         * Saisi du nombre maximum d'essai
         */
        int nbMaxEssais;
        boolean sortir;
        do {
            nbMaxEssais = 0;
            System.out.println("Combien d'essais sont autorisés ? "); //Pense a faire en sorte que seuls les chiffres soient acceptés
            try {
                sortir = true;
                nbMaxEssais = entree.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Vous ne devez mettre que des chiffres");
                entree.next();
                sortir = false;
            }
        }while (!sortir);


        /**
         * Saisi du chiffre mystere et passage en format tableau
         */

        System.out.println("Entrez votre combinaison secrète : "); //Faire une limitation de l'intervalle
        String nbMystere = entree.next();
        String[] tabMystere = nbMystere.split("(?<=.)");

        /**
         * Décompte de nbChiffre pour donner un indice au joueur
         */
        int nbChiffre = 0;
        for (int i = 0; i< tabMystere.length; i++){
            nbChiffre = nbChiffre+1;
        }
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Bienvenue sur le jeu : Recherche +/-");
        System.out.println("Tu dois trouver la combinaison secrète. A chaque proposition des indices te seront donnés");
        System.out.println("les indices sont : '+' '-' et '=' ");
        System.out.println("Tu as le droit à " +nbMaxEssais+" essais pour y arriver !");
        System.out.println("Pour t'aider sache que la combinaison secrète est à "+nbChiffre+" chiffres.");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");


        /**
         * Intialisation de la variable nbReponse
         */
        System.out.println("Entrez votre proposition : "); //Pense à catcher une exception afin que nbReponse ne soit pas inferieur ou superieur nbChiffre
        String nbReponse = entree.next();
        String[] tabReponse = nbReponse.split("(?<=.)");

        /**
         * Intialisation de la variable nombreEssais à 1 puisque le joueur démarre directement avec son premier essai
         */
        int nombreEssais = 1;

        /**
         * Initialisation de la variable essaiRestant
         */
        int essaiRestant = nbMaxEssais;

        /**
         * Boucle permettant d'indiquer si c'est plus ou moins ou égal en comparant l'égalité de tabReponse et tabMystere tant que nombreEssais est plus petit que nbMaxEssais
         * Décrémentation de essaiRestant
         * Incrémentation de nombreEssais
         * */

        while (!Arrays.equals(tabReponse, tabMystere) && nombreEssais < nbMaxEssais) {
            String[] compare = new String[tabMystere.length];
            for (int i = 0; i < tabMystere.length; i++) {
                int valeur = tabMystere[i].compareTo(tabReponse[i]);
                if (valeur == 0) {
                    compare[i] = "=";
                } else if (valeur > 0) {
                    compare[i] = "+";
                } else if (valeur < 0) {
                    compare[i] = "-";
                }
            }
            essaiRestant = essaiRestant-1;
            System.out.println("Vous avez proposé : " + nbReponse + " -> Voici des indices pour vous aider : " + Arrays.toString(compare));
            System.out.println("Il te reste "+essaiRestant+ " essais !");
            nbReponse = entree.next(); //attention pense à faire vérifier que le nombre entrée est exactement la longueur que nbMystere
            tabReponse = nbReponse.split("(?<=.)");
            nombreEssais = nombreEssais + 1;

        }
        /**
         * Si nombreEssais est plus grand ou égal à nbMaxEssais et que tabReponse et tabMystere ne sont pas égaux fin de partie
         * Sinon afficher Félicitations
         */
        if (nombreEssais >= nbMaxEssais && !Arrays.equals(tabReponse, tabMystere)) {
            String[] compare = new String[tabMystere.length];
            for (int i = 0; i < tabMystere.length; i++) {
                int valeur = tabMystere[i].compareTo(tabReponse[i]);
                if (valeur == 0) {
                    compare[i] = "=";
                } else if (valeur > 0) {
                    compare[i] = "+";
                } else if (valeur < 0) {
                    compare[i] = "-";
                }
            }
            System.out.println("Vous avez proposé : " + nbReponse + " -> Voici des indices pour vous aider : " + Arrays.toString(compare));
            System.out.println("Tu as dépassé le nombre d'essais autorisé qui était de " + nbMaxEssais+ "  essai(s)");
            System.out.println("Voici la solution : "+ tabReponse);
        }else{
            System.out.println("Félicitations !!! Tu as reussi avec " + nombreEssais + " essai(s)");
        }

    }
}


