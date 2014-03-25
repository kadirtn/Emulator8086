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
        setFlagStatesForAdd(dest.size, result);
        dest.setDeger(new StackElement(dest.size,result));
        return ++satir;
    }
    public static int SUB(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        Degisken src = list.get(1);
        int result = dest.getDeger().getValue() - src.getDeger().getValue();
        setFlagStatesForAdd(dest.size, result);
        dest.setDeger(new StackElement(dest.size,result));
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
        setFlagStatesForAdd(dest.size, result);
        dest.setDeger(new StackElement(dest.size,result));
        return ++satir;
    }

    public static int AND(int satir, Komut komut) {
        List<Degisken> list = komut.getDegiskenList();
        int result = 0;
        int operand1, operand2 = 0;

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
        //TODO PF yazılcak
        return ++satir;
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

    public static int INC(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        StackElement result = dest.getDeger();
        result.setValue(result.getValue() +1);
        
        dest.setDeger(result);
        Flag.getFlag().ZF = (result.getValue() == 0);
        //TODO PF yazılcak
        return ++satir;
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
        //sayının negatifi
        List<Degisken> list = komut.getDegiskenList();
        Degisken degisken = list.get(0);
        int result = 0;
        if (degisken.tur == DegiskenTur.REGISTER) {
            result = Register.getRegister().getValue(degisken.deger);
            if (degisken.size == 1) {
                result = 256 - result;
            }
            if (degisken.size == 2) {
                result = 65536 - result;
            }
            Register.getRegister().setValue(degisken.deger, result);

        } else if (degisken.tur == DegiskenTur.MEMORY) {
            result = EmulatorFrame.variableMap.get(degisken.deger).getValue(degisken.value);
            if (degisken.size == 1) {
                result = 256 - result;
            }
            if (degisken.size == 2) {
                result = 65536 - result;
            }
            System.out.println("result: " + result);
            EmulatorFrame.variableMap.get(degisken.deger).setValue(degisken.value, result);
        }
        //    Flag.getFlag().ZF = (result == 0);
        //   Flag.getFlag().PF = (result % 2 == 0);
        return ++satir;
    }

    public static int NOP(int satir, Komut komut) {
        //beklemeye sebep olan komut, etkisi yok 
        //Do nothing
        return -1;
    }

    public static int NOT(int satir, Komut komut) {
        List<Degisken> list = komut.getDegiskenList();
        Degisken degisken = list.get(0);
        int result = 0;
        if (degisken.tur == DegiskenTur.REGISTER) {
            result = Register.getRegister().getValue(degisken.deger);
            if (degisken.size == 1) {
                result = 255 - result;
            }
            if (degisken.size == 2) {
                result = 65535 - result;
            }
            Register.getRegister().setValue(degisken.deger, result);

        } else if (degisken.tur == DegiskenTur.MEMORY) {
            result = EmulatorFrame.variableMap.get(degisken.deger).getValue(degisken.value);
            if (degisken.size == 1) {
                result = 255 - result;
            }
            if (degisken.size == 2) {
                result = 65535 - result;
            }
            System.out.println("result: " + result);
            EmulatorFrame.variableMap.get(degisken.deger).setValue(degisken.value, result);
        }
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

    public static int SHR(int satir, Komut komut) {
        return -1;
    }

    public static int SHL(int satir, Komut komut) {
        return -1;
    }

    public static int SBB(int satir, Komut komut) throws Exception {
        List<Degisken> list = komut.getDegiskenList();
        Degisken dest = list.get(0);
        Degisken src = list.get(1);
        int result = dest.getDeger().getValue() - (src.getDeger().getValue() + (Flag.getFlag().CF ? 1 : 0));
        setFlagStatesForAdd(dest.size, result);
        dest.setDeger(new StackElement(dest.size,result));
        return ++satir;
    }

    public static int ROR(int satir, Komut komut) {
        return -1;
    }

    public static int ROL(int satir, Komut komut) {
        return -1;
    }

    private static void sizeControl(Degisken dest, Degisken src) throws Exception {
        if (dest.tur == DegiskenTur.REGISTER && src.tur == DegiskenTur.REGISTER) {
            if (dest.size != src.size) {
                throw new Exception("Boyut hatası");
            }
        } else if (dest.tur == DegiskenTur.MEMORY && src.tur == DegiskenTur.REGISTER) {
            if (dest.size != src.size) {
                throw new Exception("Boyut hatası");
            } else if (dest.tur == DegiskenTur.REGISTER && src.tur == DegiskenTur.MEMORY) {
                if (dest.size != src.size) {
                    throw new Exception("Boyut hatası");
                }
            } else if (dest.tur == DegiskenTur.MEMORY && src.tur == DegiskenTur.MEMORY) {
                if (dest.size != src.size) {
                    throw new Exception("Boyut hatası");
                }
            } else if (dest.tur == DegiskenTur.REGISTER && src.tur == DegiskenTur.IMMEDIATE) {
                if ((dest.size == 2 && src.value > 65535) || (dest.size == 1 && src.value > 255)) {
                    throw new Exception("Boyut hatası");
                }
            } else if (dest.tur == DegiskenTur.MEMORY && src.tur == DegiskenTur.IMMEDIATE);
        }
        if ((dest.size == 2 && src.value > 65535) || (dest.size == 1 && src.value > 255)) {
            throw new Exception("Boyut hatası");
        }
    }

    private static int setFlagStatesForAdd(int size, int result) {
        switch (size) {
            case 1:
                if (result > 255 || result < 0) {
                    Flag.getFlag().CF = true;
                    result %= 256;
                }
                if (result == 0) {
                    Flag.getFlag().ZF = true;
                }
                if (result > 127) {
                    Flag.getFlag().SF = true;
                }

                break;
            case 2:
                if (result > 65535 || result < 0) {
                    Flag.getFlag().CF = true;
                    result %= 256;
                }
                if (result == 0) {
                    Flag.getFlag().ZF = true;
                }
                if (result > 32767) {
                    Flag.getFlag().SF = true;
                }
                break;

        }
        return result;
    }
}
