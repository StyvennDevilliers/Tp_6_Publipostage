package fr.btsciel;


public class Ihm {
    public static void main(String[] args) {

        try{
            System.out.println("Tentative de creation des lettres...");
            new GestionDesPersonnes();
            System.out.println("Les lettres ont ete creer.");
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

}
