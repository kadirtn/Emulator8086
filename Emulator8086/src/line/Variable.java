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
public class Variable extends Line {
    public String name;
    public int size;
    public Variable(String line, int boyut, String name, int satir) {
        super(line, satir);
        this.name = name;
        this.size = boyut;
    }
}
