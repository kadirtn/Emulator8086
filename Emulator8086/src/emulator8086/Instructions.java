/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emulator8086;

import emulator8086.Degisken.DegiskenTur;
import line.Komut;
import java.util.List;
import steps.Stack;
import steps.StackElement;

/**
 *
 * @author kadirtuna
 */
public class Instructions {

    public static int MOV(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        Degisken src = list.get(1);
        dest.setDeger(src.getDeger());
        return ++satir;
    }

    public static int ADD(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        Degisken src = list.get(1);
        int result = dest.getDeger().getValue() + src.getDeger().getValue();
        dest.setDeger(new StackElement(dest.size,setFlagStatesForAdd(dest.size, result)));
        Flag.getFlag().PF = (dest.getBinaryDeger().length() - dest.getBinaryDeger().replace("1", "").length()) % 2 == 1;
        return ++satir;
    }
    public static int SUB(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        Degisken src = list.get(1);
        int result = dest.getDeger().getValue() - src.getDeger().getValue();
        dest.setDeger(new StackElement(dest.size,setFlagStatesForAdd(dest.size, result)));
        Flag.getFlag().PF = (dest.getBinaryDeger().length() - dest.getBinaryDeger().replace("1", "").length()) % 2 == 1;
        return ++satir;
    }

    public static int PUSH(int satir, Komut komut) throws Exception {
        Degisken degisken = (Degisken) komut.getDegiskenList().get(0);
        if (degisken.tur == DegiskenTur.REGISTER) {
            Stack.getStack().push(degisken.getDeger());
        } else if (degisken.tur == DegiskenTur.MEMORY) {
            Stack.getStack().push(degisken.getDeger());
        } else {
            throw new Exception("Push edilen değer register veya memory olmalı");
        }
        return ++satir;
    }

    public static int POP(int satir, Komut komut) throws Exception {
        Degisken dest = (Degisken) komut.getDegiskenList().get(0);
        StackElement element = Stack.getStack().pop();
        if(dest.size < 2)
            throw new Exception("Pop edilen destination boyutu word(2 byte) olmalı.");
        dest.setDeger(element);
        return ++satir;
    }

    public static int ADC(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        Degisken src = list.get(1);
        int result = dest.getDeger().getValue() + src.getDeger().getValue() + (Flag.getFlag().CF ? 1 : 0);
        dest.setDeger(new StackElement(dest.size,setFlagStatesForAdd(dest.size, result)));
        Flag.getFlag().PF = (dest.getBinaryDeger().length() - dest.getBinaryDeger().replace("1", "").length()) % 2 == 1;
        return ++satir;
    }

    public static int AND(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        int result;
        int operand1, operand2;

        //kararsızlık var kontrol edilmeli
        if (list.get(0).tur == DegiskenTur.REGISTER && list.get(1).tur == DegiskenTur.REGISTER) {
            operand1 = Register.getRegister().getValue(list.get(0).deger);
            operand2 = Register.getRegister().getValue(list.get(1).deger);

            if (operand1 == 1 && operand2 == 1) {
                result = 1;
            } else {
                result = 0;
            }
            Register.getRegister().setValue(list.get(0).deger, result);
        } else if (list.get(0).tur == DegiskenTur.REGISTER && list.get(1).tur == DegiskenTur.IMMEDIATE) {
            operand1 = Register.getRegister().getValue(list.get(0).deger);
            operand2 = list.get(1).value;

            if (operand1 == 1 && operand2 == 1) {
                result = 1;
            } else {
                result = 0;
            }
            Register.getRegister().setValue(list.get(0).deger, result);

        } else if (list.get(0).tur == DegiskenTur.REGISTER && list.get(1).tur == DegiskenTur.MEMORY) {
            result = Register.getRegister().getValue(list.get(0).deger) + EmulatorFrame.variableMap.get(list.get(1).deger).getValue(list.get(1).value);
            Register.getRegister().setValue(list.get(0).deger, setFlagStatesForAdd(list.get(0).size, result));
        } else if (list.get(0).tur == DegiskenTur.MEMORY && list.get(1).tur == DegiskenTur.REGISTER) {
            result = EmulatorFrame.variableMap.get(list.get(0).deger).getValue(list.get(0).value) + Register.getRegister().getValue(list.get(1).deger);
            EmulatorFrame.variableMap.get(list.get(0).deger).setValue(list.get(0).value, setFlagStatesForAdd(list.get(0).size, result));
        } else if (list.get(0).tur == DegiskenTur.MEMORY && list.get(1).tur == DegiskenTur.IMMEDIATE) {
            result = EmulatorFrame.variableMap.get(list.get(0).deger).getValue(list.get(0).value) + list.get(1).value;
            EmulatorFrame.variableMap.get(list.get(0).deger).setValue(list.get(0).value, setFlagStatesForAdd(list.get(0).size, result));
        } else if (list.get(0).tur == DegiskenTur.MEMORY && list.get(1).tur == DegiskenTur.MEMORY) {
            result = EmulatorFrame.variableMap.get(list.get(0).deger).getValue(list.get(0).value) + list.get(1).value;
            EmulatorFrame.variableMap.get(list.get(0).deger).setValue(list.get(0).value, setFlagStatesForAdd(list.get(0).size, result));
        }
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

    public static int DEC(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        StackElement result = dest.getDeger();
        result.setValue(result.getValue() -1);
        
        dest.setDeger(result);
        Flag.getFlag().ZF = (result.getValue() == 0);
        Flag.getFlag().PF = (dest.getBinaryDeger().length() - dest.getBinaryDeger().replace("1", "").length()) % 2 == 1;
        return ++satir;
    }

    public static int DIV(int satir, Komut komut) {
        return -1;
    }

    public static int HLT(int satir, Komut komut) {
        return -1;//-1 = programı sonlandır
    }

    public static int IDIV(int satir, Komut komut) {
        return -1;
    }

    public static int IMUL(int satir, Komut komut) {
        return -1;
    }

    public static int INC(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        StackElement result = dest.getDeger();
        result.setValue(result.getValue() +1);
        
        dest.setDeger(result);
        Flag.getFlag().ZF = (result.getValue() == 0);
        Flag.getFlag().PF = (dest.getBinaryDeger().length() - dest.getBinaryDeger().replace("1", "").length()) % 2 == 1;
        return ++satir;
    }

    public static int JA(int satir, Komut komut) {
        if(!Flag.getFlag().CF && !Flag.getFlag().ZF)
            return komut.functionLine;
        return ++satir;
    }

    public static int JAE(int satir, Komut komut) {
        if(Flag.getFlag().CF)
            return komut.functionLine;
        return ++satir;
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
        return komut.functionLine;
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

    public static int LOOP(int satir, Komut komut) throws Exception {
        Register.getRegister().setValue("CX", Register.getRegister().getValue("CX") - 1);
        if(Register.getRegister().getValue("CX") != 0)
            return komut.functionLine;
        return ++satir;
    }

    public static int MUL(int satir, Komut komut) {
        return -1;
    }

    public static int NEG(int satir, Komut komut) throws Exception {
        //sayının negatifi
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        StackElement result = dest.getDeger();
  
        if(dest.size == 1){
            result.setValue(256 - result.getValue());
        }
        if(dest.size == 2){
            result.setValue(65536 - result.getValue());
        }
        dest.setDeger(result);
        Flag.getFlag().ZF = (result.getValue() == 0);
        Flag.getFlag().PF = (dest.getBinaryDeger().length() - dest.getBinaryDeger().replace("1", "").length()) % 2 == 1;
        return ++satir;
    }

    public static int NOP(int satir, Komut komut) {
        //beklemeye sebep olan komut, etkisi yok 
        //Do nothing
        return -1;
    }

    public static int NOT(int satir, Komut komut) throws Exception {
        //sayının değili
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        StackElement result = dest.getDeger();
  
        if(dest.size == 1){
            result.setValue(255 - result.getValue());
        }
        if(dest.size == 2){
            result.setValue(65535 - result.getValue());
        }
        dest.setDeger(result);
        Flag.getFlag().ZF = (result.getValue() == 0);
        Flag.getFlag().PF = (dest.getBinaryDeger().length() - dest.getBinaryDeger().replace("1", "").length()) % 2 == 1;
        return ++satir;
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

    public static int SHR(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        Degisken src = list.get(1);
        String result = "";
        if(dest.tur == DegiskenTur.REGISTER && src.tur == DegiskenTur.IMMEDIATE){
            result = kaydir(dest.getBinaryDeger(), -1 * src.value,false);
        }
        else
            throw new Exception("Wrong types for ROL instruction");
        dest.setDeger(new StackElement(dest.size, result));
        return ++satir;
    }

    public static int SHL(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        Degisken src = list.get(1);
        String result = "";
        if(dest.tur == DegiskenTur.REGISTER && src.tur == DegiskenTur.IMMEDIATE){
            result = kaydir(dest.getBinaryDeger(),src.value,false);
        }
        else
            throw new Exception("Wrong types for ROL instruction");
        dest.setDeger(new StackElement(dest.size, result));
        return ++satir;
    }

    public static int SBB(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        Degisken src = list.get(1);
        int result = dest.getDeger().getValue() - (src.getDeger().getValue() + (Flag.getFlag().CF ? 1 : 0));
        dest.setDeger(new StackElement(dest.size,setFlagStatesForAdd(dest.size, result)));
        Flag.getFlag().PF = (dest.getBinaryDeger().length() - dest.getBinaryDeger().replace("1", "").length()) % 2 == 1;
        return ++satir;
    }

    public static int ROR(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        Degisken src = list.get(1);
        String result = "";
        if(dest.tur == DegiskenTur.REGISTER && src.tur == DegiskenTur.IMMEDIATE){
            result = kaydir(dest.getBinaryDeger(), -1 * src.value, true);
        }
        else
            throw new Exception("Wrong types for ROL instruction");
        dest.setDeger(new StackElement(dest.size, result));
        return ++satir;
    }

    public static int ROL(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        Degisken src = list.get(1);
        String result = "";
        if(dest.tur == DegiskenTur.REGISTER && src.tur == DegiskenTur.IMMEDIATE){
            result = kaydir(dest.getBinaryDeger(),src.value, true);
        }
        else
            throw new Exception("Wrong types for ROL instruction");
        dest.setDeger(new StackElement(dest.size, result));
        return ++satir;
    }
    private static String kaydir(String binary, int i, boolean roOperation){
        String buffer;
        for(int j = 0;j < i; j++){//sola
            buffer = binary.toString();
            binary = binary + (roOperation ? binary.charAt(0) : '0');
            binary = binary.substring(1);
            Flag.getFlag().CF = binary.charAt(buffer.length()-1) == '1';
            Flag.getFlag().OF = buffer.charAt(0) != binary.charAt(0);
            if(!roOperation)
                Flag.getFlag().ZF = !binary.contains("1");
        }
        for(int j = 0; i < j; j--){//sağa
            buffer = binary.toString();
            binary = (roOperation ? binary.charAt(binary.length()-1) : '0')+binary;
            binary = binary.substring(0,binary.length() - 1);
            Flag.getFlag().CF = binary.charAt(0) == '1';
            Flag.getFlag().OF = buffer.charAt(0) != binary.charAt(0);
            if(!roOperation)
                Flag.getFlag().ZF = !binary.contains("1");
        }
        return binary;
    }

    private static int setFlagStatesForAdd(int size, int result) {
        switch (size) {
            case 1:
                if (result > 255 || result < 0) {
                    Flag.getFlag().CF = true;
                    result %= 256;
                }
                else
                    Flag.getFlag().CF = false;
                Flag.getFlag().ZF = result == 0;
                Flag.getFlag().SF = result > 127;
                break;
            case 2:
                if (result > 65535 || result < 0) {
                    Flag.getFlag().CF = true;
                    result %= 256;
                }
                else
                    Flag.getFlag().CF = false;
                Flag.getFlag().ZF = result == 0;
                Flag.getFlag().SF = result > 32767;
                break;

        }
        return result;
    }
}
