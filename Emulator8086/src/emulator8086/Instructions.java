/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package emulator8086;

import emulator8086.Degisken.DegiskenTur;
import emulator8086.Memory.VariableType;
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
        else if(list.get(0).tur == DegiskenTur.REGISTER && list.get(1).tur == DegiskenTur.IMMEDIATE)
            Register.getRegister().setValue(list.get(0).deger, list.get(1).value);
        else if(list.get(0).tur == DegiskenTur.REGISTER && list.get(1).tur == DegiskenTur.MEMORY)
            Register.getRegister().setValue(list.get(0).deger,EmulatorFrame.variableMap.get(list.get(1).deger).getValue(list.get(1).value));
        else if(list.get(0).tur == DegiskenTur.MEMORY && list.get(1).tur == DegiskenTur.REGISTER)
            EmulatorFrame.variableMap.get(list.get(0).deger).setValue(list.get(0).value, Register.getRegister().getValue(list.get(1).deger));
        else if(list.get(0).tur == DegiskenTur.MEMORY && list.get(1).tur == DegiskenTur.IMMEDIATE)
            EmulatorFrame.variableMap.get(list.get(0).deger).setValue(list.get(0).value, list.get(1).value);
        else if(list.get(0).tur == DegiskenTur.MEMORY && list.get(1).tur == DegiskenTur.MEMORY)
            EmulatorFrame.variableMap.get(list.get(0).deger).setValue(list.get(0).value, list.get(1).value);
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
        if(degisken.tur == DegiskenTur.REGISTER)
            steps.Stack.getStack().push(Register.getRegister().getValue(degisken.deger));
        else if(degisken.tur == DegiskenTur.MEMORY)
            steps.Stack.getStack().push(EmulatorFrame.variableMap.get(degisken.deger).getValue(degisken.value));
        return ++satir;
    }
    public static int POP(int satir, Komut komut){
        Degisken degisken = (Degisken) komut.getDegiskenList().get(0);
        if(degisken.tur == DegiskenTur.REGISTER)
            Register.getRegister().setValue(degisken.deger, steps.Stack.getStack().pop());
        else if(degisken.tur == DegiskenTur.MEMORY)
            EmulatorFrame.variableMap.get(degisken.deger).setValue(satir, satir);
        return ++satir;
    }

    public static int ADC(int satir, Komut komut) {
        return ++satir;
    }

    public static int AND(int satir, Komut komut) {
        return ++satir;
    }

    public static int CLC(int satir, Komut komut) {
        return ++satir;
    }

    public static int CLD(int satir, Komut komut) {
        return -1;
    }

    public static int CMP(int satir, Komut komut) {
        return -1;
    }

    public static int DEC(int satir, Komut komut) {
        return -1;
    }

    public static int DIV(int satir, Komut komut) {
        return -1;
    }

    public static int HLT(int satir, Komut komut) {
        return -1;
    }

    public static int IDIV(int satir, Komut komut) {
        return -1;
    }

    public static int IMUL(int satir, Komut komut) {
        return -1;
    }

    public static int INC(int satir, Komut komut) {
        return -1;
    }

    public static int JA(int satir, Komut komut) {
        return -1;
    }

    public static int JAE(int satir, Komut komut) {
        return -1;
    }

    public static int JB(int satir, Komut komut) {
        return -1;
    }

    public static int JBE(int satir, Komut komut) {
        return -1;
    }

    public static int JE(int satir, Komut komut) {
        return -1;
    }

    public static int JG(int satir, Komut komut) {
        return -1;
    }

    public static int JGE(int satir, Komut komut) {
        return -1;
    }

    public static int JL(int satir, Komut komut) {
        return -1;
    }

    public static int JMP(int satir, Komut komut) {
        return -1;
    }

    public static int JLE(int satir, Komut komut) {
        return -1;
    }

    public static int JNE(int satir, Komut komut) {
        return -1;
    }

    public static int JNP(int satir, Komut komut) {
        return -1;
    }

    public static int JP(int satir, Komut komut) {
        return -1;
    }

    public static int JPO(int satir, Komut komut) {
        return -1;
    }

    public static int LEA(int satir, Komut komut) {
        return -1;
    }

    public static int LOOP(int satir, Komut komut) {
        return -1;
    }

    public static int MUL(int satir, Komut komut) {
        return -1;
    }

    public static int NEG(int satir, Komut komut) {
        return -1;
    }

    public static int NOP(int satir, Komut komut) {
        return -1;
    }

    public static int NOT(int satir, Komut komut) {
        return -1;
    }

    public static int OR(int satir, Komut komut) {
        return -1;
    }

    public static int XOR(int satir, Komut komut) {
        return -1;
    }

    public static int STD(int satir, Komut komut) {
        return -1;
    }

    public static int STC(int satir, Komut komut) {
        return -1;
    }

    public static int SHR(int satir, Komut komut) {
        return -1;
    }

    public static int SHL(int satir, Komut komut) {
        return -1;
    }

    public static int SBB(int satir, Komut komut) {
        return -1;
    }

    public static int ROR(int satir, Komut komut) {
        return -1;
    }

    public static int ROL(int satir, Komut komut) {
        return -1;
    }
    
    public boolean variableSizeControl(VariableType type, int value){
        return (type == VariableType.DB && value < 256) || (type == VariableType.DW && value < 256*256 - 1);
    }
}
