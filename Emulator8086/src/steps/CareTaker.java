/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package steps;

import emulator8086.Flag;
import emulator8086.Register;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author kadirtuna
 */
public class CareTaker {
    private List<Memento> mementoList = new ArrayList<Memento>(); 
    public void kaydet(int satir){
        mementoList.add(new Memento(satir));
    }
    public Memento get(int i){
        return mementoList.get(i);
    }
    public void load(int i){
        Memento memento = mementoList.get(i);
        Register.getRegister().setValue("AX", memento.registers.get("AX"));
        Register.getRegister().setValue("BX", memento.registers.get("BX"));
        Register.getRegister().setValue("CX", memento.registers.get("CX"));
        Register.getRegister().setValue("DX", memento.registers.get("DX"));
	Flag.getFlag().CF = memento.flags[0];
	Flag.getFlag().ZF = memento.flags[1];
	Flag.getFlag().SF = memento.flags[2];
	Flag.getFlag().OF = memento.flags[3];
	Flag.getFlag().PF = memento.flags[4];
	Flag.getFlag().DF = memento.flags[5];
        
    }
    public int getSize(){
        return mementoList.size();
    }

}
