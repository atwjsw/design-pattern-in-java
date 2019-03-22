package factory.exercise;

class Person {
    public int id;
    public String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class PersonFactory {
    private static int counter = 0;

    public static Person createPerson(String name) {
        return new Person(counter++, name);
    }
}

class Demo {
    public static void main(String[] args) {
        Person p1 = PersonFactory.createPerson("name1");
        Person p2 = PersonFactory.createPerson("name2");
        Person p3 = PersonFactory.createPerson("name3");
        System.out.println(p1);
        System.out.println(p3);
    }
}
