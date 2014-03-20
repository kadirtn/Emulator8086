/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package steps;

import emulator8086.Flag;
import emulator8086.Register;
import java.util.HashMap;

/**
 *
 * @author kadirtuna
 */
public class Memento {
    public HashMap<String,Integer> registers;
    public boolean[] flags;
    public int satir;
    public Memento(int satir){
        registers = new HashMap<String,Integer>();
        registers.put("AX", Register.getRegister().getValue("AX"));
        registers.put("BX", Register.getRegister().getValue("BX"));
        registers.put("CX", Register.getRegister().getValue("CX"));
        registers.put("DX", Register.getRegister().getValue("DX"));
        flags = new boolean[6];
        flags[0] = Flag.getFlag().CF;
        flags[1] = Flag.getFlag().ZF;
        flags[2] = Flag.getFlag().SF;
        flags[3] = Flag.getFlag().OF;
        flags[4] = Flag.getFlag().PF;
        flags[5] = Flag.getFlag().DF;
        this.satir = satir;
    }
}
