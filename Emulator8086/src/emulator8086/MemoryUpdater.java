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
        switch (komut.komut.toUpperCase()) {
            case "ADC":
                break;
            case "ADD":
                break;
            case "AND":
                break;
            case "CLC":
                EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi,248);
                EmulatorFrame.memoList = new int[1];
                EmulatorFrame.memoList[0] = EmulatorFrame.bellekAdresi;
                EmulatorFrame.bellekAdresi += 1;
                break;
            case "CLD":
                break;
            case "CMP":
                break;
            case "DEC":
                break;
            case "DIV":
                break;
            case "HLT":
                EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi,244);
                EmulatorFrame.memoList = new int[1];
                EmulatorFrame.memoList[0] = EmulatorFrame.bellekAdresi;
                EmulatorFrame.bellekAdresi += 1;
                break;
            case "IDIV":
                break;
            case "IMUL":
                break;
            case "INC":
                break;
            case "JA":
                break;
            case "JAE":
                break;
            case "JB":
                break;
            case "JBE":
                break;
            case "JE":
                break;
            case "JG":
                break;
            case "JGE":
                break;
            case "JL":
                break;
            case "JLE":
                break;
            case "JMP":
                break;
            case "JNE":
                break;
            case "JNP":
                break;
            case "JP":
                break;
            case "JPO":
                break;
            case "LEA":
                break;
            case "LOOP":
                break;
            case "MOV":
                break;
            case "MUL":
                break;
            case "NEG":
                break;
            case "NOP":
                EmulatorFrame.systemMemory.set(EmulatorFrame.bellekAdresi,144);
                EmulatorFrame.memoList = new int[1];
                EmulatorFrame.memoList[0] = EmulatorFrame.bellekAdresi;
                EmulatorFrame.bellekAdresi += 1;
                break;
            case "NOT":
                break;
            case "OR":
                break;
            case "POP":
                break;
            case "PUSH":
                break;
            case "ROL":
                break;
            case "ROR":
                break;
            case "SBB":
                break;
            case "SHL":
                break;
            case "SHR":
                break;
            case "STC":
                break;
            case "STD":
                break;
            case "SUB":
                break;
            case "XOR":
                break;
        }
        
    }
}
