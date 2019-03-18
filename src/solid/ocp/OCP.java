package solid.ocp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// OCP Open for extension, Close for modification, Specification
enum Color {
    RED, GREEN, BLUE
}

enum Size {
    SMALL, MEDIUM, LARGE, HUGE
}

class Product {
    public String name;
    public Color color;
    public Size size;

    public Product(String name, Color color, Size size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", color=" + color +
                ", size=" + size +
                '}';
    }
}

class ProduceFilter {
    public Stream<Product> filterByColor(List<Product> products, Color color) {
        return products.stream().filter(p -> p.color == color);
    };

    public Stream<Product> filterBySize(List<Product> products, Size size) {
        return products.stream().filter(p -> p.size == size);
    };
    public Stream<Product> filterByColorAndSize(List<Product> products, Color color, Size size) {
        return products.stream().filter(p -> p.color == color && p.size == size);
    };
}

interface Specification<T> {
    boolean isSatisfied(T item);
}

interface Filter<T> {
    Stream<T> filter(List<T> items, Specification<T> spec);
}

class BetterFilter implements Filter<Product> {

    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
        return items.stream().filter((p) -> spec.isSatisfied(p));
    }
}

class ColorSpecification implements Specification<Product> {

    private Color color;

    public ColorSpecification(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.color == color;
    }
}

class SizeSpecification implements Specification<Product> {

    private Size size;

    public SizeSpecification(Size size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.size == size;
    }
}

class AndSpecification implements Specification<Product> {

    private Specification<Product> specA, specB;

    public AndSpecification(Specification<Product> specA, Specification<Product> specB) {
        this.specA = specA;
        this.specB = specB;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return this.specA.isSatisfied(item) && this.specB.isSatisfied(item);
    }
}

class MultiAndSpecification implements Specification<Product> {
    private Specification<Product>[] specs;

    public MultiAndSpecification(Specification<Product> ...specs) {
        this.specs = specs;
    }

    @Override
    public boolean isSatisfied(Product item) {
        for (Specification<Product> spec: specs) {
            if (!spec.isSatisfied(item)) return false;
        }
        return true;
    }
}

class OrSpecification implements Specification<Product> {

    private Specification<Product> specA, specB;

    public OrSpecification(Specification<Product> specA, Specification<Product> specB) {
        this.specA = specA;
        this.specB = specB;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return this.specA.isSatisfied(item) || this.specB.isSatisfied(item);
    }
}

class Demo {
    public static void main(String[] args) {
        Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
        Product tree = new Product("Tree", Color.GREEN, Size.LARGE);
        Product house = new Product("House", Color.BLUE, Size.LARGE);

        List<Product> products = new ArrayList<>();
        products.add(apple);
        products.add(tree);
        products.add(house);

        ProduceFilter pf = new ProduceFilter();
        System.out.println("Green proudcts (old): ");
        pf.filterByColor(products, Color.GREEN).forEach(System.out::println);

        BetterFilter bf = new BetterFilter();
        System.out.println("Green proudcts (new): ");
        bf.filter(products, new ColorSpecification(Color.GREEN)).forEach(System.out::println);

        System.out.println("Blue and large proudcts (new): ");
        bf.filter(products, new AndSpecification(new ColorSpecification(Color.BLUE), new SizeSpecification(Size.LARGE))).forEach(System.out::println);

        System.out.println("Blue or small proudcts (new): ");
        bf.filter(products, new OrSpecification(new ColorSpecification(Color.BLUE), new SizeSpecification(Size.SMALL))).forEach(System.out::println);

        System.out.println("Green and large proudcts (new): ");
        bf.filter(products, new MultiAndSpecification(new ColorSpecification(Color.GREEN), new SizeSpecification(Size.LARGE))).forEach(System.out::println);

    }
}
