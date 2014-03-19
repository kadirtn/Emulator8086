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
public class Word {
    public Integer[] word;//0 Low 1 High
    public Word(Integer[] word){
        this.word = word;
    }
    public Word(Integer low,Integer high){
        this.word = new Integer[2];
        word[0] = low;
        word[1] = high;
    }
    public Word(int value){
        this.word = new Integer[2];
        if(value < 256){
            word[0] = value;
            word[1] = 0;
        }
        else{
            word[0] = value %256;
            word[1] = (value - value%256)/256;
        }
    }
    
}
