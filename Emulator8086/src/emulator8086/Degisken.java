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

    public Degisken(int value) {
        tur = DegiskenTur.IMMEDIATE;
        this.value = value;
    }

    public Degisken(String reg) {
        tur = DegiskenTur.REGISTER;
        deger = reg;
    }
    public Degisken(String degiskenAdi, int index){
        tur = DegiskenTur.MEMORY;
        deger = degiskenAdi;
        value = index;
    }

    public enum DegiskenTur {
        REGISTER, //AX,BX gibi Register
        MEMORY, //DB DW türlerinde değişken
        IMMEDIATE // 12h gibi bir değer
    }

}
