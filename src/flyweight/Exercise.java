package flyweight;

import java.util.*;

class Sentence
{
    private String plainText;
    private List<WordToken> tokens = new ArrayList<>();

    public Sentence(String plainText)
    {
        this.plainText = plainText;
    }

    public WordToken getWord(int index)
    {
        WordToken token = new WordToken(index);
        tokens.add(token);
        return token;
    }

    @Override
    public String toString()
    {
       StringBuilder sb = new StringBuilder();
       String[] words = plainText.split(" ");
       for (int i = 0; i < words.length; i++) {
           for (WordToken token : tokens) {
               if (i == token.index && token.capitalize) {
                   words[i] = words[i].toUpperCase();
                   break;
               }
           }
           sb.append(words[i]);
           if (i != words.length - 1) {
               sb.append(" ");
           }
       }
       return sb.toString();
    }

    class WordToken
    {
        private int index;

        public WordToken(int index) {
            this.index = index;
        }

        public boolean capitalize;
    }
}

class ExerciseDemo {
    public static void main(String[] args) {
        Sentence sentence = new Sentence("Hello world, Java Guru!");
        sentence.getWord(2).capitalize = true;
        System.out.println(sentence);

    }
}
