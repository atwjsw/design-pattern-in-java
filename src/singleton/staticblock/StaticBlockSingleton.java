package singleton.staticblock;

import java.io.File;

class StaticBlockSingleton {

    private StaticBlockSingleton() throws Exception{
        System.out.println("Single is initializing");
//        File.createTempFile(".", ".");
    }

    private static StaticBlockSingleton instance;

    static {
        try {
            instance = new StaticBlockSingleton();
        } catch (Exception e) {
            System.out.println("failed to create singleton");
        }
    }

    public static  StaticBlockSingleton getInstance() {
        return instance;
    }

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

class Demo {
    public static void main(String[] args) {
        StaticBlockSingleton s1 = StaticBlockSingleton.getInstance();
        s1.setValue(123);
        System.out.println(s1.getValue());

        StaticBlockSingleton s2 = StaticBlockSingleton.getInstance();
        System.out.println(s1 == s2);
        System.out.println(s2.getValue());
        s2.setValue(222);
        System.out.println(s1.getValue());
        System.out.println(s2.getValue());
    }
}
