package fr.btsciel;


public class Ihm {
    public static void main(String[] args) {

        try{
            new GestionDesPersonnes();
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

}
