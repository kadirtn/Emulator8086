/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package emulator8086;

import java.util.List;

/**
 *
 * @author kadirtuna
 */
public class Memory {
    
    private VariableType type;
    private List<Integer> varList = null;
    
    public Memory(VariableType type, List<Integer> varList){
        this.type = type;
        this.varList = varList;
    }
    public Integer getValue(int index){
        return varList.get(index);
    }
    public void setValue(int index, int value){
        varList.set(index, value);
    }
    public void addValue(int value){
        varList.add(value);
    }
    public VariableType getType(){
        return type;
    }
    public enum VariableType{
        DB,DW
    }
    
}
