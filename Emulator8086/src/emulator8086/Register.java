/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emulator8086;

/**
 *
 * @author kadirtuna
 */
public class Register {

    public Integer[] AX;
    public Integer[] BX;
    public Integer[] CX;
    public Integer[] DX;
    public Integer[] AL;
    public Integer[] AH;
    public Integer[] BL;
    public Integer[] BH;
    public Integer[] CL;
    public Integer[] CH;
    public Integer[] DL;
    public Integer[] DH;
    private static Register register = null;

    private Register() {
        AX = new Integer[2];
        BX = new Integer[2];
        CX = new Integer[2];
        DX = new Integer[2];
        AL = new Integer[1];
        AH = new Integer[1];
        BL = new Integer[1];
        BH = new Integer[1];
        CL = new Integer[1];
        CH = new Integer[1];
        DL = new Integer[1];
        DH = new Integer[1];
        AX[0] = 0;
        AX[1] = 0;
        BX[0] = 0;
        BX[1] = 0;
        CX[0] = 0;
        CX[1] = 0;
        DX[0] = 0;
        DX[1] = 0;
        AL[0] = AX[0];
        AH[0] = AX[1];
        BL[0] = BX[0];
        BH[0] = BX[1];
        CL[0] = CX[0];
        CH[0] = CX[1];
        DL[0] = DX[0];
        DH[0] = DX[1];
    }

    public static Register getRegister() {
        if (register == null) {
            register = new Register();
        }
        return register;
    }
}
