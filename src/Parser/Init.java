package Parser;

import java.util.function.Consumer;

public class Init {
    public Init(long valueSingle, long countFormula, Consumer<Object> update){
        SettingsLoadConfig.valueSingle = valueSingle;
        SettingsLoadConfig.countFormula = countFormula;
        StorageVarForManagerFormul.updateValueOutGameVar = update;
        new StorageVarForManagerFormul();
        new LoadConfig();
    }
}
