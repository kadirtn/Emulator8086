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
    public Integer AL;
    public Integer AH;
    public Integer BL;
    public Integer BH;
    public Integer CL;
    public Integer CH;
    public Integer DL;
    public Integer DH;
    private static Register register = null;

    private Register() {
        AX = new Integer[2];
        BX = new Integer[2];
        CX = new Integer[2];
        DX = new Integer[2];
        AX[0] = 0;
        AX[1] = 0;
        BX[0] = 0;
        BX[1] = 0;
        CX[0] = 0;
        CX[1] = 0;
        DX[0] = 0;
        DX[1] = 0;
        AL = AX[0];
        AH = AX[1];
        BL = BX[0];
        BH = BX[1];
        CL = CX[0];
        CH = CX[1];
        DL = DX[0];
        DH = DX[1];
    }

    public static Register getRegister() {
        if (register == null) {
            register = new Register();
        }
        return register;
    }
}
