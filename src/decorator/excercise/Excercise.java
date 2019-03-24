package decorator.excercise;

class Bird
{
    public int age;

    public String fly()
    {
        return age < 10 ? "flying" : "too old";
    }
}

class Lizard
{
    public int age;

    public String crawl()
    {
        return (age > 1) ? "crawling" : "too young";
    }
}

class Dragon
{
    private Bird bird;
    private Lizard lizard;

    public Dragon(int age) {
        this.bird = new Bird();
        bird.age = age;
        this.lizard = new Lizard();
        lizard.age = age;
    }

    public void setAge(int age)
    {
        bird.age = age;
        lizard.age = age;

    }
    public String fly()
    {
        return this.bird.fly();
    }
    public String crawl()
    {
        return this.lizard.crawl();
    }
}

class Demo {

    public static void main(String[] args) {
        Dragon dragon = new Dragon(1);
        System.out.println(dragon.fly());
        System.out.println(dragon.crawl());
        dragon.setAge(5);
        System.out.println(dragon.fly());
        System.out.println(dragon.crawl());
        dragon.setAge(10);
        System.out.println(dragon.fly());
        System.out.println(dragon.crawl());
    }

}
