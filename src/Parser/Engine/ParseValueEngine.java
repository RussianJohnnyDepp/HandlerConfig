package Parser.Engine;

public class ParseValueEngine {
    public Double value;
    public ParseValueEngine(String fileName) {
       String[] word = fileName.split(":");
        value = Double.parseDouble(word[1]);
    }
}
