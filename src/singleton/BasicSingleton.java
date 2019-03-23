package singleton;

class BasicSingleton {

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
}

class Demo {
    public static void main(String[] args) {
        BasicSingleton s1 = BasicSingleton.getInstance();
        s1.setValue(123);
        System.out.println(s1.getValue());

        BasicSingleton s2 = BasicSingleton.getInstance();
        System.out.println(s1 == s2);
        System.out.println(s2.getValue());
        s2.setValue(222);
        System.out.println(s1.getValue());
        System.out.println(s2.getValue());
    }
}
