import java.util.Scanner;

public class Joueurs extends Game {
    static Scanner entree = new Scanner(System.in);
    protected String nomHumain;
    protected String ordinateur;
    protected String nomOrdinateur;
    protected  Joueurs adversaire;



    public Joueurs(int numJoueur) {
        if (numJoueur == 1) {
            nomOrdinateur = ("Terry");
            setOrdinateur(nomOrdinateur);
            System.out.println("Salut je suis ton adversaire l'ordinateur et je m'appelle " + ordinateur);
        } else if (numJoueur == 2) {
            System.out.println("Comment t'appelles-tu ?");
            nomHumain = entree.next();
            nomOrdinateur = ("Terry");
            setOrdinateur(nomOrdinateur);
            System.out.println("Qui commence en premier ?");
            System.out.println("1 - Moi (" + getOrdinateur() + ")");
            System.out.println("2 - Toi (" + nomHumain + ")");
            System.out.println("Ta réponse : ");
        }
    }

    public String getNomHumain() {
        return nomHumain;
    }

    public void setNomHumain(String nomHumain) {
        this.nomHumain = nomHumain;
    }

    public String getNomOrdinateur() {
        return nomOrdinateur;
    }

    public void setNomOrdinateur(String nomOrdinateur) {
        this.nomOrdinateur = nomOrdinateur;
    }

    public Joueurs getAdversaire() {
        return adversaire;
    }

    public void setAdversaire(Joueurs adversaire) {
        this.adversaire = adversaire;
    }

    public void setOrdinateur(String ordinateur) {
        this.ordinateur = ordinateur;
    }

    public String getOrdinateur() {
        return ordinateur;
    }



}