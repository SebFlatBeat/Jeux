import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Arrays;

public class Recherche  {

    static Scanner entree = new Scanner(System.in);

    public static void main(String[] args) {
        /**
         * Saisi du nombre maximum d'essai en limitant le choix et en ajoutant une exception
         */
        int nbMaxEssais;
        boolean sortir;
        do {
            nbMaxEssais = 0;
            System.out.println("Combien d'essais sont autorisés ? Choississez un nombre entre 3 et 20 ");
            try {
                sortir = true;
                nbMaxEssais = entree.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Vous ne devez mettre que des chiffres");
                entree.next();
                sortir = false;
            }
            if (nbMaxEssais<3 || nbMaxEssais>20 && sortir) {
                System.out.println("Vous devez choisir un nombre entre 3 et 20 !!!!");
                sortir = false;
            }
        }while (!sortir);


        /**
         * Saisi du chiffre mystere et passage en format tableau
         */
        String nbMystere;
        String[] tabMystere;
        int nbChiffre = 0;
        do {
           System.out.println("Vous pouvez entrer une combinaision de 1 à 10 chiffres");
           System.out.println("Entrez votre combinaison secrète : "); //Faire une limitation de l'intervalle
               nbMystere = entree.next();
               while (nbMystere.matches("^[a-zA-Z]*$")) {
                   System.out.println("Vous ne devez mettre que des chiffres");
                   System.out.println("Vous pouvez entrer une combinaision de 1 à 10 chiffres");
                   nbMystere = entree.next();
                   }
           tabMystere = nbMystere.split("(?<=.)");
            /**
             * Décompte de nbChiffre pour donner un indice au joueur
             */
            for (int i = 0; i< tabMystere.length; i++){
                nbChiffre = nbChiffre+1;
            }
            if (nbChiffre<1 || nbChiffre>10) {
                System.out.println("Vous devez entrer une combinaision de 1 à 10 chiffres");
                sortir = false;
            }else{
                sortir = true;
            }
       }while (!sortir);




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
         * Comparaison de la taille des deux tableaux tabReponse et tabMystere
         */
        System.out.println("Entrez votre proposition : "); //Pense à catcher une exception afin que nbReponse ne soit pas inferieur ou superieur nbChiffre
        String nbReponse = entree.next();
        String[] tabReponse = nbReponse.split("(?<=.)");
        while (tabReponse.length != tabMystere.length){
            System.out.println("Votre réponse ne fait pas la meme taille que la combinaison secrète !!");
            System.out.println("Veuillez rentrer une reponse qui fait la taille de "+nbChiffre+" chiffre(s)");
            nbReponse = entree.next();
            tabReponse = nbReponse.split("(?<=.)");
        }

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
            System.out.println("Entrez votre nouvelle proposition : ");
            nbReponse = entree.next();
            tabReponse = nbReponse.split("(?<=.)");
            while (tabReponse.length != tabMystere.length){
                System.out.println("Votre réponse ne fait pas la meme taille que la combinaison secrète !!");
                System.out.println("Veuillez rentrer une reponse qui fait la taille de "+nbChiffre);
                nbReponse = entree.next();
                tabReponse = nbReponse.split("(?<=.)");
            }
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


