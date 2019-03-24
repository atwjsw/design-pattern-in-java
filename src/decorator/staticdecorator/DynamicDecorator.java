package decorator.staticdecorator;

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

class ColorShape<T extends Shape> implements Shape {

    private Shape shape;
    private String color;

    public ColorShape(T shape, String color) {
        this.color = color;
        this.shape = shape;
    }

    @Override
    public String info() {
        return shape.info() + " has the color " + color;
    }
}

class TransparentShape<T extends Shape> implements Shape {

    private T shape;
    private int transparency;

    public TransparentShape(T shape, int transparency) {
        this.shape = shape;
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info() + " has the transparency " + transparency + "%";
    }
}

class Demo {
    public static void main(String[] args) {
        Circle circle = new Circle(5);
        Shape colorCircle = new ColorShape<Circle>(circle, "red");
        System.out.println(circle.info());
        System.out.println(colorCircle.info());

        Shape transparentCircle =
                new TransparentShape<ColorShape<Circle>>(new ColorShape<Circle>(new Circle(10), "red"), 50);
        System.out.println(transparentCircle.info());
    }
}