package Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;

public class StorageVarForManagerFormul {
    static class  CustomMap {
        public String key;
        public Object value;
        public CustomMap(String key){
            this.key = key;
        }
    }

    private static ArrayList<CustomMap> storageVar = new ArrayList<>();

    public static Consumer<Object> updateValueOutGameVar;

    public StorageVarForManagerFormul() {
        File file = new File("GameVar.txt");
        BufferedReader br = null;
        String s = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader("GameVar.txt"));
            while ((sCurrentLine = br.readLine()) != null) {
                storageVar.add(new CustomMap(sCurrentLine));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static CustomMap getElementUseKey(String key){
        for(int i = 0; i < storageVar.size(); i++){
            if(key.equals(storageVar.get(i).key)){
                return storageVar.get(i);
            }
        }
        return null;
    }

    public static CustomMap getElementUseIndex(int index){
        return storageVar.get(index);
    }

    public static Object getValueOutGameVarUseIndex(int index) {
        return storageVar.get(index).value;
    }

    public static void setValueOutGameVarUseIndex(int index, Object value) {
        storageVar.get(index).value = value;
    }

    public static int getIndexPositionSearchKey(String key){
        for(int i = 0; i < storageVar.size(); i++){
            if(key.equals(storageVar.get(i).key)){
                return i;
            }
        }
        return -1;
    }
}
