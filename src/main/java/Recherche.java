import java.util.Scanner;
import java.util.Arrays;

public class Recherche {

    static Scanner entree = new Scanner(System.in);

    public static void main(String[] args) {
        /**
         * Saisi du nombre maximum d'essai
         */
        System.out.println("Combien d'essais sont autorisés ? "); //Pense a faire en sorte que les chiffres soient acceptés
        int nbMaxEssais = entree.nextInt();

        /**
         * Saisi du chiffre mystere
         */

        System.out.println("Entrez votre combinaison secrète : ");
        int nbMystere = entree.nextInt();
        String Mystere = String.valueOf(nbMystere);
        String[] tabMystere = Mystere.split("(?<=.)");


        /**
         * intialisation de la variable nbReponse
         */
        System.out.println("Entrez votre proposition : ");
        int nbReponse = entree.nextInt();
        String Reponse = String.valueOf(nbReponse);
        String[] tabReponse = Reponse.split("(?<=.)");

        /**
         * intialisation de la variable nombreEssais à 1 puisque le joueur démarre directement avec son premier essai
         */
        int nombreEssais = 1;

        /**
         * Boucle permettant d'indiquer si c'est plus ou moins ou égal
         * */

        while (!Arrays.equals(tabReponse, tabMystere) && nombreEssais <= nbMaxEssais) {
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

            nbReponse = entree.nextInt();
            Reponse = String.valueOf(nbReponse);
            tabReponse = Reponse.split("(?<=.)");
            nombreEssais = nombreEssais + 1;

        }
        if (nombreEssais >= nbMaxEssais) {
            System.out.println("Tu as dépassé le nombre d'essais autorisé qui était de : " + nbMaxEssais);
        }else{
            System.out.println("Félicitations !!! Tu as reussi avec " + nombreEssais + " d'essai(s)");
        }

    }
}


