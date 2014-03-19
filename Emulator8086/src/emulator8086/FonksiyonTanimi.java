/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package emulator8086;

import java.util.ArrayList;

/**
 *
 * @author kadirtuna
 */
public class FonksiyonTanimi extends Line{
    public String fonksiyonAdi;
    public FonksiyonTanimi(String line, String fonksiyonAdi, int komutSatiri){
        super(line,komutSatiri);
        this.fonksiyonAdi = fonksiyonAdi;
    }
}
