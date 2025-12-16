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

    public static void ecrirePerson() throws IOException {
        for (Person value : person) {
            BufferedReader br = new BufferedReader(new FileReader("lettreType.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("lettre" + value.getNom() + ".txt"));
            for (int j = 0; j < 22; j++) {
                String line = br.readLine();
                if (j == 0) {
                    line = line.replace("[nom]", value.getNom()).replace("[prenom]", value.getPrenom());
                } else if (j == 1) {
                    line = line.replace("[adresse]", value.getAdresse());
                } else if (j == 2) {
                    line = line.replace("[codePostal]", value.getCodePostal()).replace("[ville]", value.getVille());
                } else if (j == 6) {
                    line = line.replace("[entreprise]", value.getEntreprise());
                } else if (j == 7) {
                    line = line.replace("[dateDuJour]", value.getDateDuJour()).replace("[ville]", value.getVille());
                } else if (j == 12) {
                    line = line.replace("[nom]", value.getNom()).replace("[prenom]", value.getPrenom());
                } else if (j == 14) {
                    line = line.replace("[adherent]", value.getAdherent());
                } else if (j == 15) {
                    line = line.replace("[contrat]", value.getContrat());
                } else if (j == 16) {
                    line = line.replace("[date]", value.getDate());
                } else if (j == 21) {
                    line = line.replace("[nom]", value.getNom()).replace("[prenom]", value.getPrenom());
                }
                bw.write(line);
                bw.newLine();
            }
            bw.close();
        }
    }

    private void lectureFichier() throws IOException {
        if (Files.exists(path)) {
            FileReader fr = new FileReader(path.toFile());
            Type type = new TypeToken<ArrayList<Person>>(){}.getType();
            Gson gson = new Gson();
            person = gson.fromJson(fr,type);
        }else{
            throw new FileNotFoundException("file not found");
        }
    }

    public GestionDesPersonnes() throws IOException {
        lectureFichier();
        ecrirePerson();
    }
}
