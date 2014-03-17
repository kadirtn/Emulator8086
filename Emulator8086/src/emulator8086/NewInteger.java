/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package emulator8086;

/**
 *
 * @author kadirtuna
 */
public class NewInteger{
    int value = 0;
    int address = 0;

    public NewInteger(int address, int value) {
        this.address = address;
        this.value = value;
    }
    public String toString(){
        return hexValue(address)+": "+hexValue(value)+"h "+String.format("%03d", value)+" "+((char)value);
    }
    public String hexValue(int val){
        String result = "";
        while(val >= 16){
            int karakter = val % 16;
            result += getChar(karakter);
            val = (val-karakter)/16;
        }
        result += getChar(val);
        return new StringBuilder(result).reverse().toString();
    }
    public String getChar(int val){
        if(val < 10)
            return val+"";
        return ((char)(55+val))+"";
    }
    
}
