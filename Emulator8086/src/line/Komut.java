/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package line;

import emulator8086.Degisken;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gizem
 */
public class Komut extends Line {
    public String komut; //ADD
    public int functionLine; // komut loop ise loop edilecek(gidilecek) fonksiyon tanımının satırı
    private List<Degisken> degiskenList = null;// komut tanımından sonra gelen değişkenlerin listesi 0) AX  , 1)BX
    public Komut(String line, String komut, int satir){
        super(line,satir);
        this.komut = komut;
        degiskenList = new ArrayList<Degisken>();
    }
    public void addDegisken(Degisken degisken){
        degiskenList.add(degisken);
    }
    public List getDegiskenList(){
        return degiskenList;
    }
    public void yazdir(){//outline da özeti görmek için
        System.out.println("KOMUT: "+komut);
        for (int i = 0; i < degiskenList.size(); i++) {
			System.out.println((i+1)+". değişken: Türü"+degiskenList.get(i).tur+ " Değeri:"+ degiskenList.get(i).deger);
		}
        System.out.println("-------");
    }
}
