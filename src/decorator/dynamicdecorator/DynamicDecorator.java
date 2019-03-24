package decorator.dynamicdecorator;

interface Shape {
    String info();
}

class Circle implements Shape {

    private float radius;

    public Circle() {
    }

    public Circle(float radius) {
        this.radius = radius;
    }

    void resize(float factor) {
        radius *= factor;
    }

    @Override
    public String info() {
        return "A circle of radius " + radius;
    }
}

class Square implements Shape {

    private int side;

    public Square() {
    }

    public Square(int side) {
        this.side = side;
    }

    @Override
    public String info() {
        return "A square with side " + side;
    }
}

class ColorShape implements Shape {

    private Shape shape;
    private String color;

    public ColorShape(Shape shape, String color) {
        this.shape = shape;
        this.color = color;
    }

    @Override
    public String info() {
        return this.shape.info() + " with " + color + " color";
    }
}

class TransparentShape implements Shape{

    private Shape shape;
    private int transparency;

    public TransparentShape(Shape shape, int transparency) {
        this.shape = shape;
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return this.shape.info() + " with transprency of " + transparency + "%";
    }
}

class Demo {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        Shape square = new Square(10);
        System.out.println(circle.info());
        System.out.println(square.info());

        Shape blueCircle = new ColorShape(circle, "blue");
        Shape redSquare = new ColorShape(square, "red");
        System.out.println(blueCircle.info());
        System.out.println(redSquare.info());

        Shape transparentCircle = new TransparentShape(circle, 30);
        System.out.println(transparentCircle.info());
        Shape transparentBlueCircle = new TransparentShape(blueCircle, 50);
        Shape redTransparentCircle = new ColorShape(transparentCircle, "red");
        System.out.println(transparentBlueCircle.info());
        System.out.println(redTransparentCircle.info());
    }
}