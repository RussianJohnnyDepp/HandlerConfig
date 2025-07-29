package Parser.Manager;

import Parser.Engine.ParseFormulaEngine;

import java.util.ArrayList;

public class ManagerFormul {
    class Formul {
        public String key;
        private ParseFormulaEngine parseFormulaEngine = null;
        public  Formul(String key, ParseFormulaEngine parseFormulaEngine){
            this.key = key;
            this.parseFormulaEngine = parseFormulaEngine;
        }

        public double run(){
            return parseFormulaEngine.runFunction();
        }

    }

   static private ArrayList<Formul> formulArrayList = new ArrayList<>();

    public ManagerFormul(ArrayList<String> values) throws Exception {
        for(String str : values){
            String[] words = str.split(":");
            formulArrayList.add(new Formul(words[0],new ParseFormulaEngine(words[1])));
        }
    }

    public static double getFunctionAndRunUseKey(String key){
       for(Formul formul : formulArrayList){
           if(formul.key.equals(key)){
               return formul.run();
           }
       }
       return -1;
    }

    public static double getFunctionAndRunUseIndex(int index){
       return formulArrayList.get(index).run();
    }
}
