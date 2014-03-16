/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package emulator8086;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PRowLeR
 */
public class Komut {
    private String line;// ADD AX, BX
    private String komut; //ADD
    private List<Degisken> degiskenList = null;// 0) AX  , 1)BX
    public Komut(String line, String komut){
        setLine(line);
        setKomut(komut);
        degiskenList = new ArrayList<Degisken>();
    }
    public void addDegisken(Degisken degisken){
        degiskenList.add(degisken);
    }
    public void setKomut(String komut){
        this.komut = komut;
    }
    public String getKomut(){
        return komut;
    }
    public void setLine(String line){
        this.line = line;
    }
    public String getLine(){
        return line;
    }
    public List getDegiskenList(){
        return degiskenList;
    }
    public String toString() {
        return line;
    }
    public void yazdir(){
        System.out.println("KOMUT: "+komut);
        for (int i = 0; i < degiskenList.size(); i++) {
			System.out.println((i+1)+". değişken: Türü"+degiskenList.get(i).getTur().toString()+ " Değeri:"+ degiskenList.get(i).getDeger());
		}
        System.out.println("-------");
    }
}
