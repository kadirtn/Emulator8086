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
public class Flag {

    public boolean CF;
    public boolean ZF;
    public boolean SF;
    public boolean OF;
    public boolean PF;
    public boolean DF;
    private static Flag flag = null;

    private Flag() {
        CF = false;
        ZF = false;
        SF = false;
        OF = false;
        PF = false;
        DF = false;
    }

    public static Flag getFlag() {
        if (flag == null) {
            flag = new Flag();
        }
        return flag;
    }
}
