package composite.exercise;

import java.util.*;
import java.util.function.Consumer;

interface ValueContainer extends Iterable<Integer> {

    default int sum() {
        int sum  = 0;
        for (int value: this) {
            sum += value;
        }
        return sum;
    }
}

class SingleValue implements ValueContainer
{
    public int value;

    // please leave this constructor as-is
    public SingleValue(int value)
    {
        this.value = value;
    }

    @Override
    public Iterator<Integer> iterator() {
        return Collections.singleton(this.value).iterator();
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        action.accept(this.value);
    }

    @Override
    public Spliterator<Integer> spliterator() {
        return Collections.singleton(this.value).spliterator();
    }
}

class ManyValues extends ArrayList<Integer> implements ValueContainer
{
}


class MyList extends ArrayList<ValueContainer>
{
    // please leave this constructor as-is
    public MyList(Collection<? extends ValueContainer> c)
    {
        super(c);
    }

    public int sum()
    {
       int sum = 0;
       for (ValueContainer vc: this) {
           sum += vc.sum();
       }
       return sum;
    }
}

class Demo {

    public static void main(String[] args) {
        SingleValue s1 = new SingleValue(1);
        SingleValue s2 = new SingleValue(2);

        ManyValues manyValues = new ManyValues();
        manyValues.add(3);
        manyValues.add(4);
        manyValues.add(5);

        MyList myList = new MyList(Arrays.asList(s1, s2, manyValues));
        System.out.println(myList.sum());
    }
}
