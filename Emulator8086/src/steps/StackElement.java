/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package steps;

/**
 *
 * @author kadirtuna
 */
public class StackElement {
    public int size;
    private int value;

    public StackElement(int size, int value) {
        this.size = size;
        this.value = value;
    }
    public StackElement(int size, String binary) {
        this.size = size;
        this.value = Integer.parseInt(binary, 2);
    }
    public void setValue(int value){
        if(size == 0)
            this.value = value;
        else{
            int mod = 1;
            for(int i = 0; i < size;i++)
                mod *= 256;
            this.value = value % mod;
        }
    }
    public int getValue(){
        return value;
    }
}
