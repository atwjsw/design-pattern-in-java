package iterator.binarytree.arraybackedproperties;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

class SimpleCreature {
    private int strength, agility, intelligence;

    public int max() {
        return Math.max(strength, Math.max(agility, intelligence));
    }

    public int sum() {
        return strength + agility + intelligence;
    }

    public double average() {
        return sum() / 3.0;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
}

class Creature implements Iterable<Integer>{

    private int[] stats = new int[3];

    public int getStrength() {
        return stats[0];
    }

    public void setStrength(int value) {
        stats[0] = value;
    }

    public int getAgility() {
        return stats[1];
    }

    public void setAgility(int value) {
        stats[1] = value;
    }

    public int getIntelligence() {
        return stats[2];
    }

    public void setIntelligence(int value) {
        stats[2] = value;
    }

    public int sum() {
        return IntStream.of(stats).sum();
    }

    public int max() {
        return IntStream.of(stats).max().getAsInt();
    }

    public double average() {
        return IntStream.of(stats).average().getAsDouble();
    }

    @Override
    public Iterator<Integer> iterator() {
        return IntStream.of(stats).iterator();
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        IntStream.of(stats).forEach(stat -> action.accept(stat));
    }

    @Override
    public Spliterator<Integer> spliterator() {
        return IntStream.of(stats).spliterator();
    }
}

class Demo {
    public static void main(String[] args) {
        Creature creature = new Creature();
        creature.setAgility(10);
        creature.setStrength(15);
        creature.setIntelligence(5);

        System.out.println(creature.sum());
        System.out.println(creature.max());
        System.out.println(creature.average());

        for (Integer stat: creature) {
            System.out.println(stat);
        }

        creature.forEach(stat -> System.out.println(stat));

        Iterator i = creature.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }

        Spliterator s = creature.spliterator();
        s.trySplit();
    }

}