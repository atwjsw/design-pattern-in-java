package singleton.lazy;

class LazySingleton {

    private LazySingleton() {
        System.out.println("initializing instance");
    }

    private static LazySingleton instance;

//    public static synchronized LazySingleton getInstance() {
//        if (instance == null) {
//            instance = new LazySingleton();
//        }
//        return instance;
//    }

    // double-checked locking
    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
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
        LazySingleton s1 = LazySingleton.getInstance();
        s1.setValue(123);
        System.out.println(s1.getValue());

        LazySingleton s2 = LazySingleton.getInstance();
        System.out.println(s1 == s2);
        System.out.println(s2.getValue());
        s2.setValue(222);
        System.out.println(s1.getValue());
        System.out.println(s2.getValue());
    }
}
