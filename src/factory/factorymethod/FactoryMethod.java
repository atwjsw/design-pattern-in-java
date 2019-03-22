package factory.factorymethod;

class Point {

    private double x;
    private double y;

    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Point newCartesianPoint(double x, double y) {
        return new Point(x, y);
    }

    public static Point newPolarPoint(double rho, double theta) {
        return new Point(rho * Math.cos(theta), rho * Math.sin(theta));
    }
}

class Demo {

    Point p1 = Point.newCartesianPoint(1, 2);
    Point p2 = Point.newPolarPoint(5, Math.PI);

}
