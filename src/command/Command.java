package command;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class BankAccount {
    private int balance;
    private int overdraftLimit = - 500;

    public void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited " + amount + ", balance is now " + balance);
    }

    public boolean withdraw(int amount) {
        if (balance - amount >= overdraftLimit) {
            balance -= amount;
            System.out.println("Withdrew " + amount + ", balance is now " + balance);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                '}';
    }
}

interface Command {
    void call();
    void undo();
}

class BankAccountCommand implements Command {
    private Action action;
    private int amount;
    private BankAccount bankAccount;
    private boolean succeeded = true;

    public BankAccountCommand(BankAccount bankAccount, Action action, int amount) {
        this.action = action;
        this.amount = amount;
        this.bankAccount = bankAccount;
    }

    public enum Action {
        DEPOSIT, WITHDRAW
    }

    @Override
    public void call() {
        switch (action) {
            case DEPOSIT:
            bankAccount.deposit(amount);
            break;
            case WITHDRAW:
            succeeded = bankAccount.withdraw(amount);
            break;
        }
    }

    @Override
    public void undo() {
        if (!succeeded) return;
        switch (action) {
            case DEPOSIT:
                bankAccount.withdraw(amount);
                break;
            case WITHDRAW:
                bankAccount.deposit(amount);
                break;
        }
    }
}

class Demo {
    public static void main(String[] args) {
        BankAccount ba = new BankAccount();
        System.out.println(ba);
        List<Command> commands = Arrays.asList(
                new BankAccountCommand(ba, BankAccountCommand.Action.DEPOSIT, 100),
                new BankAccountCommand(ba, BankAccountCommand.Action.WITHDRAW, 50),
                new BankAccountCommand(ba, BankAccountCommand.Action.WITHDRAW, 1000));

        commands.forEach(command -> {
            command.call();
            System.out.println(ba);
        });

        Collections.reverse(commands);

        commands.forEach(command -> {
            command.undo();
            System.out.println(ba);
        });
    }
}
