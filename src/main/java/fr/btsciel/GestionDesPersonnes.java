package fr.btsciel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;

public class GestionDesPersonnes {

    private static final Path path = Paths.get("contact.json");


    private static ArrayList<Person> person = new ArrayList<>();

    public static ArrayList<Person> getPerson() {
        return person;
    }

    public static void ecrirePerson() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lettreType.txt"));
        for (int i = 0; i < person.size() ; i++) {
            Person p = person.get(i);
            BufferedWriter bw = new BufferedWriter(new FileWriter("lettre" + p.getNom() + ".txt"));
            String line = br.readLine();
            bw.write(line);
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
    }
}
