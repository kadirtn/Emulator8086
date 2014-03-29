/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emulator8086;

import java.util.HashMap;

/**
 *
 * @author gizem
 */
public class Register {

    HashMap<String, Integer> registers = new HashMap<String, Integer>();
    private static Register register = null;

    private Register() {//ilk yaratılışta register tanımları yapılıyor
        registers.put("AL", 0);
        registers.put("AH", 0);
        registers.put("BL", 0);
        registers.put("BH", 0);
        registers.put("CL", 0);
        registers.put("CH", 0);
        registers.put("DL", 0);
        registers.put("DH", 0);
    }

    public static Register getRegister() {//her çağrıldığında aynı register nesnesi döndürülüyor
        if (register == null) {
            register = new Register();
        }
        return register;
    }
    public Integer getValue(String reg){//parametre olarak yollanan register değeri decimal olarak döndürülüyor
        switch (reg.toUpperCase()) {
            case "AX":
                return registers.get("AL") + registers.get("AH") * 256;
            case "BX":
                return registers.get("BL") + registers.get("BH") * 256;
            case "CX":
                return registers.get("CL") + registers.get("CH") * 256;
            case "DX":
                return registers.get("DL") + registers.get("DH") * 256;
            default:
                return registers.get(reg);
        }
    }
    public String getHexValue(String reg){//parametre olarak yollanan register değeri hexadecimal olarak döndürülüyor
        String result = "";
        Integer value = getValue(reg.toUpperCase());
        if(value == null)
            return "NULL";
        int val = value.intValue();
        while(val >= 16){
            int karakter = val % 16;
            result += getChar(karakter);
            val = (val-karakter)/16;
        }
        result += getChar(val);
        if(reg.toUpperCase().contains("X"))
            result = fillWithZero(4,result);
        else
            result = fillWithZero(2,result);
        return new StringBuilder(result).reverse().toString()+"h";
        
    }
    public String getBinaryValue(String reg){//parametre olarak yollanan register değeri binary olarak döndürülüyor
        String result = "";
        Integer value = getValue(reg.toUpperCase());
        if(value == null)
            return "NULL";
        int val = value.intValue();
        while(val >= 2){
            int karakter = val % 2;
            result += getChar(karakter);
            val = (val-karakter)/2;
        }
        result += getChar(val);
        if(reg.toUpperCase().contains("X"))
            result = fillWithZero(16,result);
        else
            result = fillWithZero(8,result);
        return new StringBuilder(result).reverse().toString()+"h";
        
    }
    private String getChar(int val){
        if(val < 10)
            return val+"";
        return ((char)(55+val))+"";
    }
    private String fillWithZero(int lngth, String result) {//lngth değerinde girilen kadar sıfırla tamamlanır
        //örnek:
        //lngth = 8  result = 11010
        //return edilen değer 11010000
        while(result.length() < lngth){
            result+="0";
        }
        return result;
    }
    public void setValue(String reg, int value) throws Exception{//reg değeri olarak yollanan register a value alanındaki değer set edilir.
        switch (reg.toUpperCase()) {
            case "AX":
                if(value >= 256 * 256)
                    throw new Exception("Boyut Hatası!");
                registers.put("AL", value %256);
                registers.put("AH", (value - value%256) / 256);
                break;
            case "BX":
                if(value >= 256 * 256)
                    throw new Exception("Boyut Hatası!");
                registers.put("BL", value %256);
                registers.put("BH", (value - value%256) / 256);
                break;
            case "CX":
                if(value >= 256 * 256)
                    throw new Exception("Boyut Hatası!");
                registers.put("CL", value %256);
                registers.put("CH", (value - value%256) / 256);
                break;
            case "DX":
                if(value >= 256 * 256)
                    throw new Exception("Boyut Hatası!");
                registers.put("DL", value %256);
                registers.put("DH", (value - value%256) / 256);
                break;
            default:
                if(value >= 256)
                    throw new Exception("Boyut Hatası!");
                registers.put(reg.toUpperCase(),value);
                break;
        }
    }
}
