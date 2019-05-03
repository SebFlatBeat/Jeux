package com.enedis.sebastien.Algo;

import com.enedis.sebastien.Jeux.Game;

import java.util.Arrays;

public class MastermindAlgo extends Game {

   public static String [] listeEntiereCombinaison;


    public static String[] getlisteEntiereCombinaison (String[] element, int longueurListe) {

        listeEntiereCombinaison = new String[(int)Math.pow(element.length, longueurListe)];

        if (longueurListe == 1){
            return element;
        }else{
            String [] sousListeEntiere = getlisteEntiereCombinaison(element, longueurListe-1);

            int arrayIndex = 0;

            for (int i = 0; i < element.length; i++) {
                for (int j = 0; j< sousListeEntiere.length; j++)
                {
                    listeEntiereCombinaison[arrayIndex] = element[i] + sousListeEntiere[j];
                    System.out.println(listeEntiereCombinaison.length);
                    arrayIndex++;
                }
            }
        }System.out.println(Arrays.toString(listeEntiereCombinaison));
        return listeEntiereCombinaison;
    }

    public static String[] getListeEntiereCombinaison() {
        return listeEntiereCombinaison;
    }
}
