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
    private DegiskenTur tur;
    private String deger;
    private int index;
    public Degisken(DegiskenTur gelenTur){
        tur = gelenTur;
        deger = "";
        index = -1;
    }
    public Degisken(DegiskenTur gelenTur, String deger){
        setTur(gelenTur);
        setDeger(deger);
        index = -1;
    }
    
    public Degisken(DegiskenTur gelenTur, String deger, int index){
        setTur(gelenTur);
        setDeger(deger);
        setIndex(index);
    }
    public DegiskenTur getTur(){
        return tur;
    }
    
    public void setTur(DegiskenTur tur){
        this.tur = tur;
    }
    
    public String getDeger(){
        return deger;
    }
    
    public void setDeger(String deger){
        this.deger= deger;
    }
    
    public int getIndex(){
        return index;
    }
    
    public void setIndex(int index){
        this.index= index;
    }
}
