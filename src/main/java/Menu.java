import java.util.Scanner;

public class Menu extends Game {
    Scanner entree = new Scanner(System.in);
    int choix;

    public void firstMenu() {
        System.out.println("Bienvenu dans notre petit espace de jeu !");
        System.out.println(" ");
        System.out.println("Ici, tu as le choix entre deux jeux");
        System.out.println(" ");
        System.out.println(" 1 - Le recherche +/-");
        System.out.println(" 2 - Le mastermind");
        System.out.println(" ");
        System.out.println("Quel est ton choix ? 1 ou 2 ");
        System.out.println("Pour quitter le jeu immediatement entre 3");
        choix = entree.nextInt();
        if (choix == 1) {
            rechercheMenu();
        }else if (choix ==2) {
            mastermindMenu();
        }
    }

    public void rechercheMenu () {
        System.out.println( " Tu as choisi le jeu recherche +/-");
        System.out.println(" ");
        System.out.println("Tu peux choisir trois mode de jeux !!!");
        System.out.println(" ");
        System.out.println(" 1 - le mode Challerger");
        System.out.println(" 2 - le mode Defense");
        System.out.println(" 3 - le mode Duel");
        System.out.println(" 4 - Le mode developpeur");
        System.out.println(" ");
        System.out.println("Quel est ton choix ? 1 , 2 , 3 ou 4");
        System.out.println("Pour revenir au menu précedent entre le 5 ");
        if (choix == 1) {
            challengerMode();
        }else if (choix == 2) {
            defenseMode();
        }else if ( choix == 3) {
            duelMode();
        }else if (choix == 4) {
            developperMode();
        }else if (choix == 5) {
            firstMenu();
        }
    }

    public void mastermindMenu () {
        System.out.println( " Tu as choisi le jeu MasterMind");
        System.out.println(" ");
        System.out.println("Tu peux choisir trois mode de jeux !!!");
        System.out.println(" ");
        System.out.println(" 1 - le mode Challerger");
        System.out.println(" 2 - le mode Defense");
        System.out.println(" 3 - le mode Duel");
        System.out.println(" 4 - Le mode developpeur");
        System.out.println(" ");
        System.out.println("Quel est ton choix ? 1 , 2 , 3 ou 4");
        System.out.println("Pour revenir au menu précedent entre le 5 ");
        if (choix == 1) {
            challengerMode();
        }else if (choix == 2) {
            defenseMode();
        }else if ( choix == 3) {
            duelMode();
        }else if (choix == 4) {
            developperMode();
        }else if (choix == 5) {
            firstMenu();
        }
    }

}

