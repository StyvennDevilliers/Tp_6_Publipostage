package fr.btsciel;

public class Ihm {
    public static void main(String[] args) {
        try{
            new GestionDesPersonnes();
        }catch(Exception e){
            System.err.println("Erreur lors de la lecture ou l'écriture du fichier");
        }
    }

}
