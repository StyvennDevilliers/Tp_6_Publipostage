package fr.btsciel;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

public class GestionDesPersonnes {

    private Map<String, String> m;


    private static final Path path = Paths.get("contact.json");

    private static ArrayList<Person> person = new ArrayList<>();

    public void ecrirePerson() throws IOException {
        JsonElement tree = new JsonParser().parse(new FileReader("contact.json"));
        Gson gson = new Gson();
        StringBuilder modele = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader("lettreType.txt"));

        for(JsonElement e : tree.getAsJsonArray()) {
            m = gson.fromJson(e, Map.class);
            while(br.ready()) {
                String line = br.readLine();
                modele.append(line).append("\n");
            }
            br.close();

            String lettre = modele.toString();
            for (Map.Entry<String, String> entry : m.entrySet()) {
                lettre = lettre.replace("[" + entry.getKey() + "]", entry.getValue());
            }
            lettre = lettre.replace("[dateDuJour]", java.time.LocalDate.now().toString());
            BufferedWriter bw = new BufferedWriter(new FileWriter("lettre" + m.get("nom") + ".txt"));
            bw.write(lettre);
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
