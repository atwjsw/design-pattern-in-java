package command.excercise;

import java.util.Arrays;
import java.util.List;

class Command
{
    enum Action
    {
        DEPOSIT, WITHDRAW
    }

    public Action action;
    public int amount;
    public boolean success;

    public Command(Action action, int amount)
    {
        this.action = action;
        this.amount = amount;
    }
}

class Account
{
    public int balance;

    public void process(Command c)
    {
        switch (c.action) {
            case DEPOSIT:
                c.success = deposit(c.amount);
                break;
            case WITHDRAW:
                c.success = withdraw(c.amount);
                break;
        }
    }

    private boolean deposit(int amount) {
        balance += amount;
        return true;
    }

    private boolean withdraw(int amount) {
        if (amount > balance)
            return false;
        balance -= amount;
        return true;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                '}';
    }
}

class Demo {
    public static void main(String[] args) {
        Account account = new Account();
        List<Command> commands = Arrays.asList(
                new Command(Command.Action.DEPOSIT, 100),
                new Command(Command.Action.DEPOSIT, 50),
                new Command(Command.Action.WITHDRAW, 80),
                new Command(Command.Action.WITHDRAW, 100),
                new Command(Command.Action.WITHDRAW, 50)
                );
        System.out.println(account);
        commands.forEach(command -> {
            account.process(command);
            System.out.println(account);
        });
    }


}