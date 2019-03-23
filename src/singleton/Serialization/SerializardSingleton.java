package singleton.Serialization;

import java.io.*;

class BasicSingleton implements  Serializable{

    private BasicSingleton() {
    }

    private static final BasicSingleton INSTANCE
            = new BasicSingleton();

    public static BasicSingleton getInstance() {
        return INSTANCE;
    }

    private int value = 0;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    protected Object readResolve() {
        return INSTANCE;
    }
}



class Demo {

    static void saveToFile(BasicSingleton basicSingleton, String filename) throws IOException {
       try (
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            objectOutputStream.writeObject(basicSingleton);
        }
    }

    static BasicSingleton readFromFile(String filename) throws Exception {
        try (
                FileInputStream fileInputStream = new FileInputStream(filename);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                ) {
            return (BasicSingleton)objectInputStream.readObject();
        }
    }

    // 1. reflection can break singleton
    // 2. serialization can break singleton
    public static void main(String[] args) throws Exception {
        BasicSingleton s1 = BasicSingleton.getInstance();
        s1.setValue(111);
        System.out.println(s1.getValue());
        String filename = "temp";
        saveToFile(s1, filename);

        BasicSingleton s2 = readFromFile(filename);
        System.out.println(s1 == s2);
        System.out.println(s2.getValue());
        s2.setValue(222);
        System.out.println(s1.getValue());
        System.out.println(s2.getValue());
    }
}