/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package steps;

import emulator8086.Flag;
import emulator8086.Register;
import emulator8086.Word;
import java.util.HashMap;

/**
 *
 * @author kadirtuna
 */
class Memento {
    public Integer[] registers;
    public boolean[] flags;
    public HashMap<String,Integer[]> dbVariables;
    public HashMap<String,Word[]> dwVariables;
    public int satir;
    public Memento(int satir, HashMap<String,Integer[]>dbVariables, HashMap<String,Word[]> dwVariables){
        registers = new Integer[8];
        registers[0] = Register.getRegister().AL[0];
        registers[1] = Register.getRegister().AH[0];
        registers[2] = Register.getRegister().BL[0];
        registers[3] = Register.getRegister().BH[0];
        registers[4] = Register.getRegister().CL[0];
        registers[5] = Register.getRegister().CH[0];
        registers[6] = Register.getRegister().DL[0];
        registers[7] = Register.getRegister().DH[0];
        flags = new boolean[6];
        flags[0] = Flag.getFlag().CF;
        flags[1] = Flag.getFlag().ZF;
        flags[2] = Flag.getFlag().SF;
        flags[3] = Flag.getFlag().OF;
        flags[4] = Flag.getFlag().PF;
        flags[5] = Flag.getFlag().DF;
        this.satir = satir;
        this.dbVariables = dbVariables;
        this.dwVariables = dwVariables;
    }
}
