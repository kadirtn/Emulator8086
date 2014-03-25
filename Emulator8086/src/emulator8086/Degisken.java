/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emulator8086;

import steps.StackElement;

/**
 *
 * @author PRowLeR
 */
public class Degisken {

    public DegiskenTur tur;
    public String deger;
    public int value;//decimal değer
    public int size;//byte büyüklüğü 1-2 byte

    public Degisken(int value) {
        tur = DegiskenTur.IMMEDIATE;
        this.value = value;
        this.size = 0;
    }

    public Degisken(String reg) {
        tur = DegiskenTur.REGISTER;
        deger = reg;
        if (reg.equals("AX") || reg.equals("BX") || reg.equals("CX") || reg.equals("DX")) {
            size = 2;
        } else {
            size = 1;
        }
    }

    public Degisken(String degiskenAdi, int boyut, int index) {
        tur = DegiskenTur.MEMORY;
        deger = degiskenAdi;
        size = boyut;
        value = index;
    }

    public StackElement getDeger() {
        if (tur == DegiskenTur.REGISTER) {
            return new StackElement(size, Register.getRegister().getValue(deger));
        } else if (tur == DegiskenTur.MEMORY) {
            return new StackElement(size, EmulatorFrame.variableMap.get(deger).getValue(value));
        } else {
            return new StackElement(0, value);
        }
    }

    public void setDeger(StackElement element) throws Exception {
        if (tur == DegiskenTur.REGISTER && (element.size == size || element.size == 0)) {
            Register.getRegister().setValue(deger, element.getValue());
        } else if (tur == DegiskenTur.MEMORY && (element.size == size || element.size == 0)) {
            EmulatorFrame.variableMap.get(deger).setValue(value, element.getValue());
        } else if ((tur == DegiskenTur.REGISTER && element.size != size) || (tur == DegiskenTur.MEMORY && element.size != size)) {
            throw new Exception("Boyut Hatası.");
        } else {
            throw new Exception("değere değer setlenemez.");
        }

    }
    public String getHexDeger(){
        String result = "";
        int val = -1;
        if(tur == DegiskenTur.REGISTER)
            val = Register.getRegister().getValue(deger);
        else if(tur == DegiskenTur.MEMORY)
            val = EmulatorFrame.variableMap.get(deger).getValue(value);
        else
            val = value;
        while(val >= 16){
            int karakter = val % 16;
            result += getChar(karakter);
            val = (val-karakter)/16;
        }
        result += getChar(val);
        if(size == 1)
            fillWithZero(2,result);
        if(size == 2)
            fillWithZero(4,result);
        if(result.endsWith("A") ||
                result.endsWith("B") ||
                result.endsWith("C") ||
                result.endsWith("D") ||
                result.endsWith("E"))
            result +="0";
        return new StringBuilder(result).reverse().toString()+"h";
    }
    public String getBinaryDeger(){
        String result = "";
        int val = -1;
        if(tur == DegiskenTur.REGISTER)
            val = Register.getRegister().getValue(deger);
        else if(tur == DegiskenTur.MEMORY)
            val = EmulatorFrame.variableMap.get(deger).getValue(value);
        else
            val = value;
        while(val >= 2){
            int karakter = val % 2;
            result += getChar(karakter);
            val = (val-karakter)/2;
        }
        result += getChar(val);
        if(size == 1)
            fillWithZero(8,result);
        if(size == 2)
            fillWithZero(16,result);
        return new StringBuilder(result).reverse().toString();
    }
    private String getChar(int val){
        if(val < 10)
            return val+"";
        return ((char)(55+val))+"";
    }
    private void fillWithZero(int lngth, String result) {
        while(result.length() < lngth){
            result+="0";
        }
    }

    public enum DegiskenTur {

        REGISTER, //AX,BX gibi Register
        MEMORY, //DB DW türlerinde değişken
        IMMEDIATE // 12h gibi bir değer
    }

}
