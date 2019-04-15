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
        Recherche recherche = new Recherche();
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
        choix = entree.nextInt();
        if (choix == 1) {
            recherche.challengerMode();
        }else if (choix == 2) {
            recherche.defenseMode();
        }else if ( choix == 3) {
            recherche.duelMode();
        }else if (choix == 4) {
            recherche.developperMode();
        }else if (choix == 5) {
            firstMenu();
        }
    }

    public void mastermindMenu () {
        Mastermind mastermind = new Mastermind();
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
        choix = entree.nextInt();
        if (choix == 1) {
            mastermind.challengerMode();
        }else if (choix == 2) {
            mastermind.defenseMode();
        }else if ( choix == 3) {
            mastermind.duelMode();
        }else if (choix == 4) {
            mastermind.developperMode();
        }else if (choix == 5) {
            firstMenu();
        }
    }

}

