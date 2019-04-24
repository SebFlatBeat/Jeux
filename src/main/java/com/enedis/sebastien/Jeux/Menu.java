package com.enedis.sebastien.Jeux;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu extends Game {

    private static final Logger LOGGER = LogManager.getLogger(Menu.class.getName());
    Scanner entree = new Scanner(System.in);
    int choix;
    boolean bonChoix;

    /**
     * DevMode
     */
    public void devMode() {
        System.out.println("Bienvenu dans notre petit espace de jeu !");
        System.out.println(" ");
        LOGGER.info("Avant de commencer souhaites-tu activer le mode developpeur?");
        System.out.println("1 - Oui");
        System.out.println("2 - Non");
        System.out.println("Pour quitter le jeu immediatement entre 3");
        do {
            try {
                bonChoix = true;
                LOGGER.debug(choix = entree.nextInt());
            } catch (InputMismatchException e) {
                entree.next();
                LOGGER.error("Vous ne devez saisir que des chiffres");
                bonChoix = false;
            }
            if (choix > 3 || choix < 1) {
                LOGGER.error("Votre réponse est incorrecte");
                LOGGER.error("Veuillez à nouveau rentrer votre choix");
                bonChoix = false;
            }
        } while (!bonChoix);

        if (choix == 1) {
            setDevMode(true);
            firstMenu();
        }
        if (choix == 2) {
            setDevMode(false);
            firstMenu();
        }
        if (choix == 3) {
            System.out.println("A bientôt !");
        }
    }

    /**
     * Premier Menu
     */
    public void firstMenu() {

        System.out.println("Ici, tu as le choix entre deux jeux");
        System.out.println(" ");
        LOGGER.info(" 1 - Le Recherche +/-");
        LOGGER.info(" 2 - Le MasterMind");
        System.out.println(" ");
        System.out.println("Quel est ton choix ? 1 ou 2 ");
        LOGGER.info("Pour revenir au menu précedent, entre 3");
        LOGGER.info("Pour quitter le jeu immediatement entre 4");
        do {
            try {

                bonChoix = true;
                LOGGER.info(choix = entree.nextInt());
            } catch (InputMismatchException e) {
                entree.next();
                LOGGER.error("Vous ne devez saisir que des chiffres");
                bonChoix = false;
            }
            if (choix > 4 || choix < 1) {
                LOGGER.error("Votre réponse est incorrecte");
                LOGGER.error("Veuillez à nouveau rentrer votre choix");
                bonChoix = false;
            }
        } while (!bonChoix);
        if (choix == 1) {
            rechercheMenu();
        } else if (choix == 2) {
            mastermindMenu();
        } else if (choix == 3) {
            devMode();
        } else if (choix == 4) {
            System.out.println("A bientôt !!");
        }
    }

    /**
     * Menu Rechercher +/-
     */
    public void rechercheMenu() {
        Recherche recherche = new Recherche();
        LOGGER.info(" Tu as choisi le jeu recherche +/-");
        System.out.println(" ");
        System.out.println("Tu peux choisir trois modes de jeux !!!");
        System.out.println(" ");
        LOGGER.info(" 1 - le mode Challenger");
        LOGGER.info(" 2 - le mode Defense");
        LOGGER.info(" 3 - le mode Duel");
        System.out.println(" ");
        System.out.println("Quel est ton choix ? 1 , 2 , 3");
        System.out.println("Pour revenir au menu précedent, entre le 4 ");
        do {
            try {
                bonChoix = true;
                LOGGER.info(choix = entree.nextInt());
            } catch (InputMismatchException e) {
                entree.next();
                LOGGER.error("Vous ne devez saisir que des chiffres");
                bonChoix = false;
            }
            if (choix > 4 || choix < 1) {
                LOGGER.error("Votre réponse est incorrecte");
                LOGGER.error("Veuillez à nouveau rentrer votre choix");
                bonChoix = false;
            }
        } while (!bonChoix);
        if (choix == 1) {
            recherche.challengerMode();
        } else if (choix == 2) {
            recherche.defenseMode();
        } else if (choix == 3) {
            recherche.duelMode();
        } else if (choix == 4) {
            firstMenu();
        }
    }

    /**
     * Menu Mastermind
     */
    public void mastermindMenu() {
        Mastermind mastermind = new Mastermind();
        LOGGER.info(" Tu as choisi le jeu MasterMind");
        System.out.println(" ");
        System.out.println("Tu peux choisir trois modes de jeux !!!");
        System.out.println(" ");
        LOGGER.info(" 1 - le mode Challenger");
        LOGGER.info(" 2 - le mode Defense");
        LOGGER.info(" 3 - le mode Duel");
        System.out.println("Quel est ton choix ? 1 , 2 , 3 ");
        System.out.println("Pour revenir au menu précedent, entre le 4 ");
        do {
            try {
                bonChoix = true;
                LOGGER.info(choix = entree.nextInt());
            } catch (InputMismatchException e) {
                entree.next();
                LOGGER.error("Vous ne devez saisir que des chiffres");
                bonChoix = false;
            }
            if (choix > 4 || choix < 1) {
                LOGGER.error("Votre réponse est incorrecte");
                LOGGER.error("Veuillez à nouveau rentrer votre choix");
                bonChoix = false;
            }
        }while (!bonChoix) ;

            if (choix == 1) {
                mastermind.challengerMode();
            } else if (choix == 2) {
                mastermind.defenseMode();
            } else if (choix == 3) {
                mastermind.duelMode();
            } else if (choix == 4) {
                firstMenu();
            }
    }
}
