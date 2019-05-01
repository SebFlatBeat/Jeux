package com.enedis.sebastien.Jeux;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Recherche extends Game {

    private static final Logger LOGGER = LogManager.getLogger(Recherche.class.getName());

    static Scanner entree = new Scanner(System.in);
    boolean bonChoix;


    @Override
    public void challengerMode() throws IOException {
        /**
         * parametres
         */
        recherche = true;
        super.challengerMode();
        int choixJoueur = 0;
        System.out.println("Bienvenue sur le jeu : Recherche +/-");
        System.out.println("Tu as choisi le mode Challenger !");
        System.out.println("Tu dois trouver la combinaison secrète. A chaque proposition des indices te seront donnés");
        System.out.println("les indices sont : '+' '-' et '=' ");
        System.out.println("Tu as le droit à " + getNbMaxEssais() + " essais pour y arriver !");
        System.out.println("Pour t'aider sache que la combinaison secrète est à " + getNbChiffreCpu() + " chiffres.");
        if (this.devMode) {
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
            LOGGER.error("Entre une reponse qui fait la taille de " + nbChiffreCpu + " chiffre(s)");
            nbReponse = entree.next();
            LOGGER.info(nbReponse);
            tabReponse = nbReponse.split("(?<=.)");
        }
        /**
         * Boucle tant que la reponse ne correspond pas au nombre mystere et que le nombre d'essai est inferieur au nombre d'essai max
         */
        while (!Arrays.equals(tabReponse, getCpuTabMystere()) && getNombreEssais() < getNbMaxEssais()) {
            setIndice("");
            String[] compare = new String[getCpuTabMystere().length];
            for (int i = 0; i < getCpuTabMystere().length; i++) {
                int valeur = getCpuTabMystere()[i].compareTo(tabReponse[i]);
                if (valeur == 0) {
                    compare[i] = "=";
                } else if (valeur > 0) {
                    compare[i] = "+";
                } else if (valeur < 0) {
                    compare[i] = "-";
                }
                indice = getIndice() + compare[i];
            }
            essaiRestant = essaiRestant - 1;
            System.out.println("Tu as proposé : " + nbReponse + " -> Voici des indices pour t'aider : " + indice);
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
            setIndice("");
            String[] compare = new String[getCpuTabMystere().length];
            for (int i = 0; i < getCpuTabMystere().length; i++) {
                int valeur = getCpuTabMystere()[i].compareTo(tabReponse[i]);
                if (valeur == 0) {
                    compare[i] = "=";
                } else if (valeur > 0) {
                    compare[i] = "+";
                } else if (valeur < 0) {
                    compare[i] = "-";
                }
                indice = getIndice() + compare[i];
            }
            System.out.println("Tu as proposé : " + nbReponse + " -> Voici des indices pour t'aider : " + indice);
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
            Recherche recherche = new Recherche();
            recherche.challengerMode();
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
    public void defenseMode() throws IOException {
        /**
         * Parametres
         */
        super.defenseMode();
        String nextProposition = ("");
        int proposition = 0;
        int choixJoueur = 0;

        System.out.println("Bienvenue sur le jeu : Recherche +/-");
        System.out.println("Tu as choisi le mode Défenseur !");
        System.out.println("L'ordinateur doit trouver ta combinaison secrète.");
        System.out.println("Il a le droit à " + getNbMaxEssais() + " essais pour y arriver !");
        System.out.println("Ta combinaison secrète est à " + getNbChiffreJoueur() + " chiffres.");
        if (this.devMode) {
            System.out.println("Mode developpeur activé");
            System.out.println("Voici ta combinaison saisi : " +getJoueurNbMystere() );
        }

        LOGGER.info("L'ordinateur entre sa proposition. ");
        /**
         * Premiere proposition du CPU avec la valeur 5 et de la meme longueur que le nombre mystère du joueur
         */
        for (int i = 0; i < getNbChiffreJoueur(); i++) {
            getPremierePropositionTab()[i] = String.valueOf(5);
            premiereProposition = getPremiereProposition() + getPremierePropositionTab()[i];
        }
        nbReponse = premiereProposition;
        LOGGER.info(nbReponse);
        tabReponse = getPremiereProposition().split("(?<=.)");

        while (!Arrays.equals(tabReponse, getJoueurTabMystere()) && getNombreEssais() < getNbMaxEssais()) {
            nextProposition = ("");
            String[] compare = new String[getJoueurTabMystere().length];
            indice = ("");
            for (int i = 0; i < getJoueurTabMystere().length; i++) {
                int valeur = getJoueurTabMystere()[i].compareTo(tabReponse[i]);
                int nombre = Integer.parseInt(tabReponse[i]);
                String predictionProposition = "";
                if (valeur == 0) {
                    compare[i] = "=";
                    predictionProposition = tabReponse[i];
                } else if (valeur > 0) {
                    compare[i] = "+";
                } else if (valeur < 0) {
                    compare[i] = "-";
                }
                /**
                 Condition de prediction de proposition avec utilisation d'un random
                 */
                if (predictionProposition != tabReponse[i] && nombre >= 5 && valeur > 0) {
                    proposition = new Random().nextInt(9 - nombre + 1) + nombre;
                    predictionProposition = String.valueOf(proposition);
                } else if (predictionProposition != tabReponse[i] && nombre < 5 && valeur < 0) {
                    proposition = new Random().nextInt(0 + nombre) + 0;
                    predictionProposition = String.valueOf(proposition);
                } else if (predictionProposition != tabReponse[i] && nombre >= 5 && valeur < 0) {
                    proposition = new Random().nextInt(0 + nombre) + 0;
                    predictionProposition = String.valueOf(proposition);
                } else if (predictionProposition != tabReponse[i] && nombre < 5 && valeur > 0) {
                    proposition = new Random().nextInt(9 - nombre + 1) + nombre;
                    predictionProposition = String.valueOf(proposition);
                }
                indice = getIndice() + compare[i];
                nextProposition = nextProposition + predictionProposition;
            }
            essaiRestant = essaiRestant - 1;
            System.out.println("L'ordinateur a proposé : " + nbReponse + " -> Voici des indices pour l'aider : " + indice);
            System.out.println("Il lui reste " + essaiRestant + " essais !");
            LOGGER.info("L'ordinateur entre sa nouvelle proposition. ");
            nbReponse = nextProposition;
            LOGGER.info(nbReponse);
            tabReponse = nextProposition.split("(?<=.)");
            nombreEssais = nombreEssais + 1;
        }
        /**
         .Condition pour le dernier essai
         */
        if (nombreEssais >= getNbMaxEssais() && !Arrays.equals(tabReponse, getJoueurTabMystere())) {
            indice = ("");
            String[] compare = new String[getJoueurTabMystere().length];
            for (int i = 0; i < getJoueurTabMystere().length; i++) {
                int valeur = getJoueurTabMystere()[i].compareTo(tabReponse[i]);
                int nombre = Integer.parseInt(tabReponse[i]);
                String predictionProposition = "";
                if (valeur == 0) {
                    compare[i] = "=";
                    predictionProposition = tabReponse[i];
                } else if (valeur > 0) {
                    compare[i] = "+";
                } else if (valeur < 0) {
                    compare[i] = "-";
                }
                /**
                 Condition de prediction de proposition avec utilisation d'un random
                 */
                if (predictionProposition != tabReponse[i] && nombre >= 5 && valeur > 0) {
                    proposition = new Random().nextInt(9 - nombre + 1) + nombre;
                    predictionProposition = String.valueOf(proposition);
                } else if (predictionProposition != tabReponse[i] && nombre < 5 && valeur < 0) {
                    proposition = new Random().nextInt(0 + nombre + 1) + nombre;
                    predictionProposition = String.valueOf(proposition);
                } else if (predictionProposition != tabReponse[i] && nombre >= 5 && valeur < 0) {
                    proposition = new Random().nextInt(9 - nombre + 1) + nombre;
                    predictionProposition = String.valueOf(proposition);
                } else if (predictionProposition != tabReponse[i] && nombre < 5 && valeur > 0) {
                    proposition = new Random().nextInt(0 + nombre + 1) + nombre;
                    predictionProposition = String.valueOf(proposition);
                }
                indice = getIndice() + compare[i];
                nextProposition = nextProposition + predictionProposition;
            }
            System.out.println("L'ordinateur a proposé : " + nbReponse + " -> Voici des indices pour l'aider : " + indice);
            System.out.println("Mais il a dépassé le nombre d'essais autorisé qui était de " + getNbMaxEssais() + "  essai(s)");
            System.out.println("Voici la solution : " + getJoueurNbMystere() + " Tu as gagné !!!!");
        } else {
            System.out.println("L'ordinateur a proposé : " + nbReponse);
            System.out.println("Tu as perdu !!! L'ordinateur a reussi avec " + nombreEssais + " essai(s)");
        }
        /**
         * Propostion de rejoueur ou de revenir à d'autres menus
         */
        LOGGER.info("Souhaites-tu rejouer une partie en Mode defense ?");
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
            recherche.defenseMode();
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


    @Override
    protected void duelMode() throws IOException {
        /**
         * Paramètres
         */
        super.duelMode();
        String nextProposition = ("");
        int proposition = 0;
        int choixJoueur = 0;
        boolean play = true;
        if (this.devMode) {
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
                    for (int i = 0; i < getNbChiffreJoueur(); i++) {
                        getPremierePropositionTab()[i] = String.valueOf(5);
                        premiereProposition = getPremiereProposition() + getPremierePropositionTab()[i];
                    }
                    nbReponse = premiereProposition;
                    LOGGER.info(nbReponse);
                    tabReponse = getPremiereProposition().split("(?<=.)");
                    String[] compare = new String[getJoueurTabMystere().length];
                    indice = ("");
                    for (int i = 0; i < getJoueurTabMystere().length; i++) {
                        int valeur = getJoueurTabMystere()[i].compareTo(tabReponse[i]);
                        int nombre = Integer.parseInt(tabReponse[i]);
                        String predictionProposition = "";
                        if (valeur == 0) {
                            compare[i] = "=";
                            predictionProposition = tabReponse[i];
                        } else if (valeur > 0) {
                            compare[i] = "+";
                        } else if (valeur < 0) {
                            compare[i] = "-";
                        }
                        /**
                         Condition de prediction de proposition avec utilisation d'un random
                         */
                        if (predictionProposition != tabReponse[i] && nombre >= 5 && valeur > 0) {
                            proposition = new Random().nextInt(9 - nombre + 1) + nombre;
                            predictionProposition = String.valueOf(proposition);
                        } else if (predictionProposition != tabReponse[i] && nombre < 5 && valeur < 0) {
                            proposition = new Random().nextInt(0 + nombre) + 0;
                            predictionProposition = String.valueOf(proposition);
                        } else if (predictionProposition != tabReponse[i] && nombre >= 5 && valeur < 0) {
                            proposition = new Random().nextInt(0 + nombre) + 0;
                            predictionProposition = String.valueOf(proposition);
                        } else if (predictionProposition != tabReponse[i] && nombre < 5 && valeur > 0) {
                            proposition = new Random().nextInt(9 - nombre + 1) + nombre;
                            predictionProposition = String.valueOf(proposition);
                        }
                        indice = getIndice() + compare[i];
                        nextProposition = nextProposition + predictionProposition;
                    }
                    essaiRestantOrdi = essaiRestantOrdi - 1;
                    nombreEssaisOrdi = nombreEssaisOrdi + 1;
                    System.out.println("L'ordinateur a proposé : " + nbReponse + " -> Voici des indices pour l'aider : " + indice);
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
                        String[] compare = new String[getJoueurTabMystere().length];
                        indice = ("");
                        for (int i = 0; i < getJoueurTabMystere().length; i++) {
                            int valeur = getJoueurTabMystere()[i].compareTo(tabReponse[i]);
                            int nombre = Integer.parseInt(tabReponse[i]);
                            String predictionProposition = "";
                            if (valeur == 0) {
                                compare[i] = "=";
                                predictionProposition = tabReponse[i];
                            } else if (valeur > 0) {
                                compare[i] = "+";
                            } else if (valeur < 0) {
                                compare[i] = "-";
                            }
                            /**
                             Condition de prediction de proposition avec utilisation d'un random
                             */
                            if (predictionProposition != tabReponse[i] && nombre >= 5 && valeur > 0) {
                                proposition = new Random().nextInt(9 - nombre + 1) + nombre;
                                predictionProposition = String.valueOf(proposition);
                            } else if (predictionProposition != tabReponse[i] && nombre < 5 && valeur < 0) {
                                proposition = new Random().nextInt(0 + nombre) + 0;
                                predictionProposition = String.valueOf(proposition);
                            } else if (predictionProposition != tabReponse[i] && nombre >= 5 && valeur < 0) {
                                proposition = new Random().nextInt(0 + nombre) + 0;
                                predictionProposition = String.valueOf(proposition);
                            } else if (predictionProposition != tabReponse[i] && nombre < 5 && valeur > 0) {
                                proposition = new Random().nextInt(9 - nombre + 1) + nombre;
                                predictionProposition = String.valueOf(proposition);
                            }
                            indice = getIndice() + compare[i];
                            nextProposition = nextProposition + predictionProposition;
                        }
                        essaiRestantOrdi = essaiRestantOrdi - 1;
                        nombreEssaisOrdi = nombreEssaisOrdi + 1;
                        System.out.println("L'ordinateur a proposé : " + nbReponse + " -> Voici des indices pour l'aider : " + indice);
                        System.out.println("Il lui reste " + essaiRestantOrdi + " essais !");
                        actifHumain = true;
                        actifOrdi = false;
                        ordinateur.setAdversaire(humain);
                    }
                    if (nombreEssaisOrdi >= getNbMaxEssais() && !Arrays.equals(tabReponse, getJoueurTabMystere()) && actifOrdi) {
                        indice = ("");
                        String[] compare = new String[getJoueurTabMystere().length];
                        for (int i = 0; i < getJoueurTabMystere().length; i++) {
                            int valeur = getJoueurTabMystere()[i].compareTo(tabReponse[i]);
                            int nombre = Integer.parseInt(tabReponse[i]);
                            String predictionProposition = "";
                            if (valeur == 0) {
                                compare[i] = "=";
                                predictionProposition = tabReponse[i];
                            } else if (valeur > 0) {
                                compare[i] = "+";
                            } else if (valeur < 0) {
                                compare[i] = "-";
                            }
                            /**
                             Condition de prediction de proposition avec utilisation d'un random
                             */
                            if (predictionProposition != tabReponse[i] && nombre >= 5 && valeur > 0) {
                                proposition = new Random().nextInt(9 - nombre + 1) + nombre;
                                predictionProposition = String.valueOf(proposition);
                            } else if (predictionProposition != tabReponse[i] && nombre < 5 && valeur < 0) {
                                proposition = new Random().nextInt(0 + nombre + 1) + nombre;
                                predictionProposition = String.valueOf(proposition);
                            } else if (predictionProposition != tabReponse[i] && nombre >= 5 && valeur < 0) {
                                proposition = new Random().nextInt(9 - nombre + 1) + nombre;
                                predictionProposition = String.valueOf(proposition);
                            } else if (predictionProposition != tabReponse[i] && nombre < 5 && valeur > 0) {
                                proposition = new Random().nextInt(0 + nombre + 1) + nombre;
                                predictionProposition = String.valueOf(proposition);
                            }
                            indice = getIndice() + compare[i];
                            nextProposition = nextProposition + predictionProposition;
                        }
                        System.out.println("L'ordinateur a proposé : " + nbReponse + " -> Voici des indices pour l'aider : " + indice);
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
                if (devMode = true) {
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
                    setIndice("");
                    String[] compare = new String[getCpuTabMystere().length];
                    for (int i = 0; i < getCpuTabMystere().length; i++) {
                        int valeur = getCpuTabMystere()[i].compareTo(tabReponse[i]);
                        if (valeur == 0) {
                            compare[i] = "=";
                        } else if (valeur > 0) {
                            compare[i] = "+";
                        } else if (valeur < 0) {
                            compare[i] = "-";
                        }
                        indice = getIndice() + compare[i];
                    }
                    essaiRestantHumain = essaiRestantHumain - 1;
                    System.out.println("Tu as proposé : " + nbReponse + " -> Voici des indices pour t'aider : " + indice);
                    System.out.println("Il te reste " + essaiRestantHumain + " essais !");
                    actifOrdi = true;
                    actifHumain = false;
                    humain.setAdversaire(ordinateur);

                }
                if (nombreEssaisHumain >= getNbMaxEssais() && !Arrays.equals(tabReponse, getCpuTabMystere()) && actifHumain) {
                    setIndice("");
                    String[] compare = new String[getCpuTabMystere().length];
                    for (int i = 0; i < getCpuTabMystere().length; i++) {
                        int valeur = getCpuTabMystere()[i].compareTo(tabReponse[i]);
                        if (valeur == 0) {
                            compare[i] = "=";
                        } else if (valeur > 0) {
                            compare[i] = "+";
                        } else if (valeur < 0) {
                            compare[i] = "-";
                        }
                        indice = getIndice() + compare[i];
                    }
                    System.out.println("Tu as proposé : " + nbReponse + " -> Voici des indices pour t'aider : " + indice);
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