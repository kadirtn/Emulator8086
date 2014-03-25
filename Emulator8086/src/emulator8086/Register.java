/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emulator8086;

import java.util.HashMap;

/**
 *
 * @author kadirtuna
 */
public class Register {

    HashMap<String, Integer> registers = new HashMap<String, Integer>();
    private static Register register = null;

    private Register() {
        registers.put("AL", 0);
        registers.put("AH", 0);
        registers.put("BL", 0);
        registers.put("BH", 0);
        registers.put("CL", 0);
        registers.put("CH", 0);
        registers.put("DL", 0);
        registers.put("DH", 0);
    }

    public static Register getRegister() {
        if (register == null) {
            register = new Register();
        }
        return register;
    }
    public Integer getValue(String reg){
        switch (reg) {
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
    public String getHexValue(String reg){
        String result = "";
        Integer value = getValue(reg);
        if(value == null)
            return "NULL";
        int val = value.intValue();
        while(val >= 16){
            int karakter = val % 16;
            result += getChar(karakter);
            val = (val-karakter)/16;
        }
        result += getChar(val);
        if(result.length() == 1)
            result+="0";
        if(result.endsWith("A") ||
                result.endsWith("B") ||
                result.endsWith("C") ||
                result.endsWith("D") ||
                result.endsWith("E"))
            result +="0";
        return new StringBuilder(result).reverse().toString()+"h";
        
    }
    public String getChar(int val){
        if(val < 10)
            return val+"";
        return ((char)(55+val))+"";
    }
    public void setValue(String reg, int value) throws Exception{
        switch (reg) {
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
                registers.put(reg,value);
                break;
        }
    }
}
