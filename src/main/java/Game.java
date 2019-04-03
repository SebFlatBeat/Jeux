import org.apache.commons.text.RandomStringGenerator;

import java.util.Scanner;


public abstract class Game {
    static Scanner entree = new Scanner(System.in);
    protected int nbMaxEssais;
    protected int nbChiffreCpu;
    protected int nbChiffreJoueur;
    protected int nombreEssais;
    protected int essaiRestant;
    protected boolean sortir;
    protected String JoueurNbMystere;
    protected String[] JoueurTabMystere;
    protected String CpuNbMystere;
    protected String[] CpuTabMystere;
    protected String premiereProposition;
    protected String[] premierePropositionTab;
    protected RandomStringGenerator generator = new RandomStringGenerator.Builder()
            .withinRange('0','9').build();

    public String getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(String randomNumber) {
        this.randomNumber = randomNumber;
    }

    protected String randomNumber = generator.generate(4,10);

    public String getPremiereProposition() {
        return premiereProposition;
    }

    public void setPremiereProposition(String premiereProposition) {
        this.premiereProposition = premiereProposition;
    }

    public String[] getPremierePropositionTab() {
        return premierePropositionTab;
    }

    public void setPremierePropositionTab(String[] premierePropositionTab) {
        this.premierePropositionTab = premierePropositionTab;
    }

    public int getNombreEssais() {
        return nombreEssais;
    }

    public void setNombreEssais(int nombreEssais) {
        this.nombreEssais = nombreEssais;
    }

    public int getEssaiRestant() {
        return essaiRestant;
    }

    public void setEssaiRestant(int essaiRestant) {
        this.essaiRestant = essaiRestant;
    }

    public int getNbChiffreCpu() {
        return nbChiffreCpu;
    }

    public void setNbChiffreCpu(int nbChiffreCpu) {
        this.nbChiffreCpu = nbChiffreCpu;
    }

    public String getJoueurNbMystere() {
        return JoueurNbMystere;
    }

    public void setJoueurNbMystere(String joueurNbMystere) {
        JoueurNbMystere = joueurNbMystere;
    }

    public String[] getJoueurTabMystere() {
        return JoueurTabMystere;
    }

    public void setJoueurTabMystere(String[] joueurTabMystere) {
        JoueurTabMystere = joueurTabMystere;
    }

    public String getCpuNbMystere() {
        return CpuNbMystere;
    }

    public void setCpuNbMystere(String cpuNbMystere) {
        CpuNbMystere = cpuNbMystere;
    }

    public String[] getCpuTabMystere() {
        return CpuTabMystere;
    }

    public void setCpuTabMystere(String[] cpuTabMystere) {
        CpuTabMystere = cpuTabMystere;
    }

    public int getNbMaxEssais() {
        return nbMaxEssais;
    }


    public void setNbMaxEssais(int nbMaxEssais) {
        this.nbMaxEssais = nbMaxEssais;

    }

    public int getNbChiffreJoueur() {
        return nbChiffreJoueur;
    }

    public void setNbChiffreJoueur(int nbChiffreJoueur) {
        this.nbChiffreJoueur = nbChiffreJoueur;
    }

    protected void challengerMode () {
        setNbMaxEssais(10);
        setNbChiffreCpu(0);
        setNombreEssais(1);
        setEssaiRestant(getNbMaxEssais());
        setCpuNbMystere(randomNumber);
        setCpuTabMystere(CpuNbMystere.split("(?<=.)"));
        for (int i = 0; i < CpuTabMystere.length; i++) {
            nbChiffreCpu++;
        }
    }

    protected void defenseMode () {
        setNbMaxEssais(10);
        setNbChiffreJoueur(0);
        setNombreEssais(1);
        setEssaiRestant(getNbMaxEssais());
        setPremiereProposition("");
        do {
            System.out.println("Vous pouvez entrer une combinaision de 1 à 10 chiffres");
            System.out.println("Entrez votre combinaison secrète : "); //Faire une limitation de l'intervalle
            setJoueurNbMystere(entree.next());
            while (getJoueurNbMystere().matches("^[a-zA-Z]*$")) {
                System.out.println("Vous ne devez mettre que des chiffres");
                System.out.println("Vous pouvez entrer une combinaision de 1 à 10 chiffres");
                setJoueurNbMystere(entree.next());
            }
            setJoueurTabMystere(JoueurNbMystere.split("(?<=.)"));
            /**
             * Décompte de nbChiffre pour donner un indice au joueur
             */
            for (int i = 0; i< JoueurTabMystere.length; i++){
                nbChiffreJoueur++;
            }
            if (nbChiffreJoueur<1 || nbChiffreJoueur>10) {
                System.out.println("Vous devez entrer une combinaision de 1 à 10 chiffres");
                sortir = false;
            }else{
                sortir = true;
            }
        }while (!sortir);
        setPremierePropositionTab(new String[getNbChiffreJoueur()]);

    }


    protected void duelMode () {
        setNbMaxEssais(10);
        setNbChiffreCpu(0);
        setNbChiffreJoueur(0);
        /**
         * CPU
         */

        setCpuNbMystere(randomNumber);
        setCpuTabMystere(CpuNbMystere.split("(?<=.)"));
        for (int i = 0; i < CpuTabMystere.length; i++) {
            nbChiffreCpu++;
        }
        /**
         * player
         */
        do {
            System.out.println("Vous pouvez entrer une combinaision de 1 à 10 chiffres");
            System.out.println("Entrez votre combinaison secrète : "); //Faire une limitation de l'intervalle
            JoueurNbMystere = entree.next();
            while (JoueurNbMystere.matches("^[a-zA-Z]*$")) {
                System.out.println("Vous ne devez mettre que des chiffres");
                System.out.println("Vous pouvez entrer une combinaision de 1 à 10 chiffres");
                JoueurNbMystere = entree.next();
            }
            JoueurTabMystere = JoueurNbMystere.split("(?<=.)");
            /**
             * Décompte de nbChiffre pour donner un indice au joueur
             */
            for (int i = 0; i< JoueurTabMystere.length; i++){
                nbChiffreJoueur++;
            }
            if (nbChiffreJoueur<1 || nbChiffreJoueur>10) {
                System.out.println("Vous devez entrer une combinaision de 1 à 10 chiffres");
                sortir = false;
            }else{
                sortir = true;
            }
        }while (!sortir);
    }
    protected void developperMode () {

    }
}


