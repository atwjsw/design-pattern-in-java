package interpreter.exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ExpressionProcessor
{
    public Map<Character, Integer> variables = new HashMap<>();

    public int calculate(String expression)
    {
        // todo
        return 0;
    }

    private List<Token> lex(String expression) {
        return null;
    }
}

class Token {
    public enum Type {
        INTEGER,
        PLUS,
        MINUS
    }

    public Type type;
    public String text;

    public Token(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return "`" + text + "`";
    }
}