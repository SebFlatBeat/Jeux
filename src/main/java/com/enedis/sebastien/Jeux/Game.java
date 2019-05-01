package com.enedis.sebastien.Jeux;

import com.enedis.sebastien.Config.GetPropertyValues;
import org.apache.commons.text.RandomStringGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


public abstract class Game {

    private static final Logger LOGGER = LogManager.getLogger(Game.class.getName());
    static Scanner entree = new Scanner(System.in);

    protected int nbMaxEssais;
    protected int nbChiffreCpu;
    protected int nbChiffreJoueur;
    protected int nombreEssais;
    protected int essaiRestant;
    protected int nombreEssaisOrdi;
    protected int nombreEssaisHumain;
    protected int essaiRestantOrdi;
    protected int essaiRestantHumain;
    protected int longueurCombinaison;
    protected String randomNumber;
    protected String nbReponse;
    protected String[] tabReponse;
    protected String JoueurNbMystere;
    protected String[] JoueurTabMystere;
    protected String CpuNbMystere;
    protected String[] CpuTabMystere;
    protected String premiereProposition;
    protected String[] premierePropositionTab;
    protected String indice;
    protected String nextProposition;
    protected String collecteRandomNumber;
    protected static boolean devMode;
    protected boolean actifOrdi;
    protected boolean actifHumain;
    protected boolean sortir;
    protected boolean recherche;
    protected boolean mastermind;

    public void setLongueurCombinaison(int longueurCombinaison) {
        this.longueurCombinaison = longueurCombinaison;
    }

    public int getLongueurCombinaison() {
        return longueurCombinaison;
    }

    public boolean isDevMode() {
        return devMode;
    }

    public void setDevMode(boolean devMode) {
        this.devMode = devMode;
    }

    public int getNombreEssaisOrdi() {
        return nombreEssaisOrdi;
    }

    public void setNombreEssaisOrdi(int nombreEssaisOrdi) {
        this.nombreEssaisOrdi = nombreEssaisOrdi;
    }

    public int getNombreEssaisHumain() {
        return nombreEssaisHumain;
    }

    public void setNombreEssaisHumain(int nombreEssaisHumain) {
        this.nombreEssaisHumain = nombreEssaisHumain;
    }

    public int getEssaiRestantOrdi() {
        return essaiRestantOrdi;
    }

    public void setEssaiRestantOrdi(int essaiRestantOrdi) {
        this.essaiRestantOrdi = essaiRestantOrdi;
    }

    public int getEssaiRestantHumain() {
        return essaiRestantHumain;
    }

    public void setEssaiRestantHumain(int essaiRestantHumain) {
        this.essaiRestantHumain = essaiRestantHumain;
    }


    protected RandomStringGenerator generator = new RandomStringGenerator.Builder()
            .withinRange('0','9').build();

    Random random = new Random();
    protected int generatorMastermind;



    public String getRandomNumber() {
        return randomNumber;
    }

    public void setGenerator(RandomStringGenerator generator) {
        this.generator = generator;
    }

    public String getNextProposition() {
        return nextProposition;
    }

    public void setNextProposition(String nextProposition) {
        this.nextProposition = nextProposition;
    }

    public String getIndice() {
        return indice;
    }

    public void setIndice(String indice) {
        this.indice = indice;
    }

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

    public String getNbReponse() {
        return nbReponse;
    }

    public void setNbReponse(String nbReponse) {
        this.nbReponse = nbReponse;
    }

    public String[] getTabReponse() {
        return tabReponse;
    }

    public void setTabReponse(String[] tabReponse) {
        this.tabReponse = tabReponse;
    }


    protected void challengerMode() throws IOException {
        /**
         * parametrages
         */
        GetPropertyValues properties = new GetPropertyValues();
        setNbMaxEssais(Integer.parseInt(properties.getPropValues("nbMaxEssais")));
        setNombreEssais(Integer.parseInt(properties.getPropValues2("nombreEssais")));
        setLongueurCombinaison(Integer.parseInt(properties.getPropValues3("longueurCombinaison")));
        setNbChiffreCpu(0);
        setEssaiRestant(getNbMaxEssais());
        randomNumber = "";
        if (recherche){
        randomNumber = generator.generate(longueurCombinaison);}
        else if (mastermind){
            while (randomNumber.length()!=longueurCombinaison){
                generatorMastermind = (Integer.parseInt(properties.getPropValues5("chiffreMinUtilisable")) + random.nextInt((Integer.parseInt(properties.getPropValues6("chiffreMaxUtilisable"))-(Integer.parseInt(properties.getPropValues5("chiffreMinUtilisable"))))));
                collecteRandomNumber = String.valueOf(generatorMastermind);
                randomNumber = randomNumber + collecteRandomNumber;
            }
        }
        setCpuNbMystere(randomNumber);
        setCpuTabMystere(CpuNbMystere.split("(?<=.)"));
        /**
         * Décompte de nbChiffre
         */
        for (int i = 0; i < CpuTabMystere.length; i++) {
            nbChiffreCpu++;
        }
    }

    protected void defenseMode() throws IOException {
        /**
         * parametrages6
         */
        GetPropertyValues properties = new GetPropertyValues();
        setNbMaxEssais(Integer.parseInt(properties.getPropValues("nbMaxEssais")));
        setNombreEssais(Integer.parseInt(properties.getPropValues2("nombreEssais")));
        setLongueurCombinaison(Integer.parseInt(properties.getPropValues3("longueurCombinaison")));
        setNbChiffreJoueur(0);
        setEssaiRestant(getNbMaxEssais());
        setPremiereProposition("");
        do {
            LOGGER.info("Entre une combinaision de "+ getLongueurCombinaison()+ " chiffres");
            LOGGER.info("Entre ta combinaison secrète : ");
            setJoueurNbMystere(entree.next());
            LOGGER.info(JoueurNbMystere);
            while (getJoueurNbMystere().matches("^[a-zA-Z]*$")) {
                LOGGER.error("Tu ne dois mettre que des chiffres ");
                LOGGER.info("Entre une combinaision de "+ getLongueurCombinaison()+ "chiffres");
                setJoueurNbMystere(entree.next());
                LOGGER.info(JoueurNbMystere);
            }
            setJoueurTabMystere(JoueurNbMystere.split("(?<=.)"));
            /**
             * Décompte de nbChiffre pour donner un indice au joueur
             */
            for (int i = 0; i < JoueurTabMystere.length; i++) {
                nbChiffreJoueur++;
            }
            if (nbChiffreJoueur != getLongueurCombinaison()) {
                LOGGER.error("Tu dois entrer une combinaision de "+ getLongueurCombinaison()+ " chiffres");
                sortir = false;
                nbChiffreJoueur = 0;
            } else {
                sortir = true;
            }
        } while (!sortir);
        setPremierePropositionTab(new String[getNbChiffreJoueur()]);
    }

    protected void duelMode() throws IOException {
        /**
         * parametrages
         */
        GetPropertyValues properties = new GetPropertyValues();
        setNbMaxEssais(Integer.parseInt(properties.getPropValues("nbMaxEssais")));
        setNombreEssais(Integer.parseInt(properties.getPropValues2("nombreEssais")));
        setLongueurCombinaison(Integer.parseInt(properties.getPropValues3("longueurCombinaison")));
        setNbChiffreCpu(0);
        setNbChiffreJoueur(0);
        setPremiereProposition("");
        setEssaiRestantHumain(getNbMaxEssais());
        setEssaiRestantOrdi(getNbMaxEssais());


        /**
         * CPU
         */
        System.out.println("L'ordinateur saisi son nombre mystere");
        randomNumber = "";
        if (recherche){
            randomNumber = generator.generate(longueurCombinaison);}
        else if (mastermind){
            while (randomNumber.length()!=longueurCombinaison){
                generatorMastermind = (Integer.parseInt(properties.getPropValues5("chiffreMinUtilisable")) + random.nextInt((Integer.parseInt(properties.getPropValues6("chiffreMaxUtilisable"))-(Integer.parseInt(properties.getPropValues5("chiffreMinUtilisable"))))));
                collecteRandomNumber = String.valueOf(generatorMastermind);
                randomNumber = randomNumber + collecteRandomNumber;
            }
        }
        setCpuNbMystere(randomNumber);
        setCpuTabMystere(CpuNbMystere.split("(?<=.)"));
        for (int i = 0; i < CpuTabMystere.length; i++) {
            nbChiffreCpu++;
        }

        /**
         * player
         */
        do {
            LOGGER.info("Entre une combinaision de "+ getLongueurCombinaison()+ " chiffres");
            LOGGER.info("Entre ta combinaison secrète : ");
            setJoueurNbMystere(entree.next());
            LOGGER.info(JoueurNbMystere);
            while (getJoueurNbMystere().matches("^[a-zA-Z]*$")) {
                LOGGER.error("Tu ne dois mettre que des chiffres ");
                LOGGER.info("Entre une combinaision de "+ getLongueurCombinaison()+ "chiffres");
                setJoueurNbMystere(entree.next());
                LOGGER.info(JoueurNbMystere);
            }
            setJoueurTabMystere(JoueurNbMystere.split("(?<=.)"));
            /**
             * Décompte de nbChiffre pour donner un indice au joueur
             */
            for (int i = 0; i < JoueurTabMystere.length; i++) {
                nbChiffreJoueur++;
            }
            if (nbChiffreJoueur != getLongueurCombinaison()) {
                LOGGER.error("Tu dois entrer une combinaision de "+ getLongueurCombinaison()+ " chiffres");
                sortir = false;
                nbChiffreJoueur = 0;
            } else {
                sortir = true;
            }
        } while (!sortir);
        setPremierePropositionTab(new String[getNbChiffreJoueur()]);
    }
}