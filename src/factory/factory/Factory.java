package factory.factory;

class Point {

    private double x;
    private double y;

    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static class Factory {
        public static Point newCartesionPoint(double x, double y) {
            return new Point(x, y);
        }

        public static Point newPolarPoint(double rho, double theta) {
            return new Point(rho * Math.cos(theta), rho * Math.sin(theta));
        }
    }
}

class Demo {
    public static void main(String[] args) {
        Point point = Point.Factory.newPolarPoint(1, Math.PI / 2);
        Point point1 = Point.Factory.newCartesionPoint(1, 2);
    }
}
