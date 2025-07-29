package Parser;

import Parser.Manager.ManagerFormul;
import Parser.Manager.ManagerSingleValue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadConfig {
    public LoadConfig(){
        BufferedReader br = null;
        String s = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader("Config.txt"));
            ArrayList<String> singleValue = new ArrayList<>();
            ArrayList<String> formuls = new ArrayList<>();
            for(int i = 0; i < SettingsLoadConfig.valueSingle; i++){
                  singleValue.add(br.readLine());
            }

            for(int i = 0; i < SettingsLoadConfig.countFormula; i++){
                formuls.add(br.readLine());
            }

            new ManagerFormul(formuls);
            new ManagerSingleValue(singleValue);

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
}
