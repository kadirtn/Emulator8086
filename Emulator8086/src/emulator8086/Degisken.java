/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emulator8086;

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
        if(reg.equals("AX") || reg.equals("BX") || reg.equals("CX") || reg.equals("DX"))
            size = 2;
        else
            size = 1;
    }
    public Degisken(String degiskenAdi, int boyut, int index){
        tur = DegiskenTur.MEMORY;
        deger = degiskenAdi;
        size = boyut;
        value = index;
    }

    public enum DegiskenTur {
        REGISTER, //AX,BX gibi Register
        MEMORY, //DB DW türlerinde değişken
        IMMEDIATE // 12h gibi bir değer
    }

}
