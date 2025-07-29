package Parser.Manager;

import java.util.ArrayList;

public class ManagerSingleValue {
    class SingleValue {
        public String key;
        public String value;

        public SingleValue(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    public static ArrayList<SingleValue> singleValues = new ArrayList<>();

    public ManagerSingleValue(ArrayList<String> singleValues){
     for(String str : singleValues){
         String[] words = str.split(":");
         ManagerSingleValue.singleValues.add(new SingleValue(words[0],words[1]));
     }
    }

    public static SingleValue getSingleValueUseKey(String key){
        for(SingleValue singleValue : singleValues){
            if(singleValue.key.equals(key)){
                return singleValue;
            }
        }
        return null;
    }

    public static SingleValue getSingleValueUseIndex(int index){
        return singleValues.get(index);
    }
}
