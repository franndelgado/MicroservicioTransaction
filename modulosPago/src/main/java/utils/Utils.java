package utils;

import com.accenture.modulosPago.entities.Transaction;

public final class Utils {

    public Utils() {
    }

    public static String generateRandomDigit(int cant){
        String generateNumber = "";
        for(int i = 0; i < cant; i++) {
            int newNumber = (int) (Math.random() * 10);
            generateNumber += String.valueOf(newNumber);
        }
        return generateNumber;
    }


    public static Boolean verifyNumber(String number){
        try {
            Double.parseDouble(number);
            return true;
        }catch (NumberFormatException e){
            e.getMessage();
            return false;
        }
    }

    /*public static Boolean verifyTwoDecimals(Double amount){
        String str = String.valueOf(amount);
        int index = str.indexOf("."); //10.000000
        int counter = 0;
        for(int i = index; i < str.length(); i++){
            counter++;
        }
        if(counter < 3 && counter > 1){
            return true;
        }else{
            return false;
        }

     */


    public static Boolean verifyTwoDecimals(Double amount){
        String str = String.valueOf(amount);
        int index = str.indexOf(".");
        int counter = 0;
        for(int i = index; i < str.length(); i++){
            counter++;
        }
        if(counter > 2){
            return true;
        }else{
            return false;
        }
    }
}