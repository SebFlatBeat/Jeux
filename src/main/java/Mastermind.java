import java.util.Random;
import java.util.Scanner;

public class Mastermind extends Game {

    /**
     * Taille de la combinaison
     */
    final static int combinaisonMax = 6;

    /**
     * Génère de manière aléatoire une combinaison
     *
     * @param c = une combinaison
     * @param t = taille de combinaison
     * @param n = nombre
     */
    public static void generateurDeCombinaison(int[] c, int t, int n) {

        Random random = new Random();
        for (int nb = 0; nb < t; nb++) {
            c[nb] = random.nextInt(n) + 1;
        }
    }

    /**
     * Saisi de la combinaison du joueur
     *
     * @param c = une combinaison
     * @param t = taille de combinaison
     * @param n = nombre
     */
    public static void saisiCombinaison(int[] c, int t, int n) {

        Scanner sc = new Scanner(System.in);
        System.out.println(t + " chiffres dans [1.." + n + " ]?");
        for (int nb = 0; nb < t; nb++) {
            c[nb] = sc.nextInt();
        }

    }


}
