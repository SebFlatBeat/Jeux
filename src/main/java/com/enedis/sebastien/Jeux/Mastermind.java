package com.enedis.sebastien.Jeux;

import com.enedis.sebastien.Algo.Knuth;
import com.enedis.sebastien.Algo.KnuthAlgo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Mastermind extends Game{

    private static final Logger LOGGER = LogManager.getLogger(Mastermind.class.getName());

    static Scanner entree = new Scanner(System.in);
    boolean bonChoix;

    @Override
    public void challengerMode() {
        super.challengerMode();
        /**
         * Parametres supplementaires
         */
        int choixJoueur = 0;
        String bienPlace = "-1";
        String malPlace = "-2";
        String enleverNbPlace = "";

        System.out.println("Bienvenue sur le jeu : MasterMind");
        System.out.println("Tu as choisi le mode Challenger !");
        System.out.println("Tu dois trouver la combinaison secrète. A chaque proposition des indices te seront donnés");
        System.out.println("les indices sont : Bien placé et Mal placé ");
        System.out.println("Tu as le droit à " + getNbMaxEssais() + " essais pour y arriver !");
        System.out.println("Pour t'aider sache que la combinaison secrète est à " + getNbChiffreCpu() + " chiffres.");
        /**
         * condition si mode developpeur est activé
         */
        if (devMode = true) {
            System.out.println("Mode developpeur activé");
            System.out.println("Voici la combinaison de l'ordinateur : " +getCpuNbMystere() );
        }
        LOGGER.info("Entre ta proposition : ");
        nbReponse = entree.next();
        LOGGER.info(nbReponse);
        tabReponse = nbReponse.split("(?<=.)");

        /**
         * Boucle permettant de s'assurer que la saisie de l'utilisateur fasse la meme taille que la combinaison secrete
         */
        while (tabReponse.length != getCpuTabMystere().length) {
            LOGGER.error("Ta réponse ne fait pas la meme taille que la combinaison secrète !!");
            System.out.println("Entre une reponse qui fait la taille de " + nbChiffreCpu + " chiffre(s)");
            nbReponse = entree.next();
            LOGGER.info(nbReponse);
            tabReponse = nbReponse.split("(?<=.)");
        }
        /**
         * Boucle tant que la reponse ne correspond pas au nombre mystere et que le nombre d'essai est inferieur au nombre d'essai max
         */
        while (!Arrays.equals(tabReponse, getCpuTabMystere()) && getNombreEssais() < getNbMaxEssais()) {
            int nbBienPlace = 0;
            int nbMalPlace = 0;
            String[] copy = tabReponse;
            String copyCpuNbMystere = CpuNbMystere;
            String[] compare = copyCpuNbMystere.split("(?<=.)");

            for (int i = 0; i < getCpuTabMystere().length; i++) {
                int rechercheBienPlace = compare[i].compareTo(copy[i]);
                if (rechercheBienPlace == 0) {
                    nbBienPlace++;
                    compare[i] = bienPlace;
                    copy[i] = enleverNbPlace;
                }
            }
            for (int a = 0; a < tabReponse.length; a++) {
                for (int i = 0; i < getCpuTabMystere().length; i++) {
                    int rechercheMalPlace = compare[i].compareTo(copy[a]);
                    if (rechercheMalPlace == 0) {
                        nbMalPlace++;
                        compare[i] = malPlace;
                        break;
                    }
                }
            }
            essaiRestant = essaiRestant - 1;
            System.out.println("Tu as proposé : " + nbReponse + " -> Voici des indices pour t'aider : Tu as " + nbBienPlace + " chiffre(s) de bien placé(s) et " + nbMalPlace + " chiffre(s) de mal placé(s)");
            System.out.println("Il te reste " + essaiRestant + " essais !");
            LOGGER.info("Entre une nouvelle proposition : ");
            nbReponse = entree.next();
            LOGGER.info(nbReponse);
            tabReponse = nbReponse.split("(?<=.)");
            /**
             * Boucle permettant de s'assurer que la saisie de l'utilisateur fasse la meme taille que la combinaison secrete
             */
            while (tabReponse.length != getCpuTabMystere().length) {
                LOGGER.error("Ta réponse ne fait pas la meme taille que la combinaison secrète !!");
                System.out.println("Entre une reponse qui fait la taille de " + nbChiffreCpu + " chiffres");
                nbReponse = entree.next();
                LOGGER.info(nbReponse);
                tabReponse = nbReponse.split("(?<=.)");
            }
            nombreEssais = nombreEssais + 1;
        }
        /**
         * Condition pour le dernier essai et la victoire
         */
        if (nombreEssais >= getNbMaxEssais() && !Arrays.equals(tabReponse, getCpuTabMystere())) {
            int nbBienPlace = 0;
            int nbMalPlace = 0;
            String[] copy = tabReponse;
            String copyCpuNbMystere = CpuNbMystere;
            String[] compare = copyCpuNbMystere.split("(?<=.)");

            for (int i = 0; i < getCpuTabMystere().length; i++) {
                int rechercheBienPlace = compare[i].compareTo(copy[i]);
                if (rechercheBienPlace == 0) {
                    nbBienPlace++;
                    compare[i] = bienPlace;
                    copy[i] = enleverNbPlace;
                }
            }
            for (int a = 0; a < tabReponse.length; a++) {
                for (int i = 0; i < getCpuTabMystere().length; i++) {
                    int rechercheMalPlace = compare[i].compareTo(copy[a]);
                    if (rechercheMalPlace == 0) {
                        nbMalPlace++;
                        compare[i] = malPlace;
                        break;
                    }
                }
            }
            System.out.println("Tu as proposé : " + nbReponse + " -> Voici des indices pour t'aider : Tu as " + nbBienPlace + " chiffre(s) de bien placé(s) et " + nbMalPlace + " chiffre(s) de mal placé(s)");
            System.out.println("Seulement tu as dépassé le nombre d'essais autorisé qui était de " + getNbMaxEssais() + "  essai(s)");
            System.out.println("Voici la solution : " + getCpuNbMystere());
        } else {
            System.out.println("Félicitations !!! Tu as reussi avec " + nombreEssais + " essai(s)");
        }
        /**
         * Propostion de rejoueur ou de revenir à d'autres menus
         */
        LOGGER.info("Souhaites-tu rejouer une partie en Mode challenger ?");
        System.out.println("1 - Oui");
        System.out.println("2 - Non");
        do {
            try {
                bonChoix = true;
                LOGGER.info(choixJoueur = entree.nextInt());
            } catch (InputMismatchException e) {
                entree.next();
                LOGGER.error("Vous ne devez saisir que des chiffres");
                bonChoix = false;
            }
            if (choixJoueur > 2 || choixJoueur < 1) {
                LOGGER.error("Votre réponse est incorrecte");
                LOGGER.error("Veuillez à nouveau rentrer votre choix");
                bonChoix = false;
            }
        } while (!bonChoix);
        if (choixJoueur == 1) {
            Mastermind mastermind = new Mastermind();
            mastermind.challengerMode();
        } else if (choixJoueur == 2) {
            LOGGER.info("Souhaites-tu revenir au menu du jeu Mastermind?");
            System.out.println("1 - Oui");
            System.out.println("2 - Non");
            do {
                try {
                    bonChoix = true;
                    LOGGER.info(choixJoueur = entree.nextInt());
                } catch (InputMismatchException e) {
                    entree.next();
                    LOGGER.error("Vous ne devez saisir que des chiffres");
                    bonChoix = false;
                }
                if (choixJoueur > 2 || choixJoueur < 1) {
                    LOGGER.error("Votre réponse est incorrecte");
                    LOGGER.error("Veuillez à nouveau rentrer votre choix");
                    bonChoix = false;
                }
            } while (!bonChoix);
            if (choixJoueur == 1) {
                LOGGER.info("Choisis ton mode de jeux :");
                System.out.println("1 - Mode Challenger");
                System.out.println("2 - Mode Defenseur");
                System.out.println("3 - Mode Duel");
                System.out.println("4 - Revenir au menu principal");
                do {
                    try {
                        bonChoix = true;
                        LOGGER.info(choixJoueur = entree.nextInt());
                    } catch (InputMismatchException e) {
                        entree.next();
                        LOGGER.error("Vous ne devez saisir que des chiffres");
                        bonChoix = false;
                    }
                    if (choixJoueur > 4 || choixJoueur < 1) {
                        LOGGER.error("Votre réponse est incorrecte");
                        LOGGER.error("Veuillez à nouveau rentrer votre choix");
                        bonChoix = false;
                    }
                } while (!bonChoix);
                if (choixJoueur == 1) {
                    Mastermind mastermind = new Mastermind();
                    mastermind.challengerMode();
                } else if (choixJoueur == 2) {
                    Mastermind mastermind = new Mastermind();
                    mastermind.defenseMode();
                } else if (choixJoueur == 3) {
                    Mastermind mastermind = new Mastermind();
                    mastermind.duelMode();
                }else if (choixJoueur == 4) {
                    Menu menu = new Menu();
                    menu.firstMenu();
                }
            } else if (choixJoueur == 2) {
                LOGGER.info("Souhaites-tu revenir au menu principal ?");
                System.out.println("1 - Oui");
                System.out.println("2 - Non");
                do {
                    try {
                        bonChoix = true;
                        LOGGER.info(choixJoueur = entree.nextInt());
                    } catch (InputMismatchException e) {
                        entree.next();
                        LOGGER.error("Vous ne devez saisir que des chiffres");
                        bonChoix = false;
                    }
                    if (choixJoueur > 2 || choixJoueur < 1) {
                        LOGGER.error("Votre réponse est incorrecte");
                        LOGGER.error("Veuillez à nouveau rentrer votre choix");
                        bonChoix = false;
                    }
                } while (!bonChoix);
                if (choixJoueur == 1) {
                    Menu menu = new Menu();
                    menu.firstMenu();
                } else if (choixJoueur == 2) {
                    System.out.println("A bientôt");
                }
            }
        }
    }


    @Override
    public void defenseMode() {
    super.defenseMode();
        String nextProposition = ("");
        String [] testTab = nextProposition.split("(?<=.)");
        int proposition = 0;
        int choixJoueur = 0;
        String bienPlace = "-1";
        String malPlace = "-2";
        String enleverNbPlace = "";


        System.out.println("Bienvenue sur le jeu : MasterMind");
        System.out.println("Tu as choisi le mode Défenseur !");
        System.out.println("L'ordinateur doit trouver ta combinaison secrète.");
        System.out.println("Il a le droit à " + getNbMaxEssais() + " essais pour y arriver !");
        System.out.println("Ta combinaison secrète est à " + getNbChiffreJoueur() + " chiffres.");
        LOGGER.info("L'ordinateur entre sa proposition. ");
        /**
         * Premiere proposition du CPU avec la valeur 1 et de la meme longueur que le nombre mystère du joueur
         */
        for (int i = 0; i < getNbChiffreJoueur(); i++) {
            getPremierePropositionTab()[i] = String.valueOf(1);
            premiereProposition = getPremiereProposition() + getPremierePropositionTab()[i];
        }

        nbReponse = premiereProposition;
        LOGGER.info(nbReponse);
        tabReponse = getPremiereProposition().split("(?<=.)");

        while (!Arrays.equals(tabReponse, getJoueurTabMystere()) && getNombreEssais() < getNbMaxEssais()) {
            int nbBienPlace = 0;
            int nbMalPlace = 0;
            String[] copy = tabReponse;
            String copyJoueurNbMystere = JoueurNbMystere;
            String[] compare = copyJoueurNbMystere.split("(?<=.)");

            for (int i = 0; i < getJoueurTabMystere().length; i++) {
                int rechercheBienPlace = compare[i].compareTo(copy[i]);
                if (rechercheBienPlace == 0) {
                    nbBienPlace++;
                    compare[i] = bienPlace;
                    copy[i] = enleverNbPlace;
                }
            }
            for (int a = 0; a < tabReponse.length; a++) {
                for (int i = 0; i < getJoueurTabMystere().length; i++) {
                    int rechercheMalPlace = compare[i].compareTo(copy[a]);
                    if (rechercheMalPlace == 0) {
                        nbMalPlace++;
                        compare[i] = malPlace;
                        break;
                    }
                }
            }

            while(getJoueurTabMystere().length != testTab.length ) {
                nextProposition = ("");
                int[] newProposition = new int[getJoueurTabMystere().length];
                for (int trial = 0; trial < getJoueurTabMystere().length; trial++) {
                    Knuth.Fonction<Integer, List<Integer>> setOfN = KnuthAlgo.setOfN(nbChiffreJoueur);
                    for (int i = 0; i < nbChiffreJoueur; i++) setOfN.appelle(i);
                    for (int s : setOfN.appelle(nbChiffreJoueur - 1)) newProposition[s]++;
                }
                String predictionProposition = "";
                for (int i = 0; i < newProposition.length; i++) {
                    predictionProposition = String.valueOf(newProposition[i]);
                    nextProposition = nextProposition + predictionProposition;
                }
                testTab = nextProposition.split("(?<=.)");
            }
            String eraseTestTab[] = {};
            testTab = eraseTestTab;
            essaiRestant = essaiRestant - 1;
            System.out.println("Il a proposé : " + nbReponse + " -> Voici des indices pour l'aider : Il a " + nbBienPlace + " chiffre(s) de bien placé(s) et " + nbMalPlace + " chiffre(s) de mal placé(s)");
            System.out.println("Il lui reste " + essaiRestant + " essais !");
            System.out.println("L'ordinateur entre sa nouvelle proposition. ");
            nbReponse = nextProposition;
            tabReponse = nextProposition.split("(?<=.)");
            nombreEssais = nombreEssais + 1;
        }
        /**
         .Condition pour le dernier essai
         */
        if (nombreEssais >= getNbMaxEssais() && !Arrays.equals(tabReponse, getJoueurTabMystere())) {
            int nbBienPlace = 0;
            int nbMalPlace = 0;
            String[] copy = tabReponse;
            String copyJoueurNbMystere = JoueurNbMystere;
            String[] compare = copyJoueurNbMystere.split("(?<=.)");

            for (int i = 0; i < getJoueurTabMystere().length; i++) {
                int rechercheBienPlace = compare[i].compareTo(copy[i]);
                if (rechercheBienPlace == 0) {
                    nbBienPlace++;
                    compare[i] = bienPlace;
                    copy[i] = enleverNbPlace;
                }
            }
            for (int a = 0; a < tabReponse.length; a++) {
                for (int i = 0; i < getJoueurTabMystere().length; i++) {
                    int rechercheMalPlace = compare[i].compareTo(copy[a]);
                    if (rechercheMalPlace == 0) {
                        nbMalPlace++;
                        compare[i] = malPlace;
                        break;
                    }
                }
            }

            while(getJoueurTabMystere().length != testTab.length ) {
                nextProposition = ("");
                int[] newProposition = new int[getJoueurTabMystere().length];
                for (int trial = 0; trial < getJoueurTabMystere().length; trial++) {
                    Knuth.Fonction<Integer, List<Integer>> setOfN = KnuthAlgo.setOfN(nbChiffreJoueur);
                    for (int i = 0; i < nbChiffreJoueur; i++) setOfN.appelle(i);
                    for (int s : setOfN.appelle(nbChiffreJoueur - 1)) newProposition[s]++;
                }
                String predictionProposition = "";
                for (int i = 0; i < newProposition.length; i++) {
                    predictionProposition = String.valueOf(newProposition[i]);
                    nextProposition = nextProposition + predictionProposition;
                }
                testTab = nextProposition.split("(?<=.)");
            }
            String eraseTestTab[] = {};
            testTab = eraseTestTab;
            essaiRestant = essaiRestant - 1;
            System.out.println("Il a proposé : " + nbReponse + " -> Voici des indices pour l'aider : Il a " + nbBienPlace + " chiffre(s) de bien placé(s) et " + nbMalPlace + " chiffre(s) de mal placé(s)");
            System.out.println("Mais il a dépassé le nombre d'essais autorisé qui était de " + getNbMaxEssais() + "  essai(s)");
            System.out.println("Voici la solution : " + getJoueurNbMystere() + " Tu as gagné !!!!");
        } else {
            System.out.println("L'ordinateur a proposé : " + nbReponse);
            System.out.println("Tu as perdu !!! L'ordinateur a reussi avec " + nombreEssais + " essai(s)");
        }
        /**
         * Propostion de rejoueur ou de revenir à d'autres menus
         */
        System.out.println("Souhaites-tu rejouer une partie en Mode defense ?");
        System.out.println("1 - Oui");
        System.out.println("2 - Non");
        do {
            try {
                bonChoix = true;
                LOGGER.info(choixJoueur = entree.nextInt());
            } catch (InputMismatchException e) {
                entree.next();
                LOGGER.error("Vous ne devez saisir que des chiffres");
                bonChoix = false;
            }
            if (choixJoueur > 2 || choixJoueur < 1) {
                LOGGER.error("Votre réponse est incorrecte");
                LOGGER.error("Veuillez à nouveau rentrer votre choix");
                bonChoix = false;
            }
        } while (!bonChoix);
        if (choixJoueur == 1) {
            Mastermind mastermind = new Mastermind();
            mastermind.defenseMode();
        } else if (choixJoueur == 2) {
            LOGGER.info("Souhaites-tu revenir au menu du jeu Mastermind?");
            System.out.println("1 - Oui");
            System.out.println("2 - Non");
            do {
                try {
                    bonChoix = true;
                    LOGGER.info(choixJoueur = entree.nextInt());
                } catch (InputMismatchException e) {
                    entree.next();
                    LOGGER.error("Vous ne devez saisir que des chiffres");
                    bonChoix = false;
                }
                if (choixJoueur > 2 || choixJoueur < 1) {
                    LOGGER.error("Votre réponse est incorrecte");
                    LOGGER.error("Veuillez à nouveau rentrer votre choix");
                    bonChoix = false;
                }
            } while (!bonChoix);
            if (choixJoueur == 1) {
                LOGGER.info("Choisis ton mode de jeux :");
                System.out.println("1 - Mode Challenger");
                System.out.println("2 - Mode Defenseur");
                System.out.println("3 - Mode Duel");
                System.out.println("4 - Revenir au menu principal");
                do {
                    try {
                        bonChoix = true;
                        LOGGER.info(choixJoueur = entree.nextInt());
                    } catch (InputMismatchException e) {
                        entree.next();
                        LOGGER.error("Vous ne devez saisir que des chiffres");
                        bonChoix = false;
                    }
                    if (choixJoueur > 4 || choixJoueur < 1) {
                        LOGGER.error("Votre réponse est incorrecte");
                        LOGGER.error("Veuillez à nouveau rentrer votre choix");
                        bonChoix = false;
                    }
                } while (!bonChoix);
                if (choixJoueur == 1) {
                    Mastermind mastermind = new Mastermind();
                    mastermind.challengerMode();
                } else if (choixJoueur == 2) {
                    Mastermind mastermind = new Mastermind();
                    mastermind.defenseMode();
                } else if (choixJoueur == 3) {
                    Mastermind mastermind = new Mastermind();
                    mastermind.duelMode();
                }else if (choixJoueur == 4) {
                    Menu menu = new Menu();
                    menu.firstMenu();
                }
            } else if (choixJoueur == 2) {
                LOGGER.info("Souhaites-tu revenir au menu principal ?");
                System.out.println("1 - Oui");
                System.out.println("2 - Non");
                do {
                    try {
                        bonChoix = true;
                        LOGGER.info(choixJoueur = entree.nextInt());
                    } catch (InputMismatchException e) {
                        entree.next();
                        LOGGER.error("Vous ne devez saisir que des chiffres");
                        bonChoix = false;
                    }
                    if (choixJoueur > 2 || choixJoueur < 1) {
                        LOGGER.error("Votre réponse est incorrecte");
                        LOGGER.error("Veuillez à nouveau rentrer votre choix");
                        bonChoix = false;
                    }
                } while (!bonChoix);
                if (choixJoueur == 1) {
                    Menu menu = new Menu();
                    menu.firstMenu();
                } else if (choixJoueur == 2) {
                    System.out.println("A bientôt");
                }
            }
        }
    }

    @Override
    public void duelMode(){
        /**
         * Paramètres
         */
        super.duelMode();
        String nextProposition = ("");
        String [] testTab = nextProposition.split("(?<=.)");
        int proposition = 0;
        int choixJoueur = 0;
        String bienPlace = "-1";
        String malPlace = "-2";
        String enleverNbPlace = "";
        boolean play = true;
        if (devMode) {
            System.out.println("Mode developpeur activé");
            System.out.println("Voici la combinaison de l'ordinateur : " +getCpuNbMystere() );
            System.out.println("Voici ta combinaison saisi : " +getJoueurNbMystere());
        }

        Joueurs ordinateur = new Joueurs(1);
        Joueurs humain = new Joueurs(2);
        ordinateur.setAdversaire(humain);
        humain.setAdversaire(ordinateur);
        do {
            try {
                bonChoix = true;
                LOGGER.info(choixJoueur = entree.nextInt());
            } catch (InputMismatchException e) {
                entree.next();
                LOGGER.error("Vous ne devez saisir que des chiffres");
                bonChoix = false;
            }
            if (choixJoueur > 2 || choixJoueur < 1) {
                LOGGER.error("Votre réponse est incorrecte");
                LOGGER.error("Veuillez à nouveau rentrer votre choix");
                bonChoix = false;
            }
        } while (!bonChoix);
        if (choixJoueur == 1) {
            actifOrdi = true;
        } else if (choixJoueur == 2) {
            actifHumain = true;
        }

        while (play) {
            /**
             * CPU
             */
            while (actifOrdi) {
                /**
                 * Premiere proposition CPU
                 */
                if (nombreEssaisOrdi < 1) {
                    LOGGER.info("L'ordinateur saisit sa proposition.");
                    int nbBienPlace = 0;
                    int nbMalPlace = 0;
                    String[] copy = tabReponse;
                    String copyJoueurNbMystere = JoueurNbMystere;
                    String[] compare = copyJoueurNbMystere.split("(?<=.)");

                    for (int i = 0; i < getJoueurTabMystere().length; i++) {
                        int rechercheBienPlace = compare[i].compareTo(copy[i]);
                        if (rechercheBienPlace == 0) {
                            nbBienPlace++;
                            compare[i] = bienPlace;
                            copy[i] = enleverNbPlace;
                        }
                    }
                    for (int a = 0; a < tabReponse.length; a++) {
                        for (int i = 0; i < getJoueurTabMystere().length; i++) {
                            int rechercheMalPlace = compare[i].compareTo(copy[a]);
                            if (rechercheMalPlace == 0) {
                                nbMalPlace++;
                                compare[i] = malPlace;
                                break;
                            }
                        }
                    }

                    while(getJoueurTabMystere().length != testTab.length ) {
                        nextProposition = ("");
                        int[] newProposition = new int[getJoueurTabMystere().length];
                        for (int trial = 0; trial < getJoueurTabMystere().length; trial++) {
                            Knuth.Fonction<Integer, List<Integer>> setOfN = KnuthAlgo.setOfN(nbChiffreJoueur);
                            for (int i = 0; i < nbChiffreJoueur; i++) setOfN.appelle(i);
                            for (int s : setOfN.appelle(nbChiffreJoueur - 1)) newProposition[s]++;
                        }
                        String predictionProposition = "";
                        for (int i = 0; i < newProposition.length; i++) {
                            predictionProposition = String.valueOf(newProposition[i]);
                            nextProposition = nextProposition + predictionProposition;
                        }
                        testTab = nextProposition.split("(?<=.)");
                    }
                    String eraseTestTab[] = {};
                    testTab = eraseTestTab;
                    essaiRestantOrdi = essaiRestantOrdi - 1;
                    nombreEssaisOrdi = nombreEssaisOrdi + 1;
                    System.out.println("Il a proposé : " + nbReponse + " -> Voici des indices pour l'aider : Il a " + nbBienPlace + " chiffre(s) de bien placé(s) et " + nbMalPlace + " chiffre(s) de mal placé(s)");
                    System.out.println("Il lui reste " + essaiRestantOrdi + " essais !");
                    actifHumain = true;
                    actifOrdi = false;
                    ordinateur.setAdversaire(humain);
                } else if (!Arrays.equals(tabReponse, getJoueurTabMystere()) && nombreEssaisOrdi < getNbMaxEssais()) {
                    LOGGER.info("L'ordinateur saisi sa réponse.");
                    nbReponse = nextProposition;
                    LOGGER.info(nbReponse);
                    tabReponse = nbReponse.split("(?<=.)");
                    nextProposition = "";
                    if (!Arrays.equals(tabReponse, getJoueurTabMystere()) && getNombreEssaisOrdi() < getNbMaxEssais() && actifOrdi) {
                        int nbBienPlace = 0;
                        int nbMalPlace = 0;
                        String[] copy = tabReponse;
                        String copyJoueurNbMystere = JoueurNbMystere;
                        String[] compare = copyJoueurNbMystere.split("(?<=.)");

                        for (int i = 0; i < getJoueurTabMystere().length; i++) {
                            int rechercheBienPlace = compare[i].compareTo(copy[i]);
                            if (rechercheBienPlace == 0) {
                                nbBienPlace++;
                                compare[i] = bienPlace;
                                copy[i] = enleverNbPlace;
                            }
                        }
                        for (int a = 0; a < tabReponse.length; a++) {
                            for (int i = 0; i < getJoueurTabMystere().length; i++) {
                                int rechercheMalPlace = compare[i].compareTo(copy[a]);
                                if (rechercheMalPlace == 0) {
                                    nbMalPlace++;
                                    compare[i] = malPlace;
                                    break;
                                }
                            }
                        }

                        while(getJoueurTabMystere().length != testTab.length ) {
                            nextProposition = ("");
                            int[] newProposition = new int[getJoueurTabMystere().length];
                            for (int trial = 0; trial < getJoueurTabMystere().length; trial++) {
                                Knuth.Fonction<Integer, List<Integer>> setOfN = KnuthAlgo.setOfN(nbChiffreJoueur);
                                for (int i = 0; i < nbChiffreJoueur; i++) setOfN.appelle(i);
                                for (int s : setOfN.appelle(nbChiffreJoueur - 1)) newProposition[s]++;
                            }
                            String predictionProposition = "";
                            for (int i = 0; i < newProposition.length; i++) {
                                predictionProposition = String.valueOf(newProposition[i]);
                                nextProposition = nextProposition + predictionProposition;
                            }
                            testTab = nextProposition.split("(?<=.)");
                        }
                        String eraseTestTab[] = {};
                        testTab = eraseTestTab;
                        essaiRestantOrdi = essaiRestantOrdi - 1;
                        nombreEssaisOrdi = nombreEssaisOrdi + 1;
                        System.out.println("Il a proposé : " + nbReponse + " -> Voici des indices pour l'aider : Il a " + nbBienPlace + " chiffre(s) de bien placé(s) et " + nbMalPlace + " chiffre(s) de mal placé(s)");
                        System.out.println("Il lui reste " + essaiRestantOrdi + " essais !");
                        actifHumain = true;
                        actifOrdi = false;
                        ordinateur.setAdversaire(humain);
                    }
                    if (nombreEssaisOrdi >= getNbMaxEssais() && !Arrays.equals(tabReponse, getJoueurTabMystere()) && actifOrdi) {
                        int nbBienPlace = 0;
                        int nbMalPlace = 0;
                        String[] copy = tabReponse;
                        String copyJoueurNbMystere = JoueurNbMystere;
                        String[] compare = copyJoueurNbMystere.split("(?<=.)");

                        for (int i = 0; i < getJoueurTabMystere().length; i++) {
                            int rechercheBienPlace = compare[i].compareTo(copy[i]);
                            if (rechercheBienPlace == 0) {
                                nbBienPlace++;
                                compare[i] = bienPlace;
                                copy[i] = enleverNbPlace;
                            }
                        }
                        for (int a = 0; a < tabReponse.length; a++) {
                            for (int i = 0; i < getJoueurTabMystere().length; i++) {
                                int rechercheMalPlace = compare[i].compareTo(copy[a]);
                                if (rechercheMalPlace == 0) {
                                    nbMalPlace++;
                                    compare[i] = malPlace;
                                    break;
                                }
                            }
                        }

                        while(getJoueurTabMystere().length != testTab.length ) {
                            nextProposition = ("");
                            int[] newProposition = new int[getJoueurTabMystere().length];
                            for (int trial = 0; trial < getJoueurTabMystere().length; trial++) {
                                Knuth.Fonction<Integer, List<Integer>> setOfN = KnuthAlgo.setOfN(nbChiffreJoueur);
                                for (int i = 0; i < nbChiffreJoueur; i++) setOfN.appelle(i);
                                for (int s : setOfN.appelle(nbChiffreJoueur - 1)) newProposition[s]++;
                            }
                            String predictionProposition = "";
                            for (int i = 0; i < newProposition.length; i++) {
                                predictionProposition = String.valueOf(newProposition[i]);
                                nextProposition = nextProposition + predictionProposition;
                            }
                            testTab = nextProposition.split("(?<=.)");
                        }
                        String eraseTestTab[] = {};
                        testTab = eraseTestTab;
                        essaiRestantOrdi = essaiRestantOrdi - 1;
                        nombreEssaisOrdi = nombreEssaisOrdi + 1;
                        System.out.println("Il a proposé : " + nbReponse + " -> Voici des indices pour l'aider : Il a " + nbBienPlace + " chiffre(s) de bien placé(s) et " + nbMalPlace + " chiffre(s) de mal placé(s)");
                        System.out.println("Mais il a dépassé le nombre d'essais autorisé qui était de " + getNbMaxEssais() + "  essai(s)");
                        System.out.println("Voici la solution : " + getJoueurNbMystere() + " Tu as gagné !!!!");
                        actifHumain = true;
                        actifOrdi = false;
                        ordinateur.setAdversaire(humain);
                        play = essaiRestantHumain != 0;
                    }
                    if (nombreEssaisOrdi <= getNbMaxEssais() && Arrays.equals(tabReponse, getJoueurTabMystere()) && actifOrdi) {
                        System.out.println("L'ordinateur a proposé : " + nbReponse);
                        System.out.println("Tu as perdu !!! L'ordinateur a reussi avec " + nombreEssaisOrdi + " essai(s)");
                        play = false;
                        actifOrdi = false;
                    }
                }
            }

            /**
             * Player
             */
            while (actifHumain) {
                nombreEssaisHumain = nombreEssaisHumain + 1;
                System.out.println("Pour information la combinaision de l'ordinateur est de " + getNbChiffreCpu() + " chiffres !");
                if (isDevMode()) {
                    System.out.println("Mode developpeur activé");
                    System.out.println("Voici la combinaison de l'ordinateur : " +getCpuNbMystere() );
                }
                LOGGER.info("Saisi ta proposition :");
                nbReponse = entree.next();
                LOGGER.info(nbReponse);
                tabReponse = nbReponse.split("(?<=.)");

                while (tabReponse.length != getCpuTabMystere().length) {
                    LOGGER.error("Ta réponse ne fait pas la meme taille que la combinaison secrète !!");
                    System.out.println("Entre une reponse qui fait la taille de " + nbChiffreCpu + " chiffre(s)");
                    nbReponse = entree.next();
                    LOGGER.info(nbReponse);
                    tabReponse = nbReponse.split("(?<=.)");
                }

                while (!Arrays.equals(tabReponse, getCpuTabMystere()) && getNombreEssaisHumain() < getNbMaxEssais() && actifHumain) {
                    int nbBienPlace = 0;
                    int nbMalPlace = 0;
                    String[] copy = tabReponse;
                    String copyCpuNbMystere = CpuNbMystere;
                    String[] compare = copyCpuNbMystere.split("(?<=.)");

                    for (int i = 0; i < getCpuTabMystere().length; i++) {
                        int rechercheBienPlace = compare[i].compareTo(copy[i]);
                        if (rechercheBienPlace == 0) {
                            nbBienPlace++;
                            compare[i] = bienPlace;
                            copy[i] = enleverNbPlace;
                        }
                    }
                    for (int a = 0; a < tabReponse.length; a++) {
                        for (int i = 0; i < getCpuTabMystere().length; i++) {
                            int rechercheMalPlace = compare[i].compareTo(copy[a]);
                            if (rechercheMalPlace == 0) {
                                nbMalPlace++;
                                compare[i] = malPlace;
                                break;
                            }
                        }
                    }
                    essaiRestant = essaiRestant - 1;
                    System.out.println("Tu as proposé : " + nbReponse + " -> Voici des indices pour t'aider : Tu as " + nbBienPlace + " chiffre(s) de bien placé(s) et " + nbMalPlace + " chiffre(s) de mal placé(s)");
                    System.out.println("Il te reste " + essaiRestant + " essais !");
                    actifOrdi = true;
                    actifHumain = false;
                    humain.setAdversaire(ordinateur);

                }
                if (nombreEssaisHumain >= getNbMaxEssais() && !Arrays.equals(tabReponse, getCpuTabMystere()) && actifHumain) {
                    int nbBienPlace = 0;
                    int nbMalPlace = 0;
                    String[] copy = tabReponse;
                    String copyCpuNbMystere = CpuNbMystere;
                    String[] compare = copyCpuNbMystere.split("(?<=.)");

                    for (int i = 0; i < getCpuTabMystere().length; i++) {
                        int rechercheBienPlace = compare[i].compareTo(copy[i]);
                        if (rechercheBienPlace == 0) {
                            nbBienPlace++;
                            compare[i] = bienPlace;
                            copy[i] = enleverNbPlace;
                        }
                    }
                    for (int a = 0; a < tabReponse.length; a++) {
                        for (int i = 0; i < getCpuTabMystere().length; i++) {
                            int rechercheMalPlace = compare[i].compareTo(copy[a]);
                            if (rechercheMalPlace == 0) {
                                nbMalPlace++;
                                compare[i] = malPlace;
                                break;
                            }
                        }
                    }
                    essaiRestant = essaiRestant - 1;
                    System.out.println("Tu as proposé : " + nbReponse + " -> Voici des indices pour t'aider : Tu as " + nbBienPlace + " chiffre(s) de bien placé(s) et " + nbMalPlace + " chiffre(s) de mal placé(s)");
                    System.out.println("Seulement tu as dépassé le nombre d'essais autorisé qui était de " + getNbMaxEssais() + "  essai(s)");
                    System.out.println("Voici la solution : " + getCpuNbMystere());
                    if (essaiRestantOrdi != 0) {
                        play = true;
                    } else {
                        play = false;
                        actifHumain = false;
                    }

                } else if (nombreEssaisHumain <= getNbMaxEssais() && Arrays.equals(tabReponse, getCpuTabMystere()) && actifHumain) {
                    System.out.println("Félicitations !!! Tu as reussi avec " + nombreEssaisHumain + " essai(s)");
                    play = false;
                }
                actifOrdi = true;
                actifHumain = false;
                humain.setAdversaire(ordinateur);
            }
        }
        LOGGER.info("Souhaites-tu rejouer une partie en Mode duel ?");
        System.out.println("1 - Oui");
        System.out.println("2 - Non");
        do {
            try {
                bonChoix = true;
                LOGGER.info(choixJoueur = entree.nextInt());
            } catch (InputMismatchException e) {
                entree.next();
                LOGGER.error("Vous ne devez saisir que des chiffres");
                bonChoix = false;
            }
            if (choixJoueur > 2 || choixJoueur < 1) {
                LOGGER.error("Votre réponse est incorrecte");
                LOGGER.error("Veuillez à nouveau rentrer votre choix");
                bonChoix = false;
            }
        } while (!bonChoix);
        if (choixJoueur == 1) {
            Recherche recherche = new Recherche();
            recherche.duelMode();
        } else if (choixJoueur == 2) {
            LOGGER.info("Souhaites-tu revenir au menu du jeu Recherche +/- ?");
            System.out.println("1 - Oui");
            System.out.println("2 - Non");
            do {
                try {
                    bonChoix = true;
                    LOGGER.info(choixJoueur = entree.nextInt());
                } catch (InputMismatchException e) {
                    entree.next();
                    LOGGER.error("Vous ne devez saisir que des chiffres");
                    bonChoix = false;
                }
                if (choixJoueur > 2 || choixJoueur < 1) {
                    LOGGER.error("Votre réponse est incorrecte");
                    LOGGER.error("Veuillez à nouveau rentrer votre choix");
                    bonChoix = false;
                }
            } while (!bonChoix);
            if (choixJoueur == 1) {
                LOGGER.info("Choisis ton mode de jeux :");
                System.out.println("1 - Mode Challenger");
                System.out.println("2 - Mode Defenseur");
                System.out.println("3 - Mode Duel");
                System.out.println("4 - Revenir au menu principal");
                do {
                    try {
                        bonChoix = true;
                        LOGGER.info(choixJoueur = entree.nextInt());
                    } catch (InputMismatchException e) {
                        entree.next();
                        LOGGER.error("Vous ne devez saisir que des chiffres");
                        bonChoix = false;
                    }
                    if (choixJoueur > 4 || choixJoueur < 1) {
                        LOGGER.error("Votre réponse est incorrecte");
                        LOGGER.error("Veuillez à nouveau rentrer votre choix");
                        bonChoix = false;
                    }
                } while (!bonChoix);
                if (choixJoueur == 1) {
                    Recherche recherche = new Recherche();
                    recherche.challengerMode();
                } else if (choixJoueur == 2) {
                    Recherche recherche = new Recherche();
                    recherche.defenseMode();
                } else if (choixJoueur == 3) {
                    Recherche recherche = new Recherche();
                    recherche.duelMode();
                } else if (choixJoueur == 4) {
                    Menu menu = new Menu();
                    menu.firstMenu();
                }
            } else if (choixJoueur == 2) {
                LOGGER.info("Souhaites-tu revenir au menu principal ?");
                System.out.println("1 - Oui");
                System.out.println("2 - Non");
                do {
                    try {
                        bonChoix = true;
                        LOGGER.info(choixJoueur = entree.nextInt());
                    } catch (InputMismatchException e) {
                        entree.next();
                        LOGGER.error("Vous ne devez saisir que des chiffres");
                        bonChoix = false;
                    }
                    if (choixJoueur > 2 || choixJoueur < 1) {
                        LOGGER.error("Votre réponse est incorrecte");
                        LOGGER.error("Veuillez à nouveau rentrer votre choix");
                        bonChoix = false;
                    }
                } while (!bonChoix);
                if (choixJoueur == 1) {
                    Menu menu = new Menu();
                    menu.firstMenu();
                } else if (choixJoueur == 2) {
                    System.out.println("A bientôt");
                }
            }
        }
    }
}
