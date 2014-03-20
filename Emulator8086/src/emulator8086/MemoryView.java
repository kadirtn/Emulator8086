/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package emulator8086;

import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author kadirtuna
 */
public class MemoryView {
    private NewInteger[] memory;
    public MemoryView(int size){
        memory = new NewInteger[size];
        for(int i = 0;i < memory.length; i++)
            memory[i] = new NewInteger(4096+i, 0);
    }
    public int get(int index){
        return memory[index].value;
    }
    public void set(int index, int value){
        memory[index].value = value;
    }
    public NewInteger[] getList(){
        return memory;
    }
    
}
