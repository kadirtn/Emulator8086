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
        if (tur == DegiskenTur.REGISTER && element.size == size) {
            Register.getRegister().setValue(deger, element.value);
        } else if (tur == DegiskenTur.MEMORY) {
            EmulatorFrame.variableMap.get(deger).setValue(value, element.value);
        } else if ((tur == DegiskenTur.REGISTER && element.size != size) || (tur == DegiskenTur.MEMORY && element.size != size)) {
            throw new Exception("Boyut Hatası.");
        } else {
            throw new Exception("Memory ye değer setlenemez.");
        }

    }

    public enum DegiskenTur {

        REGISTER, //AX,BX gibi Register
        MEMORY, //DB DW türlerinde değişken
        IMMEDIATE // 12h gibi bir değer
    }

}
