package solid.srp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

// SRP Single Responsibility Principle
class Journal {

    private final List<String> entries = new ArrayList<>();
    private static int count = 0;

    public void addEntry(String text) {
        entries.add("" + (++count) + ": " + text);
    }

    public void removeEntity(int index) {
        entries.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }
}

class Persistence {
    public void saveTOFile(Journal journal, String filename, boolean overwite) throws FileNotFoundException {
        if (overwite || new File(filename).exists()) {
            try (PrintStream out = new PrintStream(filename)) {
                out.println(journal.toString());
            }
        }
    }

    public Journal load(String filename) {
        return null;
    }

    public Journal load(URL url) {
        return null;
    }
}

class Demo {
    public static void main(String[] args) throws IOException {
        Journal j = new Journal();
        j.addEntry("I creied tofay");
        j.addEntry("I ate a bug");
        System.out.println(j);

        Persistence p = new Persistence();
        String filename = "c:\\tmp\\journal.txt";
        p.saveTOFile(j, filename, true);

        Runtime.getRuntime().exec("notepad.exe " + filename);
    }
}
