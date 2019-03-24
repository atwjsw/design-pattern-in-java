package chainofresponsibility.exercise;

import java.util.ArrayList;
import java.util.List;

abstract class Creature
{
    public abstract int getAttack();
    public abstract int getDefense();
}

class Goblin extends Creature
{
    private Game game;
    private int baseAttack = 1;
    private int baseDefense = 1;

    public Goblin(Game game)
    {
        this.game = game;
//        this.game.creatures.add(this);
    }

    @Override
    public int getAttack()
    {
        for (Creature creature: game.creatures) {
            if (creature instanceof GoblinKing && creature != this)
                return baseAttack + 1;
        }
        return baseAttack;
    }

    @Override
    public int getDefense()
    {
        return baseDefense + game.creatures.size() - 1;
    }

    @Override
    public String toString() {
        return "Goblin{" +
                ", attack=" + getAttack() +
                ", defense=" + getDefense() +
                '}';
    }
}

class GoblinKing extends Goblin
{
    private int baseAttack = 3;
    private int baseDefense = 3;


    public GoblinKing(Game game) {
        super(game);
    }

    @Override
    public int getAttack() {
        return 3;
    }
}

enum Statistic
{
    ATTACK, DEFENSE
}

class Game
{
    public List<Creature> creatures = new ArrayList<>();
}

class Demo {
    public static void main(String[] args) {

        Game game = new Game();
//        System.out.println("g1 join...");
        Creature g1 = new Goblin(game);
//        System.out.println(g1);
//
//        System.out.println("g2 joing...");
//        Creature g2 = new Goblin(game);
//        System.out.println(g1);
//        System.out.println(g2);
//
//        System.out.println("g3 join...");
//        Creature g3 = new Goblin(game);
//        System.out.println(g1);
//        System.out.println(g2);
//        System.out.println(g3);
//
//        System.out.println("king join ...");
//        Creature king = new GoblinKing(game);
//        System.out.println(g1);
//        System.out.println(g2);
//        System.out.println(g3);
//        System.out.println(king);

//
//        System.out.println(g3);
//        System.out.println(king);
//
//        System.out.println("g4 join...");
//        Creature g4 = new Goblin(game);
//        System.out.println(g1);
//        System.out.println(g2);
//        System.out.println(g3);
//        System.out.println(g4);
//        System.out.println(king);


    }
}