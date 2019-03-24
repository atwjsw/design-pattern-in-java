package proxy.protectionproxy.exercise;

class Person
{
    private int age;

    public Person(int age)
    {
        this.age = age;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String drink() { return "drinking"; }
    public String drive() { return "driving"; }
    public String drinkAndDrive() { return "driving while drunk"; }
}

class ResponsiblePerson
{
    private Person person;

    public ResponsiblePerson(Person person)
    {
        this.person = person;
    }

    public String drink() {
        if (person.getAge() < 18) {
            return "too young";
        }
        return "drinking";
    }
    public String drive() {
        if (person.getAge() < 16) {
            return "too young";
        }
        return "driving";
    }
    public String drinkAndDrive() { return "dead"; }
}

class Demo {

    public static void main(String[] args) {
        Person p1 = new Person(18);
        ResponsiblePerson responsiblePerson = new ResponsiblePerson(p1);
        System.out.println(responsiblePerson.drink());
        System.out.println(responsiblePerson.drive());
        System.out.println(responsiblePerson.drinkAndDrive());

        Person p2 = new Person(17);
        ResponsiblePerson responsiblePerson2 = new ResponsiblePerson(p2);
        System.out.println(responsiblePerson2.drink());
        System.out.println(responsiblePerson2.drive());
        System.out.println(responsiblePerson2.drinkAndDrive());

        Person p3 = new Person(15);
        ResponsiblePerson responsiblePerson3 = new ResponsiblePerson(p3);
        System.out.println(responsiblePerson3.drink());
        System.out.println(responsiblePerson3.drive());
        System.out.println(responsiblePerson3.drinkAndDrive());

    }
}