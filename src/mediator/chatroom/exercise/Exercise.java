package mediator.chatroom.exercise;

import java.util.ArrayList;
import java.util.List;

class Participant
{
    public int value;
    private Mediator mediator;

    public Participant(Mediator mediator)
    {
        this.mediator = mediator;
        this.mediator.add(this);
        value = 0;
    }

    public void say(int n)
    {
        mediator.broadcast(this, n);
    }

    @Override
    public String toString() {
        return "Participant{" +
                "value=" + value +
                '}';
    }
}

class Mediator
{
    private List<Participant> participants = new ArrayList<>();

    public void broadcast(Participant source, int n) {
        participants.forEach(p -> {
            if (p != source)
                p.value += n;
        });
    }

    public void add(Participant p) {
        participants.add(p);
    }
}

class Demo {
    public static void main(String[] args) {
        Mediator m = new Mediator();
        Participant p1 = new Participant(m);
        Participant p2 = new Participant(m);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println();

        p1.say(3);
        p2.say(2);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println();

        Participant p3 = new Participant(m);
        p3.say(1);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
    }
}