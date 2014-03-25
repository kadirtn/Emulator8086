/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package line;

/**
 *
 * @author kadirtuna
 */
public class Line {// Genel Asm satır sınıfı. 
    //Satır;
    //bir Komut olabilir(Komut extends Line)// ADD AX, BX
    //variable tanımı olabilir(Variable extends Line)// var1 db 1, 3, 5
    //fonksiyon tanımı olabilir(FonksiyonTanimi extends Line)// fonk1:
    public String line;
    public int komutSatiri;
    public Line(String line,int komutSatiri){
        this.line = line;
        this.komutSatiri = komutSatiri;
    }
    public String toString() {//Jlist içine eklenmiş Line nesneleri toString methodundan dönen değeri ekranda gösterilir
        return line;
    }
}
