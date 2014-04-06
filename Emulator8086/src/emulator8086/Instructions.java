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
 * @author gizem
 */
public class Instructions {

    public static int MOV(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        Degisken src = list.get(1);
        dest.setDeger(src.getDeger());
        MemoryUpdater.updateMemory(komut, src.getDeger());
        return ++satir;
    }

    public static int ADD(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        Degisken src = list.get(1);
        long result = dest.getDeger().getValue() + src.getDeger().getValue();
        StackElement res = new StackElement(dest.size, setFlagStatesForAdd(dest.size, new Long(result).intValue()));
        dest.setDeger(res);
        Flag.getFlag().SF = dest.getMostSignificantBit().equals("1");
        Flag.getFlag().OF = dest.getMostSignificantBit().equals(src.getMostSignificantBit()) && !res.getMostSignificantBit().equals(src.getMostSignificantBit());
        Flag.getFlag().PF = (dest.getBinaryDeger().length() - dest.getBinaryDeger().replace("1", "").length()) % 2 == 0;
        MemoryUpdater.updateMemory(komut, res);
        return ++satir;
    }

    public static int SUB(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        Degisken src = list.get(1);
        long result = dest.getDeger().getValue() - src.getDeger().getValue();
        StackElement res = new StackElement(dest.size, setFlagStatesForAdd(dest.size, new Long(result).intValue()));
        dest.setDeger(res);
        Flag.getFlag().SF = dest.getMostSignificantBit().equals("1");
        Flag.getFlag().OF = dest.getMostSignificantBit().equals(src.getMostSignificantBit()) && !res.getMostSignificantBit().equals(src.getMostSignificantBit());
        Flag.getFlag().PF = (dest.getBinaryDeger().length() - dest.getBinaryDeger().replace("1", "").length()) % 2 == 0;
        MemoryUpdater.updateMemory(komut, res);
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
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int POP(int satir, Komut komut) throws Exception {
        Degisken dest = (Degisken) komut.getDegiskenList().get(0);
        StackElement element = Stack.getStack().pop();
        if (dest.size < 2) {
            throw new Exception("Pop edilen destination boyutu word(2 byte) olmalı.");
        }
        dest.setDeger(element);
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int ADC(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        Degisken src = list.get(1);
        long result = dest.getDeger().getValue() + src.getDeger().getValue() + (Flag.getFlag().CF ? 1 : 0);
        StackElement res = new StackElement(dest.size, setFlagStatesForAdd(dest.size, new Long(result).intValue()));
        dest.setDeger(res);
        Flag.getFlag().SF = dest.getMostSignificantBit().equals("1");
        Flag.getFlag().OF = dest.getMostSignificantBit().equals(src.getMostSignificantBit()) && !res.getMostSignificantBit().equals(src.getMostSignificantBit());
        Flag.getFlag().PF = (dest.getBinaryDeger().length() - dest.getBinaryDeger().replace("1", "").length()) % 2 == 0;
        MemoryUpdater.updateMemory(komut, res);
        return ++satir;
    }

    public static int AND(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        Degisken src = list.get(1);

        String result = "";
        String destOperand = dest.getBinaryDeger(dest.size);
        String srcOperand = src.getBinaryDeger(dest.size);
        for (int i = 0; i < destOperand.length(); i++) {
            result += ((destOperand.charAt(i) == '1' && srcOperand.charAt(i) == '1') ? '1' : '0');
        }

        dest.setBinaryDeger(dest.size, result);
        Flag.getFlag().CF = false;
        Flag.getFlag().OF = false;
        Flag.getFlag().ZF = dest.getDeger().getValue() == 0;
        Flag.getFlag().SF = dest.getMostSignificantBit().equals("1");
        Flag.getFlag().PF = (dest.getBinaryDeger().length() - dest.getBinaryDeger().replace("1", "").length()) % 2 == 0;
        MemoryUpdater.updateMemory(komut, dest.getDeger());

        return ++satir;
    }

    public static int CLC(int satir, Komut komut) {
        Flag.getFlag().CF = false;
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int CLD(int satir, Komut komut) {
        Flag.getFlag().DF = false;
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int CMP(int satir, Komut komut) {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        Degisken src = list.get(1);
        long result = dest.getDeger().getValue() - src.getDeger().getValue();
        Degisken dummy = new Degisken(setFlagStatesForAdd(dest.size, new Long(result).intValue()));
        Flag.getFlag().SF = dummy.getMostSignificantBit().equals("1");

        Flag.getFlag().PF = (dummy.getBinaryDeger().length() - dummy.getBinaryDeger().replace("1", "").length()) % 2 == 1;
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int DEC(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        StackElement result = dest.getDeger();
        result.setValue(new Long(result.getValue() - 1).intValue());

        dest.setDeger(result);
        Flag.getFlag().ZF = (result.getValue() == 0);
        Flag.getFlag().PF = (dest.getBinaryDeger().length() - dest.getBinaryDeger().replace("1", "").length()) % 2 == 0;
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int DIV(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken src = list.get(0);
        switch (src.size) {
            case 1:
                long ax = Register.getRegister().getValue("AX");
                long ah = ax % src.getDeger().getValue();
                long al = (ax - (ax % src.getDeger().getValue())) / src.getDeger().getValue();
                Register.getRegister().setValue("AH", new Long(ah).intValue());
                Register.getRegister().setValue("AL", new Long(al).intValue());
                break;
            case 2:
                long dxax = (Register.getRegister().getValue("AX").longValue() * 256 * 256) + Register.getRegister().getValue("AX").longValue();
                long ldx = dxax % src.getDeger().getValue().longValue();
                long lax = (dxax - (dxax % src.getDeger().getValue().longValue())) / src.getDeger().getValue().longValue();
                Register.getRegister().setValue("DX", new Long(ldx).intValue());
                Register.getRegister().setValue("AX", new Long(lax).intValue());
                break;
        }
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int HLT(int satir, Komut komut) {
        MemoryUpdater.updateMemory(komut, null);
        return -1;//-1 = programı sonlandır
    }

    public static int IDIV(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken src = list.get(0);
        switch (src.size) {
            case 1:
                long ax = new StackElement(2,Register.getRegister().getValue("AX")).getSignedValue();
                long ah = ax % src.getDeger().getSignedValue();
                long al = (ax - (ax % src.getDeger().getSignedValue())) / src.getDeger().getSignedValue();
                if(al < 256)
                    al += 256;
                Register.getRegister().setValue("AH", new Long(ah).intValue());
                Register.getRegister().setValue("AL", new Long(al).intValue());
                break;
            case 2:
                long dxax = new StackElement(4,(Register.getRegister().getValue("AX").longValue() * 256 * 256) + Register.getRegister().getValue("AX").longValue()).getSignedValue().longValue();
                long ldx = dxax % src.getDeger().getValue().longValue();
                long lax = (dxax - (dxax % src.getDeger().getValue().longValue())) / src.getDeger().getValue().longValue();
                if(lax < 256*256)
                    lax += 256*256;
                Register.getRegister().setValue("DX", new Long(ldx).intValue());
                Register.getRegister().setValue("AX", new Long(lax).intValue());
                break;
        }
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int IMUL(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken src = list.get(0);
        long result;
        long lngresult;
        switch (src.size) {
            case 1:
                result = src.getDeger().getSignedValue() * new StackElement(1, Register.getRegister().getValue("AL")).getSignedValue();
                Flag.getFlag().CF = result >= 256;
                Flag.getFlag().OF = result >= 256;
                Register.getRegister().setValue("AX", new Long(result).intValue());
                break;
            case 2:
                long srcval = src.getDeger().getSignedValue();
                long regval = new StackElement(2, Register.getRegister().getValue("AX")).getSignedValue();
                lngresult = srcval * regval;
                Flag.getFlag().CF = new Long(lngresult).compareTo(new Long(256 * 256)) >= 0;
                Flag.getFlag().OF = new Long(lngresult).compareTo(new Long(256 * 256)) >= 0;
                long axValue = lngresult % 65536;
                long dxValue = (lngresult - (lngresult % (256 * 256))) / (256 * 256);
                Register.getRegister().setValue("AX", new Long(axValue).intValue());
                Register.getRegister().setValue("DX", new Long(dxValue).intValue());
                break;
        }
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int INC(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        StackElement result = dest.getDeger();
        result.setValue(new Long(result.getValue() + 1).intValue());

        dest.setDeger(result);
        Flag.getFlag().ZF = (result.getValue() == 0);
        Flag.getFlag().PF = (dest.getBinaryDeger().length() - dest.getBinaryDeger().replace("1", "").length()) % 2 == 0;
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int JA(int satir, Komut komut) {
        if (!Flag.getFlag().CF && !Flag.getFlag().ZF) {
            return komut.functionLine;
        }
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int JAE(int satir, Komut komut) {
        if (!Flag.getFlag().CF) {
            return komut.functionLine;
        }
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int JB(int satir, Komut komut) {
        if (Flag.getFlag().CF) {
            return komut.functionLine;
        }
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int JBE(int satir, Komut komut) {
        if (Flag.getFlag().CF && Flag.getFlag().ZF) {
            return komut.functionLine;
        }
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int JE(int satir, Komut komut) {
        if (Flag.getFlag().ZF) {
            return komut.functionLine;
        }
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int JG(int satir, Komut komut) {
        if (!Flag.getFlag().ZF && Flag.getFlag().SF == Flag.getFlag().OF) {
            return komut.functionLine;
        }
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int JGE(int satir, Komut komut) {
        if (Flag.getFlag().SF == Flag.getFlag().OF) {
            return komut.functionLine;
        }
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int JL(int satir, Komut komut) {
        if (Flag.getFlag().SF != Flag.getFlag().OF) {
            return komut.functionLine;
        }
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int JMP(int satir, Komut komut) {
        MemoryUpdater.updateMemory(komut, null);
        return komut.functionLine;
    }

    public static int JLE(int satir, Komut komut) {
        if (Flag.getFlag().SF != Flag.getFlag().OF && Flag.getFlag().ZF) {
            return komut.functionLine;
        }
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int JNE(int satir, Komut komut) {
        if (!Flag.getFlag().ZF) {
            return komut.functionLine;
        }
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int JNP(int satir, Komut komut) {
        if (!Flag.getFlag().PF) {
            return komut.functionLine;
        }
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int JP(int satir, Komut komut) {
        if (Flag.getFlag().PF) {
            return komut.functionLine;
        }
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int JPO(int satir, Komut komut) {
        if (!Flag.getFlag().PF) {
            return komut.functionLine;
        }
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int LEA(int satir, Komut komut) throws Exception {
        throw new Exception("LEA instruction is not supported");
    }

    public static int LOOP(int satir, Komut komut) throws Exception {
        Register.getRegister().setValue("CX", Register.getRegister().getValue("CX") - 1);
        if (Register.getRegister().getValue("CX") != 0) {
            return komut.functionLine;
        }
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int MUL(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken src = list.get(0);
        long result;
        switch (src.size) {
            case 1:
                result = src.getDeger().getValue() * Register.getRegister().getValue("AL");
                Flag.getFlag().CF = result >= 256;
                Flag.getFlag().OF = result >= 256;
                Register.getRegister().setValue("AX", new Long(result).intValue());
                break;
            case 2:
                long srcval = src.getDeger().getValue();
                long regval = Register.getRegister().getValue("AX");
                result = srcval * regval;
                Flag.getFlag().CF = new Long(result).compareTo(new Long(256 * 256)) >= 0;
                Flag.getFlag().OF = new Long(result).compareTo(new Long(256 * 256)) >= 0;
                long axValue = result % 65536;
                long dxValue = (result - (result % (256 * 256))) / (256 * 256);
                Register.getRegister().setValue("AX", new Long(axValue).intValue());
                Register.getRegister().setValue("DX", new Long(dxValue).intValue());
                break;
        }
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int NEG(int satir, Komut komut) throws Exception {
        //sayının negatifi
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        StackElement result = dest.getDeger();

        if (dest.size == 1) {
            result.setValue(256 - result.getValue().intValue());
        }
        if (dest.size == 2) {
            result.setValue(65536 - result.getValue().intValue());
        }
        dest.setDeger(result);
        Flag.getFlag().ZF = (result.getValue() == 0);
        Flag.getFlag().PF = (dest.getBinaryDeger().length() - dest.getBinaryDeger().replace("1", "").length()) % 2 == 0;
        Flag.getFlag().SF = dest.getMostSignificantBit().equals("1");
        Flag.getFlag().OF = (dest.size == 1 && dest.getDeger().getValue() == 128) || (dest.size == 2 && dest.getDeger().getValue() == 32768);
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int NOP(int satir, Komut komut) {
        //beklemeye sebep olan komut, etkisi yok 
        //Do nothing
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int NOT(int satir, Komut komut) throws Exception {
        //sayının değili
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        StackElement result = dest.getDeger();

        if (dest.size == 1) {
            result.setValue(255 - result.getValue().intValue());
        }
        if (dest.size == 2) {
            result.setValue(65535 - result.getValue().intValue());
        }
        dest.setDeger(result);
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int OR(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        Degisken src = list.get(1);

        String result = "";
        String destOperand = dest.getBinaryDeger(dest.size);
        String srcOperand = src.getBinaryDeger(dest.size);
        for (int i = 0; i < destOperand.length(); i++) {
            result += ((destOperand.charAt(i) == '1' || srcOperand.charAt(i) == '1') ? '1' : '0');
        }

        dest.setBinaryDeger(dest.size, result);
        Flag.getFlag().CF = false;
        Flag.getFlag().OF = false;
        Flag.getFlag().ZF = dest.getDeger().getValue() == 0;
        Flag.getFlag().SF = dest.getMostSignificantBit().equals("1");
        Flag.getFlag().PF = (dest.getBinaryDeger().length() - dest.getBinaryDeger().replace("1", "").length()) % 2 == 0;

        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int XOR(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        Degisken src = list.get(1);

        String result = "";
        String destOperand = dest.getBinaryDeger(dest.size);
        String srcOperand = src.getBinaryDeger(dest.size);
        for (int i = 0; i < destOperand.length(); i++) {
            result += (((destOperand.charAt(i) == '1' && srcOperand.charAt(i) == '0') || (destOperand.charAt(i) == '0' && srcOperand.charAt(i) == '1')) ? '1' : '0');
        }

        dest.setBinaryDeger(dest.size, result);
        Flag.getFlag().CF = false;
        Flag.getFlag().OF = false;
        Flag.getFlag().ZF = dest.getDeger().getValue() == 0;
        Flag.getFlag().SF = dest.getMostSignificantBit().equals("1");
        Flag.getFlag().PF = (dest.getBinaryDeger().length() - dest.getBinaryDeger().replace("1", "").length()) % 2 == 0;

        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int STD(int satir, Komut komut) {
        Flag.getFlag().DF = true;
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int STC(int satir, Komut komut) {
        Flag.getFlag().CF = true;
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int SHR(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        Degisken src = list.get(1);
        String result = "";
        if (dest.tur == DegiskenTur.REGISTER && src.tur == DegiskenTur.IMMEDIATE) {
            result = kaydir(dest.getBinaryDeger(), -1 * src.value, false);
        } else {
            throw new Exception("Wrong types for ROL instruction");
        }
        dest.setDeger(new StackElement(dest.size, result));
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int SHL(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        Degisken src = list.get(1);
        String result = "";
        if (dest.tur == DegiskenTur.REGISTER && src.tur == DegiskenTur.IMMEDIATE) {
            result = kaydir(dest.getBinaryDeger(), src.value, false);
        } else {
            throw new Exception("Wrong types for ROL instruction");
        }
        dest.setDeger(new StackElement(dest.size, result));
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int SBB(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        Degisken src = list.get(1);
        long result = dest.getDeger().getValue() - (src.getDeger().getValue() + (Flag.getFlag().CF ? 1 : 0));
        StackElement res = new StackElement(dest.size, setFlagStatesForAdd(dest.size, new Long(result).intValue()));
        dest.setDeger(res);
        Flag.getFlag().OF = dest.getMostSignificantBit().equals(src.getMostSignificantBit()) && !res.getMostSignificantBit().equals(src.getMostSignificantBit());
        Flag.getFlag().PF = (dest.getBinaryDeger().length() - dest.getBinaryDeger().replace("1", "").length()) % 2 == 0;
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int ROR(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        Degisken src = list.get(1);
        String result = "";
        if (dest.tur == DegiskenTur.REGISTER && src.tur == DegiskenTur.IMMEDIATE) {
            result = kaydir(dest.getBinaryDeger(), -1 * src.value, true);
        } else {
            throw new Exception("Wrong types for ROL instruction");
        }
        dest.setDeger(new StackElement(dest.size, result));
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    public static int ROL(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        Degisken src = list.get(1);
        String result = "";
        if (dest.tur == DegiskenTur.REGISTER && src.tur == DegiskenTur.IMMEDIATE) {
            result = kaydir(dest.getBinaryDeger(), src.value, true);
        } else {
            throw new Exception("Wrong types for ROL instruction");
        }
        dest.setDeger(new StackElement(dest.size, result));
        MemoryUpdater.updateMemory(komut, null);
        return ++satir;
    }

    private static String kaydir(String binary, int i, boolean roOperation) {
        String buffer;
        for (int j = 0; j < i; j++) {//sola
            buffer = binary.toString();
            binary = binary + (roOperation ? binary.charAt(0) : '0');
            binary = binary.substring(1);
            Flag.getFlag().CF = binary.charAt(buffer.length() - 1) == '1';
            Flag.getFlag().OF = buffer.charAt(0) != binary.charAt(0);
            if (!roOperation) {
                Flag.getFlag().ZF = !binary.contains("1");
            }
        }
        for (int j = 0; i < j; j--) {//sağa
            buffer = binary.toString();
            binary = (roOperation ? binary.charAt(binary.length() - 1) : '0') + binary;
            binary = binary.substring(0, binary.length() - 1);
            Flag.getFlag().CF = binary.charAt(0) == '1';
            Flag.getFlag().OF = buffer.charAt(0) != binary.charAt(0);
            if (!roOperation) {
                Flag.getFlag().ZF = !binary.contains("1");
            }
        }
        return binary;
    }

    private static int setFlagStatesForAdd(int size, int result) {
        switch (size) {
            case 1:
                if (result > 255 || result < 0) {
                    Flag.getFlag().CF = true;
                    result %= 256;
                } else {
                    Flag.getFlag().CF = false;
                }
                Flag.getFlag().ZF = result == 0;
                Flag.getFlag().SF = result > 127;
                break;
            case 2:
                if (result > 65535 || result < 0) {
                    Flag.getFlag().CF = true;
                    result %= 256;
                } else {
                    Flag.getFlag().CF = false;
                }
                Flag.getFlag().ZF = result == 0;
                Flag.getFlag().SF = result > 32767;
                break;

        }
        return result;
    }

}
