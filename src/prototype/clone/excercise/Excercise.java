package prototype.clone.excercise;

class Point
{
    public int x, y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Point deepCopy() {
        return new Point(x, y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class Line
{
    public Point start, end;

    public Line(Point start, Point end)
    {
        this.start = start;
        this.end = end;
    }

    public Line deepCopy()
    {
        return new Line(start.deepCopy(), end.deepCopy());
    }

    @Override
    public String toString() {
        return "Line{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}

class Demo {
    public static void main(String[] args) {
        Line line = new Line(new Point(1, 1), new Point(2, 2));
        Line line2 = line.deepCopy();
        line2.start.x = 0;
        line2.end.x = 0;

        System.out.println(line);
        System.out.println(line2);
    }
}
