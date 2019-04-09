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
    protected String nbReponse;
    protected String [] tabReponse;
    protected String JoueurNbMystere;
    protected String[] JoueurTabMystere;
    protected String CpuNbMystere;
    protected String[] CpuTabMystere;
    protected String premiereProposition;
    protected String[] premierePropositionTab;
    protected String indice;
    protected String nextProposition;
    private int choixJoueur;
    protected boolean actifOrdi;
    protected boolean actifHumain;
    private int numJoueur;
    protected String personum;
    protected String humain;
    protected String nomHumain;
    protected String ordinateur;
    protected Game adversaire;

    protected RandomStringGenerator generator = new RandomStringGenerator.Builder()
            .withinRange('0','9').build();

    public String getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(String randomNumber) {
        this.randomNumber = randomNumber;
    }

    protected String randomNumber = generator.generate(4,10);

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



    protected void challengerMode () {
        /**
         * parametrages
         */
        setNbMaxEssais(10);
        setNbChiffreCpu(0);
        setNombreEssais(1);
        setEssaiRestant(getNbMaxEssais());
        setCpuNbMystere(randomNumber);
        setCpuTabMystere(CpuNbMystere.split("(?<=.)"));
        /**
         * Décompte de nbChiffre
         */
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
            System.out.println("Tu peux entrer une combinaision de 1 à 10 chiffres");
            System.out.println("Entre ta combinaison secrète : "); //Faire une limitation de l'intervalle
            setJoueurNbMystere(entree.next());
            while (getJoueurNbMystere().matches("^[a-zA-Z]*$")) {
                System.out.println("Tu ne dois mettre que des chiffres");
                System.out.println("Tu peux entrer une combinaision de 1 à 10 chiffres");
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
                System.out.println("Tu dois entrer une combinaision de 1 à 10 chiffres");
                sortir = false;
            }else{
                sortir = true;
            }
        }while (!sortir);
        setPremierePropositionTab(new String[getNbChiffreJoueur()]);

    }

    protected void duelMode () {
        /**
         * parametrages
         */
        setNbMaxEssais(10);
        setNbChiffreCpu(0);
        setNbChiffreJoueur(0);
        int numJoueur = 1;
        Game ordinateur = new Joueurs(numJoueur) {
        };
        numJoueur ++;
        Game humain = new Joueurs(numJoueur) {
        };
        ordinateur.setAdversaire(humain);
        humain.setAdversaire(ordinateur);
        System.out.println("Qui commence en premier ?");
        System.out.println("1 - Moi (l'ordinateur)");
        System.out.println("2 - Toi ("+nomHumain+")");
        System.out.println("Ta réponse : ");
        choixJoueur = entree.nextInt();
        if (choixJoueur == 1) {
            ordinateur.activeJoueur();
            actifOrdi = true;
        }else if (choixJoueur == 2) {
            humain.activeJoueur();
            actifHumain = true;
        }

        /**
         * CPU
         */
        System.out.println("L'ordinateur saisi son nombre mystere");
        setCpuNbMystere(randomNumber);
        setCpuTabMystere(CpuNbMystere.split("(?<=.)"));
        for (int i = 0; i < CpuTabMystere.length; i++) {
            nbChiffreCpu++;
        }

        /**
         * player
         */
        do {
            System.out.println("L'ordinateur a saisi sa combinsaison secrète.");
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


    public void setHumain(String humain) {
        this.humain = humain;
    }

    public void setOrdinateur(String ordinateur) {
        this.ordinateur = ordinateur;
    }

    public String getOrdinateur() {
        return ordinateur;
    }

    public Game getAdversaire() {
        return adversaire;
    }

    public void setAdversaire(Game adversaire) {
        this.adversaire = adversaire;
    }

    protected void activeJoueur () {
        if (JoueurTabMystere != tabReponse && actifHumain) {
            this.adversaire.activeJoueur();
        }else if (CpuTabMystere != tabReponse && actifOrdi)
            this.adversaire.activeJoueur();
    }
}


