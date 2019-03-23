package singleton.testability;

import com.google.common.collect.Iterables;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.Assert.assertEquals;

class SingletonDatabase implements Database {
    private Dictionary<String, Integer> capitals = new Hashtable<>();

    private static int instanceCount = 0;
    public static int getCount() { return instanceCount; }

    private SingletonDatabase() {
        instanceCount++;
        System.out.println("Initializing database");

        try {
//            File file = new File(SingletonDatabase.class.getProtectionDomain()
//                    .getCodeSource().getLocation().getPath());
//            Path fullPath = Paths.get(file.getPath(), "capitals.txt");
            Path fullPath = Paths.get("c://tmp", "capitals.txt");
            List<String> lines = Files.readAllLines(fullPath);

            Iterables.partition(lines, 2)
                    .forEach(kv -> capitals.put(
                            kv.get(0).trim(),
                            Integer.parseInt(kv.get(1))
                    ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final SingletonDatabase INSTANCE = new SingletonDatabase();

    public static SingletonDatabase getInstance() {
        return INSTANCE;
    }

    @Override
    public int getPopulatoion(String name) {
        return capitals.get(name);
    }
}

class SingletonRecordFinder {
    public int getTotalPopulcation(List<String> names) {
        int result = 0;
        for (String name: names)
            result +=SingletonDatabase.getInstance().getPopulatoion(name);
        return result;
    }
}

class Tests {

//    public static void main(String[] args) {
//        SingletonRecordFinder finder = new SingletonRecordFinder();
//        List<String> names = Arrays.asList("Seoul", "Mexico City");
//        int tp = finder.getTotalPopulcation(names);
////        assertEquals(1750000 + 1740000, tp);
//        System.out.println(tp);
//    }

//    public static void main(String[] args) {
//        SingletonDatabase singletonDatabase = SingletonDatabase.getInstance();
//        ConfigurableRecordFinder crf = new ConfigurableRecordFinder(singletonDatabase);
//        List<String> names = Arrays.asList("Seoul", "Mexico City");
//        int tp = crf.getTotalPopulcation(names);
//        System.out.println(tp);
//    }

    public static void main(String[] args) {
        Database testDatabase = new TestableDatabase();
        ConfigurableRecordFinder crf = new ConfigurableRecordFinder(testDatabase);
        List<String> names = Arrays.asList("aaa", "ccc");
        int tp = crf.getTotalPopulcation(names);
        System.out.println(tp); //4
    }
}

interface Database {
    int getPopulatoion(String name);
}

class TestableDatabase implements Database{

    private Dictionary<String, Integer> capitals = new Hashtable<>();

    public TestableDatabase() {
        capitals.put("aaa", 1);
        capitals.put("bbb", 2);
        capitals.put("ccc", 3);
    }

    @Override
    public int getPopulatoion(String name) {
        return capitals.get(name);
    }
}

class ConfigurableRecordFinder {

    private Database database;

    public ConfigurableRecordFinder(Database database) {
        this.database = database;
    }

    public int getTotalPopulcation(List<String> names) {
        int result = 0;
        for (String name: names)
            result += database.getPopulatoion(name);
        return result;
    }
}