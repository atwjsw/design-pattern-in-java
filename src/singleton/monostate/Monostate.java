package singleton.monostate;

class CEO {

    private static String name;
    private static int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        CEO.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        CEO.age = age;
    }

    @Override
    public String toString() {
        return "CEO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class MonostateDemo {
    public static void main(String[] args) {
        CEO ceo = new CEO();
        ceo.setName("Adam Smith");
        ceo.setAge(55);
        System.out.println(ceo);

        CEO ceo2 = new CEO();
        System.out.println(ceo2);

        CEO ceo3 = new CEO();
        ceo3.setAge(50);
        System.out.println(ceo);
        System.out.println(ceo2);
        System.out.println(ceo3);
    }
}
