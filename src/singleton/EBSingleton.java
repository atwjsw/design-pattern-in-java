package singleton;

import java.io.*;

enum EBSingleton {

    INSTANCE;

    EBSingleton() {
        value = 42;
    };

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

class EbDemo {

    static void saveToFile(EBSingleton ebSingleton, String filename) throws IOException {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(filename);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            objectOutputStream.writeObject(ebSingleton);
        }
    }

    static EBSingleton readFromFile(String filename) throws Exception {
        try (
                FileInputStream fileInputStream = new FileInputStream(filename);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ) {
            return (EBSingleton)objectInputStream.readObject();
        }
    }

    // 1. reflection can break singleton
    // 2. serialization can break singleton
    public static void main(String[] args) throws Exception {
//        EBSingleton s1 = EBSingleton.INSTANCE;
//        s1.setValue(111);
//        System.out.println(s1.getValue());
        String filename = "temp";
//        saveToFile(s1, filename);

        EBSingleton s2 = readFromFile(filename);
//        System.out.println(s1 == s2);
        System.out.println(s2.getValue());
        s2.setValue(222);
//        System.out.println(s1.getValue());
        System.out.println(s2.getValue());
    }
}