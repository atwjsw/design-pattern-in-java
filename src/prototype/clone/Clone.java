package prototype.clone;

import java.util.Arrays;

class Address implements Cloneable{
    public String houseNumber;
    public String streetName;

    public Address(String houseNumber, String streetName) {
        this.houseNumber = houseNumber;
        this.streetName = streetName;
    }

    @Override
    public String toString() {
        return "Addresss{" +
                "houseNumber='" + houseNumber + '\'' +
                ", streetName='" + streetName + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Address(houseNumber, streetName);
    }
}

class Person implements Cloneable {
    String[] names;
    Address address;

    public Person(String[] names, Address address) {
        this.names = names;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "names=" + Arrays.toString(names) +
                ", address=" + address +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Person(names.clone(), (Address)address.clone());
//        return super.clone();
    }
}

class Demo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person john = new Person(
                new String[]{"John", "Smith"},
                new Address("123", "London Street")
        );

        Person jane = (Person)john.clone();
        jane.names[0] ="Jane";
        jane.address.houseNumber = "124";
        System.out.println(john);
        System.out.println(jane);
    }
}