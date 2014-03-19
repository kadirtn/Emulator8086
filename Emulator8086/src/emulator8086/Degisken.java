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
    public int value;

    public Degisken(int value) {
        tur = DegiskenTur.VALUE;
        this.value = value;
    }

    public Degisken(String reg) {
        tur = DegiskenTur.REGISTER;
        deger = reg;
    }

    public enum DegiskenTur {
        REGISTER, //AX,BX gibi Register
        ATTRIBUTE, //DB DW türlerinde değişken
        VALUE // 12h gibi bir değer
    }

}
