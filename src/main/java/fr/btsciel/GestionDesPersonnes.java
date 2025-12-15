package fr.btsciel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GestionDesPersonnes {

    private static final Path path = Paths.get("contact.json");

    private static ArrayList<Person> person = new ArrayList<>();

    public static ArrayList<Person> getPerson() {
        return person;
    }

    public static void ecrirePerson() throws IOException {
        for (int i = 0; i < person.size() ; i++) {
            BufferedReader br = new BufferedReader(new FileReader("lettreType.txt"));
            Person p = person.get(i);
            BufferedWriter bw = new BufferedWriter(new FileWriter("lettre" + p.getNom() + ".txt"));
            for (int j = 0; j < 22 ; j++) {
                String line = br.readLine();
                if(j == 0){
                    line = line.replace("[nom]", p.getNom()).replace("[prenom]", p.getPrenom());
                }else if(j == 1){
                    line = line.replace("[adresse]",p.getAdresse());
                }else if(j == 2){
                    line = line.replace("[codePostal]",p.getCodePostal()).replace("[ville]",p.getVille());
                }else if (j == 6){
                    line = line.replace("[entreprise]",p.getEntreprise());
                }else if  (j == 7){
                    line = line.replace("[dateDuJour]",p.getDateDuJour()).replace("[ville]",p.getVille());
                }else if (j == 12){
                    line = line.replace("[nom]", p.getNom()).replace("[prenom]", p.getPrenom());
                }else if (j == 14){
                    line = line.replace("[adherent]",p.getAdherent());
                }else if (j == 15){
                    line = line.replace("[contrat]",p.getContrat());
                }else if (j == 16){
                    line = line.replace("[date]",p.getDate());
                }else if  (j == 21){
                    line = line.replace("[nom]", p.getNom()).replace("[prenom]", p.getPrenom());
                }
                bw.write(line);
                bw.newLine();
            }
            bw.close();
        }
    }

    private void lectureFichier() throws IOException, ClassNotFoundException {
        if (Files.exists(path)) {
            FileReader fr = new FileReader(path.toFile());
            Type type = new TypeToken<ArrayList<Person>>(){}.getType();
            Gson gson = new Gson();
            person = gson.fromJson(fr,type);
        }else{
            throw new FileNotFoundException("file not found");
        }
    }

    public GestionDesPersonnes() throws IOException, ClassNotFoundException {
        lectureFichier();
        ecrirePerson();
    }
}
