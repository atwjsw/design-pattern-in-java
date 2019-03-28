package momento.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Token
{
    public int value = 0;

    public Token(int value)
    {
        this.value = value;
    }
}

class Memento
{
    public List<Token> tokens = new ArrayList<>();
}

class TokenMachine
{
    public List<Token> tokens = new ArrayList<>();

    public Memento addToken(int value)
    {
        tokens.add(new Token(value));
        Memento m = new Memento();
        m.tokens = new ArrayList<>(tokens);
        return m;
    }

    public Memento addToken(Token token)
    {
       return addToken(token.value);
    }

    public void revert(Memento m)
    {
        tokens = m.tokens;
    }
}
