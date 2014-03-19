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
public class Line {
    public String line;
    public int komutSatiri;
    public Line(String line,int komutSatiri){
        this.line = line;
        this.komutSatiri = komutSatiri;
    }
    public String toString() {
        return line;
    }
}
