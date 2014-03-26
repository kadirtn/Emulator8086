/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package steps;

import emulator8086.Degisken;
import emulator8086.EmulatorFrame;
import emulator8086.Register;

/**
 *
 * @author gizem
 */
public class StackElement {
    public int size;
    private int value;

    public StackElement(int size, int value) {
        this.size = size;
        this.value = value;
    }
    public StackElement(int size, String binary) {
        this.size = size;
        this.value = Integer.parseInt(binary, 2);
    }
    public void setValue(int value){
        if(size == 0)
            this.value = value;
        else{
            int mod = 1;
            for(int i = 0; i < size;i++)
                mod *= 256;
            this.value = value % mod;
        }
    }
    public Integer getValue(){
        return new Integer(value);
    }
    public String getMostSignificantBit(){
        return ""+getBinaryDeger().charAt(0);
    }
    
    public String getBinaryDeger(){
        String result = "";
        int val = value;
        while(val >= 2){
            int karakter = val % 2;
            result += getChar(karakter);
            val = (val-karakter)/2;
        }
        result += getChar(val);
        if(size == 1)
            result = fillWithZero(8,result);
        if(size == 2)
            result = fillWithZero(16,result);
        return new StringBuilder(result).reverse().toString();
    }
    private String getChar(int val){
        if(val < 10)
            return val+"";
        return ((char)(55+val))+"";
    }
    private String fillWithZero(int lngth, String result) {
        while(result.length() < lngth){
            result+="0";
        }
        return result;
    }
}
