/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emulator8086;

import line.Komut;
import steps.StackElement;

/**
 *
 * @author kadirtuna
 */
public class MemoryUpdater {

    public static void updateMemory(Komut komut, StackElement res) {
        switch (komut.komut.toUpperCase().replaceAll("İ", "I")) {
            case "ADC":
            case "ADD":
            case "OR":
            case "AND":
            case "XOR":
            case "ROL":
            case "ROR":
            case "SBB":
            case "SHL":
            case "SHR":
            case "SUB":
                if (res.size == 1) {
                    EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi, res.getLowValue());
                    EmulatorFrame.memoList = new int[1];
                    EmulatorFrame.memoList[0] = EmulatorFrame.bellekAdresi++;
                } else {
                    EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi, res.getLowValue());
                    EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi + 1, res.getHighValue());
                    EmulatorFrame.memoList = new int[2];
                    EmulatorFrame.memoList[0] = EmulatorFrame.bellekAdresi++;
                    EmulatorFrame.memoList[1] = EmulatorFrame.bellekAdresi++;
                }
                break;

            case "STC":
                EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi, 249);
                EmulatorFrame.memoList = new int[1];
                EmulatorFrame.memoList[0] = EmulatorFrame.bellekAdresi++;
                break;
            case "CLC":
                EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi, 248);
                EmulatorFrame.memoList = new int[1];
                EmulatorFrame.memoList[0] = EmulatorFrame.bellekAdresi++;
                break;
            case "STD":
                EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi, 253);
                EmulatorFrame.memoList = new int[1];
                EmulatorFrame.memoList[0] = EmulatorFrame.bellekAdresi++;
                break;
            case "CLD":
                EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi, 252);
                EmulatorFrame.memoList = new int[1];
                EmulatorFrame.memoList[0] = EmulatorFrame.bellekAdresi++;
                break;
            case "CMP":
                EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi, 58);
                EmulatorFrame.memoList = new int[1];
                EmulatorFrame.memoList[0] = EmulatorFrame.bellekAdresi++;
                break;
            case "DEC":
                EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi, 254);
                EmulatorFrame.memoList = new int[1];
                EmulatorFrame.memoList[0] = EmulatorFrame.bellekAdresi++;
                break;
            case "HLT":
                EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi, 244);
                EmulatorFrame.memoList = new int[1];
                EmulatorFrame.memoList[0] = EmulatorFrame.bellekAdresi++;
                break;
            case "INC":
                EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi, 254);
                EmulatorFrame.memoList = new int[1];
                EmulatorFrame.memoList[0] = EmulatorFrame.bellekAdresi++;
                break;
            case "JA":
            case "JAE":
            case "JB":
            case "JBE":
            case "JE":
            case "JG":
            case "JGE":
            case "JL":
            case "JLE":
            case "JMP":
            case "JNE":
            case "JNP":
            case "JP":
            case "JPO":
                EmulatorFrame.memoList = null;
                break;
            case "LEA":
                EmulatorFrame.memoList = null;
                break;
            case "LOOP":
                EmulatorFrame.memoList = null;
                break;
            case "MOV":
            case "NEG":
            case "NOT":
                if (res.size == 1) {
                    EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi, res.getLowValue());
                    EmulatorFrame.memoList = new int[1];
                    EmulatorFrame.memoList[0] = EmulatorFrame.bellekAdresi++;
                } else {
                    EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi, res.getLowValue());
                    EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi + 1, res.getHighValue());
                    EmulatorFrame.memoList = new int[2];
                    EmulatorFrame.memoList[0] = EmulatorFrame.bellekAdresi++;
                    EmulatorFrame.memoList[1] = EmulatorFrame.bellekAdresi++;
                }
                break;
            case "NOP":
                EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi, 144);
                EmulatorFrame.memoList = new int[1];
                EmulatorFrame.memoList[0] = EmulatorFrame.bellekAdresi++;
                break;
            case "POP":
            case "PUSH":
                if (res.size == 1) {
                    EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi, res.getLowValue());
                    EmulatorFrame.memoList = new int[1];
                    EmulatorFrame.memoList[0] = EmulatorFrame.bellekAdresi++;
                } else {
                    EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi, res.getLowValue());
                    EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi + 1, res.getHighValue());
                    EmulatorFrame.memoList = new int[2];
                    EmulatorFrame.memoList[0] = EmulatorFrame.bellekAdresi++;
                    EmulatorFrame.memoList[1] = EmulatorFrame.bellekAdresi++;
                }
                break;

            case "MUL":
            case "IMUL":
                if(res.size == 1){
                    EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi, Register.getRegister().getValue("AL"));
                    EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi + 1, Register.getRegister().getValue("AH"));
                    EmulatorFrame.memoList = new int[2];
                    EmulatorFrame.memoList[0] = EmulatorFrame.bellekAdresi++;
                    EmulatorFrame.memoList[1] = EmulatorFrame.bellekAdresi++;
                }
                else{
                    EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi, Register.getRegister().getValue("AL"));
                    EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi + 1, Register.getRegister().getValue("AH"));
                    EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi + 2, Register.getRegister().getValue("DL"));
                    EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi + 3, Register.getRegister().getValue("DH"));
                    EmulatorFrame.memoList = new int[4];
                    EmulatorFrame.memoList[0] = EmulatorFrame.bellekAdresi++;
                    EmulatorFrame.memoList[1] = EmulatorFrame.bellekAdresi++;
                    EmulatorFrame.memoList[2] = EmulatorFrame.bellekAdresi++;
                    EmulatorFrame.memoList[3] = EmulatorFrame.bellekAdresi++;
                }
                break;

            case "DIV":
            case "IDIV":
                if(res.size == 1){
                    EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi, Register.getRegister().getValue("AL"));//bölüm
                    EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi + 1, Register.getRegister().getValue("AH"));//kalan
                    EmulatorFrame.memoList = new int[2];
                    EmulatorFrame.memoList[0] = EmulatorFrame.bellekAdresi++;
                    EmulatorFrame.memoList[1] = EmulatorFrame.bellekAdresi++;
                }
                else{
                    EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi, Register.getRegister().getValue("AL"));//bölüm
                    EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi + 1, Register.getRegister().getValue("AH"));//bölüm
                    EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi + 2, Register.getRegister().getValue("DL"));//kalan
                    EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi + 3, Register.getRegister().getValue("DH"));//kalan
                    EmulatorFrame.memoList = new int[4];
                    EmulatorFrame.memoList[0] = EmulatorFrame.bellekAdresi++;
                    EmulatorFrame.memoList[1] = EmulatorFrame.bellekAdresi++;
                    EmulatorFrame.memoList[2] = EmulatorFrame.bellekAdresi++;
                    EmulatorFrame.memoList[3] = EmulatorFrame.bellekAdresi++;
                }
                break;
        }

    }
}
