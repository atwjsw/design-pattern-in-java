package prototype.clone.copyconstructor;

class Address {
    public String streetAddress, city, country;

    public Address(String streetAddress, String city, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
    }

    // copy constructor
    public Address(Address other) {
        this(other.streetAddress, other.city, other.country);
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

class Employee {
    public String name;
    public Address address;

    public Employee(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Employee(Employee other) {
        this(other.name, new Address(other.address));
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

class Demo {

    public static void main(String[] args) {
        Employee john = new Employee("John", new Address("123 Wellington St", "Aurora", "Canada"));

        Employee chris = new Employee(john);
        chris.name = "Chris";
        chris.address.streetAddress = "234 William Graham Dr.";

        System.out.println(john);
        System.out.println(chris);
    }
}
