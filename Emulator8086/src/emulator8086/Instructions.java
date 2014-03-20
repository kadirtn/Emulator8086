/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package emulator8086;

import emulator8086.Degisken.DegiskenTur;
import line.Komut;
import java.util.List;

/**
 *
 * @author kadirtuna
 */
public class Instructions {
    public static int MOV(int satir, Komut komut){
            List<Degisken> list = komut.getDegiskenList();
        if(list.get(0).tur == DegiskenTur.REGISTER && list.get(1).tur == DegiskenTur.REGISTER)
            Register.getRegister().setValue(list.get(0).deger, Register.getRegister().getValue(list.get(1).deger));
        else if(list.get(0).tur == DegiskenTur.REGISTER && list.get(1).tur == DegiskenTur.VALUE)
            Register.getRegister().setValue(list.get(0).deger, list.get(1).value);
        return ++satir;
    }
    public static int ADD(int satir, Komut komut){
        
        return ++satir;
    }
    public static int SUB(int satir, Komut komut){
        
        return ++satir;
    }
    public static int PUSH(int satir, Komut komut){
        Degisken degisken = (Degisken) komut.getDegiskenList().get(0);
        steps.Stack.getStack().push(degisken.value);
        return ++satir;
    }
    public static int POP(int satir, Komut komut){
        Degisken degisken = (Degisken) komut.getDegiskenList().get(0);
        Register.getRegister().setValue(degisken.deger, steps.Stack.getStack().pop());
        return ++satir;
    }
}
