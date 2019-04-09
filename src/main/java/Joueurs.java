import java.util.Scanner;

public class Joueurs extends Game {
    static Scanner entree = new Scanner(System.in);

    public Joueurs(int numJoueur) {

        if (numJoueur == 1) {
            personum = ordinateur;
            System.out.println("Salut je suis ton adversaire l'ordinateur ");
        } else if (numJoueur == 2) {
            personum = humain;
            System.out.println("Comment t'appelles-tu ?");
            nomHumain = entree.next();
            setHumain("Joueur" + nomHumain);
        }

    }
}
