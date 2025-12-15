package fr.btsciel;

import java.util.ArrayList;

public class Ihm {
    public static void main(String[] args) {
        try{
            new GestionDesPersonnes();
        }catch(Exception e){
            System.err.println("Erreur lors de la lecture du fichier");
        }
    }

}
