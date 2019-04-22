import java.util.Scanner;

public class Menu extends Game {
    Scanner entree = new Scanner(System.in);
    int choix;

    /**
     * DevMode
     */

    public void devMode() {
        System.out.println("Bienvenu dans notre petit espace de jeu !");
        System.out.println(" ");
        System.out.println("Avant de commencer souhaites-tu activer le mode developpeur?");
        System.out.println("1 - Oui");
        System.out.println("2 - Non");
        choix = entree.nextInt();
        if (choix == 1) {
            setDevMode(true);
            firstMenu();
        }
        if (choix == 2) {
            setDevMode(false);
            firstMenu();
        }
    }

    /**
     * Premier Menu
     */
    public void firstMenu() {

        System.out.println("Ici, tu as le choix entre deux jeux");
        System.out.println(" ");
        System.out.println(" 1 - Le Recherche +/-");
        System.out.println(" 2 - Le MasterMind");
        System.out.println(" ");
        System.out.println("Quel est ton choix ? 1 ou 2 ");
        System.out.println("Pour revenir au menu précedent entre 3");
        System.out.println("Pour quitter le jeu immediatement entre 4");
        choix = entree.nextInt();
        if (choix == 1) {
            rechercheMenu();
        }else if (choix ==2) {
            mastermindMenu();
        }else if (choix == 3) {
            devMode();
        }else if (choix == 4){
            System.out.println("A bientôt !!");
        }
    }

    /**
     * Menu Rechercher +/-
     */
    public void rechercheMenu () {
        Recherche recherche = new Recherche();
        System.out.println( " Tu as choisi le jeu recherche +/-");
        System.out.println(" ");
        System.out.println("Tu peux choisir trois mode de jeux !!!");
        System.out.println(" ");
        System.out.println(" 1 - le mode Challerger");
        System.out.println(" 2 - le mode Defense");
        System.out.println(" 3 - le mode Duel");
        System.out.println(" ");
        System.out.println("Quel est ton choix ? 1 , 2 , 3");
        System.out.println("Pour revenir au menu précedent entre le 4 ");
        choix = entree.nextInt();
        if (choix == 1) {
            recherche.challengerMode();
        }else if (choix == 2) {
            recherche.defenseMode();
        }else if ( choix == 3) {
            recherche.duelMode();
        }else if (choix == 4) {
            firstMenu();
        }
    }

    /**
     * Menu Mastermind
     */
    public void mastermindMenu () {
        Mastermind mastermind = new Mastermind();
        System.out.println( " Tu as choisi le jeu MasterMind");
        System.out.println(" ");
        System.out.println("Tu peux choisir trois mode de jeux !!!");
        System.out.println(" ");
        System.out.println(" 1 - le mode Challerger");
        System.out.println(" 2 - le mode Defense");
        System.out.println(" 3 - le mode Duel");
        System.out.println(" 4 - Souhaites-tu activer ou desativer le mode developpeur?");
        System.out.println(" ");
        System.out.println("Quel est ton choix ? 1 , 2 , 3 ");
        System.out.println("Pour revenir au menu précedent entre le 4 ");
        choix = entree.nextInt();
        if (choix == 1) {
            mastermind.challengerMode();
        }else if (choix == 2) {
            mastermind.defenseMode();
        }else if ( choix == 3) {
            mastermind.duelMode();
        }else if (choix == 4) {
            firstMenu();
        }
    }

}

