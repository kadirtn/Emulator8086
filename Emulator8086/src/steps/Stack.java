/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package steps;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kadirtuna
 */
public class Stack {
    List<Integer[]>stackList = null;
    
    private static Stack stack = null;
    
    private Stack(){
        stackList = new ArrayList<Integer[]>();
    }
    public static Stack getStack(){
        if(stack == null)
            stack = new Stack();
        return stack;
    }
    public Integer[] pop(){
        Integer[] result = stackList.get(stackList.size()-1);
        stackList.remove(stackList.size()-1);
        return result;
    }
    public void push(Integer[] value){
        stackList.add(value);
    }
    
}
