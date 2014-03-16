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
    private void addDegisken(Degisken degisken){
        degiskenList.add(degisken);
    }
    private void setKomut(String komut){
        this.komut = komut;
    }
    private String getKomut(){
        return komut;
    }
    private void setLine(String line){
        this.line = line;
    }
    private String getLine(){
        return line;
    }
    private List getDegiskenList(){
        return degiskenList;
    }
    
}
