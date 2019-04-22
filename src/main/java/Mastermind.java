import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Mastermind extends Game{

    @Override
    public void challengerMode() {
        super.challengerMode();
        /**
         * Parametres supplementaires
         */
        int choixJoueur;
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
        if (isDevMode()) {
            System.out.println("Mode developpeur activé");
            System.out.println("Voici la combinaison de l'ordinateur : " +getCpuNbMystere() );
        }
        System.out.println("Entre ta proposition : ");
        nbReponse = entree.next();
        tabReponse = nbReponse.split("(?<=.)");

        /**
         * Boucle permettant de s'assurer que la saisie de l'utilisateur fasse la meme taille que la combinaison secrete
         */
        while (tabReponse.length != getCpuTabMystere().length) {
            System.out.println("Ta réponse ne fait pas la meme taille que la combinaison secrète !!");
            System.out.println("Entre une reponse qui fait la taille de " + nbChiffreCpu + " chiffre(s)");
            nbReponse = entree.next();
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
            System.out.println("Entre une nouvelle proposition : ");
            nbReponse = entree.next();
            tabReponse = nbReponse.split("(?<=.)");
            /**
             * Boucle permettant de s'assurer que la saisie de l'utilisateur fasse la meme taille que la combinaison secrete
             */
            while (tabReponse.length != getCpuTabMystere().length) {
                System.out.println("Ta réponse ne fait pas la meme taille que la combinaison secrète !!");
                System.out.println("Entre une reponse qui fait la taille de " + nbChiffreCpu + " chiffres");
                nbReponse = entree.next();
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
        System.out.println("Souhaites-tu rejouer une partie en Mode challenger ?");
        System.out.println("1 - Oui");
        System.out.println("2 - Non");
        choixJoueur = entree.nextInt();
        if (choixJoueur == 1) {
            Mastermind mastermind = new Mastermind();
            mastermind.challengerMode();
        } else if (choixJoueur == 2) {
            System.out.println("Souhaites-tu revenir au menu du jeu Mastermind?");
            System.out.println("1 - Oui");
            System.out.println("2 - Non");
            choixJoueur = entree.nextInt();
            if (choixJoueur == 1) {
                System.out.println("Choisis ton mode de jeux :");
                System.out.println("1 - Mode Challenger");
                System.out.println("2 - Mode Defenseur");
                System.out.println("3 - Mode Duel");
                System.out.println("4 - Revenir au menu principal");
                choixJoueur = entree.nextInt();
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
                System.out.println("Souhaites-tu revenir au menu principal ?");
                System.out.println("1 - Oui");
                System.out.println("2 - Non");
                choixJoueur = entree.nextInt();
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
        int proposition = 0;
        int choixJoueur;
        System.out.println("Bienvenue sur le jeu : MasterMind");
        System.out.println("Tu as choisi le mode Défenseur !");
        System.out.println("L'ordinateur doit trouver ta combinaison secrète.");
        System.out.println("Il a le droit à " + getNbMaxEssais() + " essais pour y arriver !");
        System.out.println("Ta combinaison secrète est à " + getNbChiffreJoueur() + " chiffres.");

        System.out.println("L'ordinateur entre sa proposition. ");

        while (!Arrays.equals(tabReponse, getJoueurTabMystere()) && getNombreEssais() < getNbMaxEssais()) {
            int nbBienPlace = 0;
            int nbMalPlace = 0;
            int joueurNbMystere = Integer.parseInt(getJoueurNbMystere());
            int[] bin = new int[getJoueurTabMystere().length];
            for (int trial = 0; trial < joueurNbMystere ; trial++) {
                Knuth.Fonction<Integer, List<Integer>> setOfN = KnuthAlgo.setOfN(9);
                for (int i = 0; i < getJoueurTabMystere().length; i++) setOfN.appelle(i);
                for (int s : setOfN.appelle(getJoueurTabMystere().length)) bin[s]++;
            }

            int [] compare = new int[joueurNbMystere];

            for (int i = 0;i<getJoueurTabMystere().length;i++) {
                boolean rechercheBienPlace = bin.equals(compare);

            }

            }
            essaiRestant = essaiRestant - 1;
            System.out.println("L'ordinateur a proposé : " + nbReponse + " -> Voici des indices pour l'aider : " + indice);
            System.out.println("Il lui reste " + essaiRestant + " essais !");
            System.out.println("L'ordinateur entre sa nouvelle proposition. ");
            nbReponse = nextProposition;
            tabReponse = nextProposition.split("(?<=.)");
            nombreEssais = nombreEssais + 1;

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
        System.out.println("Souhaites-tu rejouer une partie en Mode defense ?");
        System.out.println("1 - Oui");
        System.out.println("2 - Non");
        choixJoueur = entree.nextInt();
        if (choixJoueur == 1) {
            Mastermind mastermind = new Mastermind();
            mastermind.defenseMode();
        } else if (choixJoueur == 2) {
            System.out.println("Souhaites-tu revenir au menu du jeu MasterMind?");
            System.out.println("1 - Oui");
            System.out.println("2 - Non");
            choixJoueur = entree.nextInt();
            if (choixJoueur == 1) {
                System.out.println("Choisis ton mode de jeux :");
                System.out.println("1 - Mode Challenger");
                System.out.println("2 - Mode Defenseur");
                System.out.println("3 - Mode Duel");
                System.out.println("4 - Revenir au menu principal");
                choixJoueur = entree.nextInt();
                if (choixJoueur == 1) {
                    Mastermind mastermind = new Mastermind();
                    mastermind.challengerMode();
                } else if (choixJoueur == 2) {
                    Mastermind mastermind = new Mastermind();
                    mastermind.defenseMode();
                } else if (choixJoueur == 3) {
                    Mastermind mastermind = new Mastermind();
                    mastermind.duelMode();
                } else if (choixJoueur == 4) {
                    Menu menu = new Menu();
                    menu.firstMenu();
                }
            } else if (choixJoueur == 2) {
                System.out.println("Souhaites-tu revenir au menu principal ?");
                System.out.println("1 - Oui");
                System.out.println("2 - Non");
                choixJoueur = entree.nextInt();
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
    super.duelMode();
    }

    @Override
    public void developperMode(){
    super.developperMode();
        int choixJoueur;
        System.out.println("Le nombre mystere de l'ordinateur est : ");
        System.out.println(getCpuNbMystere());
        System.out.println("Souhaites-tu revenir au menu du jeu Recherche +/- ?");
        System.out.println("1 - Oui");
        System.out.println("2 - Non");
        choixJoueur = entree.nextInt();
        if (choixJoueur == 1) {
            System.out.println("Choisis ton mode de jeux :");
            System.out.println("1 - Mode Challenger");
            System.out.println("2 - Mode Defenseur");
            System.out.println("3 - Mode Duel");
            System.out.println("4 - Revenir au menu principal");
            choixJoueur = entree.nextInt();
            if (choixJoueur == 1) {
                Mastermind mastermind = new Mastermind();
                mastermind.challengerMode();
            } else if (choixJoueur == 2) {
                Mastermind mastermind = new Mastermind();
                mastermind.defenseMode();
            } else if (choixJoueur == 3) {
                Mastermind mastermind = new Mastermind();
                mastermind.duelMode();
            } else if (choixJoueur == 4) {
                Menu menu = new Menu();
                menu.firstMenu();
            }
        } else if (choixJoueur == 2) {
            System.out.println("Souhaites-tu revenir au menu principal ?");
            System.out.println("1 - Oui");
            System.out.println("2 - Non");
            choixJoueur = entree.nextInt();
            if (choixJoueur == 1) {
                Menu menu = new Menu();
                menu.firstMenu();
            } else if (choixJoueur == 2) {
                System.out.println("A bientôt");
            }
        }
    }
}
