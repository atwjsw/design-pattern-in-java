package builder.excercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CodeBuilder {

    private String className;
//    private Map<String, String> fields = new HashMap<>();
    private List<Field> fields = new ArrayList<>();

    public CodeBuilder(String className) {
        this.className = className;
    }

    public CodeBuilder addField(String name, String type) {
        this.fields.add(new Field(type, name));
        return this;
    }

    @Override
    public String toString() {
        String newLine = System.lineSeparator();
        StringBuilder sb = new StringBuilder();
        sb.append("public class ");
        sb.append(className);
        sb.append(newLine);
        sb.append("{");
        sb.append(newLine);
        fields.forEach((f) -> {
            sb.append("  public ");
            sb.append(f.type);
            sb.append(" ");
            sb.append(f.name);
            sb.append(";");
            sb.append(newLine);
        });
        sb.append("}");
        return sb.toString();
    }

    class Field {
        public String type;
        public String name;

        public Field(String type, String name) {
            this.type = type;
            this.name = name;
        }
    }
}

class Demo {

    public static void main(String[] args) {
        CodeBuilder cb = new CodeBuilder("Person")
                .addField("name", "String")
                .addField("age", "int");
        System.out.println(cb);
    }

}
