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

    private Map<String, Object> m;

    private static final Path path = Paths.get("contact.json");

    private static ArrayList<Person> person = new ArrayList<>();

    public void ecrirePerson() throws IOException {
        for (Person value : person) {
            //map.put(,value.getAdherent());

            BufferedReader br = new BufferedReader(new FileReader("lettreType.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("lettre" + value.getNom() + ".txt"));
            while(br.ready()) {
                String line = br.readLine();
                bw.write(line);
                bw.newLine();
            }
            bw.close();
        }
    }

    private void lectureFichier() throws IOException {
        JsonElement tree = new JsonParser().parse(new FileReader("contact.json"));
        if (Files.exists(path)) {
            FileReader fr = new FileReader(path.toFile());
            Type type = new TypeToken<ArrayList<Person>>(){}.getType();
            Gson gson = new Gson();
            person = gson.fromJson(fr,type);
            for(JsonElement e : tree.getAsJsonArray()) {
                m = gson.fromJson(e, Map.class);
            }
            System.out.println(m.get("nom"));
        }else{
            throw new FileNotFoundException("file not found");
        }

    }

    public GestionDesPersonnes() throws IOException {
        lectureFichier();
        ecrirePerson();
    }
}
