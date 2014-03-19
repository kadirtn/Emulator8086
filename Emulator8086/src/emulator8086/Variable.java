/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emulator8086;

/**
 *
 * @author kadirtuna
 */
class Variable extends Line {
    public String name;
    public Variable(String line, String name, int satir) {
        super(line, satir);
        this.name = name;
    }
}
