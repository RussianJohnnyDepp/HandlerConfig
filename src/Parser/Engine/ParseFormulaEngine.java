package Parser.Engine;

import Parser.StorageVarForManagerFormul;

import java.util.ArrayList;

public class ParseFormulaEngine {

    class ElementFunction {
        Object object;
        double result = 0;
        boolean isFunction = false;
        boolean isSpecialValue = false;
        boolean isValue = false;

        public ElementFunction(Object object, boolean isFunction, boolean isSpecialValue, boolean isValue) {
            this.object = object;
            this.isFunction = isFunction;
            this.isSpecialValue = isSpecialValue;
            this.isValue = isValue;
        }
    }

    private ArrayList<ElementFunction> elementFunctions = new ArrayList<>();
    private ArrayList<Character> operationSymbol = new ArrayList<>();

    private double engineCalculation(double one, double two, char operation) {
        if (operation == '+') {
            return one + two;
        } else if (operation == '-') {
            return one - two;
        } else if (operation == '*') {
            return one * two;
        } else if (operation == '/') {
            return one / two;
        }
        return 0;
    }

    public double runFunction() {
        ArrayList<Double> values = new ArrayList<>();

        for(ElementFunction elementFunction : elementFunctions){
            if(elementFunction.isFunction){
                values.add(((ParseFormulaEngine) elementFunction.object).runFunction());
            }else if(elementFunction.isValue){
                values.add((Double)elementFunction.object);
            }else if(elementFunction.isSpecialValue){

                values.add((double) ((int) StorageVarForManagerFormul.getValueOutGameVarUseIndex(((int)elementFunction.object))));
            }
        }

        for(int i = 0; i < operationSymbol.size(); i++){
            char ch = operationSymbol.get(i);
            if(ch == '*' || ch == '/'){
                values.set(i,engineCalculation(values.get(i),values.get(i+1),ch));
                values.remove(i+1);
                operationSymbol.remove(i);
            }
        }
        double result = 0;
        if(operationSymbol.size() == 0){
                result = values.get(0);
        }else {
                result = engineCalculation(values.getFirst(), values.get(1), operationSymbol.getFirst());

            for (int i = 1; i < operationSymbol.size(); i++) {
                result = engineCalculation(result, values.get(i + 1), operationSymbol.get(i));
            }
        }
        return  result;
    }



    public ParseFormulaEngine(String formulaInText) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(formulaInText);

        for (int i = 0; i < stringBuilder.length(); i++) {
            char ch = stringBuilder.charAt(i);
            int countFoundOpeningBracket = 0;
            int idLastClosingParenthesis = 0;
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                operationSymbol.add(ch);
                stringBuilder.setCharAt(i, ' ');
            } else if (ch == '(') {
                countFoundOpeningBracket++;
                for (int c = i + 1; c < stringBuilder.length(); c++) {
                    ch = stringBuilder.charAt(c);
                    if (ch == '(') {
                        countFoundOpeningBracket++;
                    } else if (ch == ')') {
                        countFoundOpeningBracket--;
                        if (countFoundOpeningBracket == 0) {
                            stringBuilder.deleteCharAt(i);
                            stringBuilder.deleteCharAt(c - 1);
                            i = c -2;
                            break;
                        }
                    }
                }
            }
        }

        String[] word = stringBuilder.toString().split(" ");
        for(String string : word){
            try{
                elementFunctions.add(new ElementFunction(Double.parseDouble(string),false,false,true));
            }catch (Exception e){
                int id = StorageVarForManagerFormul.getIndexPositionSearchKey(string);
                if(id != -1){
                    elementFunctions.add(new ElementFunction(id,false,true,false));
                }else{
                    elementFunctions.add(new ElementFunction(new ParseFormulaEngine(string),true,false,false));
                }
            }
        }

    }


}