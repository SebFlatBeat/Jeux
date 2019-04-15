import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Recherche extends Game {

    static Scanner entree = new Scanner(System.in);

    @Override
    public void challengerMode() {
        super.challengerMode();
        System.out.println("Bienvenue sur le jeu : Recherche +/-");
        System.out.println("Tu as choisi le mode Challenger !");
        System.out.println("Tu dois trouver la combinaison secrète. A chaque proposition des indices te seront donnés");
        System.out.println("les indices sont : '+' '-' et '=' ");
        System.out.println("Tu as le droit à " + getNbMaxEssais() + " essais pour y arriver !");
        System.out.println("Pour t'aider sache que la combinaison secrète est à " + getNbChiffreCpu() + " chiffres.");

        System.out.println("Entre ta proposition : "); //Pense à catcher une exception afin que nbReponse ne soit pas inferieur ou superieur nbChiffre
        nbReponse = entree.next();
        tabReponse = nbReponse.split("(?<=.)");

        while (tabReponse.length != getCpuTabMystere().length) {
            System.out.println("Ta réponse ne fait pas la meme taille que la combinaison secrète !!");
            System.out.println("Entre une reponse qui fait la taille de " + nbChiffreCpu + " chiffre(s)");
            nbReponse = entree.next();
            tabReponse = nbReponse.split("(?<=.)");
        }

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
            System.out.println("Entre une nouvelle proposition : ");
            nbReponse = entree.next();
            tabReponse = nbReponse.split("(?<=.)");
            while (tabReponse.length != getCpuTabMystere().length) {
                System.out.println("Ta réponse ne fait pas la meme taille que la combinaison secrète !!");
                System.out.println("Entre une reponse qui fait la taille de " + nbChiffreCpu + " chiffres");
                nbReponse = entree.next();
                tabReponse = nbReponse.split("(?<=.)");
            }
            nombreEssais = nombreEssais + 1;
        }
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
    }


    @Override
    public void defenseMode() {
        super.defenseMode();
        String nextProposition = ("");
        int proposition = 0;
        System.out.println("Bienvenue sur le jeu : Recherche +/-");
        System.out.println("Tu as choisi le mode Défenseur !");
        System.out.println("L'ordinateur doit trouver ta combinaison secrète.");
        System.out.println("Il a le droit à " + getNbMaxEssais() + " essais pour y arriver !");
        System.out.println("Ta combinaison secrète est à " + getNbChiffreJoueur() + " chiffres.");

        System.out.println("L'ordinateur entre sa proposition. ");
        /**
         * Premiere proposition du CPU avec la valeur 5 et de la meme longueur que le nombre mystère du joueur
         */
        for (int i = 0; i < getNbChiffreJoueur(); i++) {
            getPremierePropositionTab()[i] = String.valueOf(5);
            premiereProposition = getPremiereProposition() + getPremierePropositionTab()[i];
        }
        nbReponse = premiereProposition;
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
            System.out.println("L'ordinateur entre sa nouvelle proposition. ");
            nbReponse = nextProposition;
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
    }


    @Override
    protected void duelMode() {
        super.duelMode();
        String nextProposition = ("");
        int proposition = 0;
        int choixJoueur;
        boolean play = true;

        Joueurs ordinateur = new Joueurs(1);
        Joueurs humain = new Joueurs(2);
        ordinateur.setAdversaire(humain);
        humain.setAdversaire(ordinateur);
        choixJoueur = entree.nextInt();
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
                    System.out.println("L'ordinateur saisit sa proposition.");
                    for (int i = 0; i < getNbChiffreJoueur(); i++) {
                        getPremierePropositionTab()[i] = String.valueOf(5);
                        premiereProposition = getPremiereProposition() + getPremierePropositionTab()[i];
                    }
                    nbReponse = premiereProposition;
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
                    System.out.println("L'ordinateur saisi sa réponse.");
                    nbReponse = nextProposition;
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
                        if (essaiRestantHumain != 0) {
                            play = true;
                        } else {
                            play = false;
                        }
                    }
                    if (nombreEssaisOrdi <= getNbMaxEssais() && Arrays.equals(tabReponse, getJoueurTabMystere()) && actifOrdi) {
                        System.out.println("L'ordinateur a proposé : " + nbReponse);
                        System.out.println("Tu as perdu !!! L'ordinateur a reussi avec " + nombreEssaisOrdi + " essai(s)");
                        play = false;
                    }
                }
            }

            /**
             * Player
             */
            while (actifHumain) {
                nombreEssaisHumain = nombreEssaisHumain + 1;
                System.out.println("Pour information la combinaision de l'ordinateur est de " + getNbChiffreCpu() + " chiffres !");
                System.out.println("Saisi ta proposition :");
                nbReponse = entree.next();
                tabReponse = nbReponse.split("(?<=.)");

                while (tabReponse.length != getCpuTabMystere().length) {
                    System.out.println("Ta réponse ne fait pas la meme taille que la combinaison secrète !!");
                    System.out.println("Entre une reponse qui fait la taille de " + nbChiffreCpu + " chiffre(s)");
                    nbReponse = entree.next();
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
        System.out.println("Souhaites-tu rejouer une partie en Mode duel ?");
        System.out.println("1 - Oui");
        System.out.println("2 - Non");
        choixJoueur = entree.nextInt();
        if (choixJoueur == 1) {
            duelMode();
        }else if (choixJoueur == 2) {
            System.out.println("Souhaites-tu revenir au menu principal ?");
            System.out.println("1 - Oui");
            System.out.println("2 - Non");
            choixJoueur = entree.nextInt();
            if (choixJoueur == 1) {
                Menu menu = new Menu();
                menu.firstMenu();
            }else if (choixJoueur == 2) {
                System.out.println("A bientôt");
            }
        }
    }
}

