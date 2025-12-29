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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

public class GestionDesPersonnes {

    private Map<String, String> m;


    private static final Path path = Paths.get("contact.json");

    private static ArrayList<Person> person = new ArrayList<>();

    public void ecrireLettre() throws IOException {
        JsonElement tree = new JsonParser().parse(new FileReader(path.toString()));
        Gson gson = new Gson();

        for(JsonElement e : tree.getAsJsonArray()) {
            String lettre = lireModele("lettreType.txt");

            m = gson.fromJson(e, Map.class);

            for (Map.Entry<String, String> entry : m.entrySet()) {
                lettre = lettre.replace("[" + entry.getKey() + "]", entry.getValue());
            }
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            lettre = lettre.replace("[dateDuJour]", java.time.LocalDate.now().format(fmt));
            BufferedWriter bw = new BufferedWriter(new FileWriter("lettre_" + m.get("nom") + ".txt"));
            bw.write(lettre);
            bw.close();
        }
    }

    private String lireModele(String cheminModele) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(cheminModele)));
            String line;
            while (br.ready()) {
                line = br.readLine();
                sb.append(line).append("\n");
            }
        }catch (Exception e){
            throw new FileNotFoundException("file not found");
        }
        return sb.toString();
    }


    public GestionDesPersonnes() throws IOException {
        ecrireLettre();
    }
}
