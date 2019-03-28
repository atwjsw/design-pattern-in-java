package template.templatemethod;

abstract class Game {
    protected int currentPlayer;
    protected final int numberOfPlayers;

    protected Game(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public void run() {
        start();
        while (!haveWinner())
            takeTurn();
        System.out.println("Player " + getWinningPlayer() + " wins");
    }

    protected abstract void start();
    protected abstract boolean haveWinner();
    protected abstract void takeTurn();
    protected abstract int getWinningPlayer();
}

class Chess extends Game {

    private int maxTurns = 10;
    private int turn = 1;

    public Chess() {
        super(3);
    }

    @Override
    protected void start() {
        System.out.println("Staring a game of chess.");
    }

    @Override
    protected boolean haveWinner() {
        return turn == maxTurns;
    }

    @Override
    protected void takeTurn() {
        System.out.println("Turn " + (turn++) + " taken by player " + currentPlayer);
        currentPlayer = (currentPlayer + 1) % numberOfPlayers;
    }

    @Override
    protected int getWinningPlayer() {
        return 0;
    }
}

class Demo {
    public static void main(String[] args) {
        Chess chess = new Chess();
        chess.run();
    }
}