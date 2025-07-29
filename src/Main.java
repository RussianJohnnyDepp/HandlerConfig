import Parser.Init;
import Parser.Manager.ManagerFormul;
import Parser.StorageVarForManagerFormul;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


public class Main {


    public static void main(String[] args) throws Exception {

        new Init(1,1,object -> {
            StorageVarForManagerFormul.setValueOutGameVarUseIndex(StorageVarForManagerFormul.getIndexPositionSearchKey("Hp_player"),55);
        });

        StorageVarForManagerFormul.updateValueOutGameVar.accept(null);


        System.out.println(""+ManagerFormul.getFunctionAndRunUseKey("hunger_decay"));

}




}