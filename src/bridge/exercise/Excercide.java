package bridge.exercise;

abstract class Shape
{
    protected Renderer renderer;

    public Shape(Renderer renderer) {
        this.renderer = renderer;
    }

    public abstract String getName();

    public String toString() {
        return "Drawing " + getName() +  " as " + renderer.whatToRenderAs();
    }
}

interface Renderer {
    String whatToRenderAs();
}

class VectorRenderer implements Renderer {
    @Override
    public String whatToRenderAs() {
        return "lines";
    }
}

class RasterRenderer implements Renderer {
    @Override
    public String whatToRenderAs() {
        return "pixels";
    }
}

class Triangle extends Shape
{
    public Triangle(Renderer renderer) {
        super(renderer);
    }

    @Override
    public String getName()
    {
        return "Triangle";
    }
}

class Square extends Shape
{
    public Square(Renderer renderer) {
        super(renderer);
    }

    @Override
    public String getName()
    {
        return "Square";
    }
}

class Demo {
    public static void main(String[] args) {
        Renderer vr = new VectorRenderer();
        Square s1 = new Square(vr);
        System.out.println(s1.toString());

        Renderer rr = new RasterRenderer();
        Triangle t1 = new Triangle(rr);
        System.out.println(t1.toString());
    }
}
