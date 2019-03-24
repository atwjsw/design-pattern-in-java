package bridge.guice;

// Shape - > Circle, Square
// Rendering -> Vector, Raster

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

// Cartesian Product: CircleVectorRenderer, CircleRasterRenderer, SquareVectorRenderer, SquareRasterRender
interface Renderer {
    void renderCircle(float radius);
    void renderSquare(float side);
}

class VectorRenderer implements Renderer {

    @Override
    public void renderCircle(float radius) {
        System.out.println("Vector drawing a circle of radius " + radius);
    }

    @Override
    public void renderSquare(float side) {
        System.out.println("Vector drawing a sqaure with side " + side);
    }
}

class RasterRenderer implements Renderer {

    @Override
    public void renderCircle(float radius) {
        System.out.println("Raster drawing a circle of radius " + radius);
    }

    @Override
    public void renderSquare(float side) {
        System.out.println("Raster drawing a sqaure with side " + side);
    }
}

abstract class Shape {

    protected Renderer renderer;

    public Shape(Renderer renderer) {
        this.renderer = renderer;
    }

    abstract void draw();
    abstract void resize(float factor);
}

class Circle extends Shape {

    public int radius;

    @Inject
    public Circle(Renderer renderer) {
        super(renderer);
    }

    public Circle(Renderer renderer, int radius) {
        super(renderer);
        this.radius = radius;
    }

    @Override
    void draw() {
        renderer.renderCircle(radius);
    }

    @Override
    void resize(float factor) {
        radius *= factor;
    }
}

class Square extends Shape {

    public int side;

    public Square(Renderer renderer) {
        super(renderer);
    }

    public Square(Renderer renderer, int side) {
        super(renderer);
        this.side = side;
    }

    @Override
    void draw() {
        renderer.renderSquare(side);
    }

    @Override
    void resize(float factor) {
        side *= factor;
    }
}

class ShapeModule extends AbstractModule {

    @Override
    protected void configure() {
       bind(Renderer.class).to(VectorRenderer.class);
    }
}

class Demo {
//    public static void main(String[] args) {
//        VectorRenderer vectorRenderer = new VectorRenderer();
//        Circle circle = new Circle(vectorRenderer, 5);
//        circle.radius = 5;
//        circle.draw();
//        circle.resize(2);
//        circle.draw();
//        RasterRenderer rasterRenderer = new RasterRenderer();
//        Square c2 = new Square(rasterRenderer, 3);
//        c2.side = 3;
//        c2.draw();
//        c2.resize(3);
//        c2.draw();
//    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new ShapeModule());
        Circle circle = injector.getInstance(Circle.class);
        circle.radius = 5;
        circle.draw();
        circle.resize(2);
        circle.draw();
    }
}