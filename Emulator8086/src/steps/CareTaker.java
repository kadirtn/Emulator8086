/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package steps;

import emulator8086.Flag;
import emulator8086.Register;
import emulator8086.Word;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author kadirtuna
 */
public class CareTaker {
    private List<Memento> mementoList = new ArrayList<Memento>(); 
    public void kaydet(int satir, HashMap<String,Integer[]>dbVariables, HashMap<String,Word[]> dwVariables){
        mementoList.add(new Memento(satir,dbVariables, dwVariables));
    }
    public Memento get(int i){
        return mementoList.get(i);
    }
    public void load(int i){
        Memento memento = mementoList.get(i);
        
        Register.getRegister().AH[0] = memento.registers[0];
	Register.getRegister().AL[0] = memento.registers[1];
	Register.getRegister().BH[0] = memento.registers[2];
	Register.getRegister().BL[0] = memento.registers[3];
	Register.getRegister().CH[0] = memento.registers[4];
	Register.getRegister().CL[0] = memento.registers[5];
	Register.getRegister().DH[0] = memento.registers[6];
	Register.getRegister().DL[0] = memento.registers[7];
	Flag.getFlag().CF = memento.flags[0];
	Flag.getFlag().ZF = memento.flags[1];
	Flag.getFlag().SF = memento.flags[2];
	Flag.getFlag().OF = memento.flags[3];
	Flag.getFlag().PF = memento.flags[4];
	Flag.getFlag().DF = memento.flags[5];
        
    }
    public HashMap<String,Integer[]> loadDbVariables(int i){
        Memento memento = mementoList.get(i);
        return memento.dbVariables;
    }
    public HashMap<String,Word[]> loadDwVariables(int i){
        Memento memento = mementoList.get(i);
        return memento.dwVariables;
    }
}
