package cu.kareldv.brainfuck;

import cu.kareldv.brainfuck.api.Code;
import cu.kareldv.brainfuck.api.Instruction;
import cu.kareldv.brainfuck.api.Memory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Karel
 */
public class Processor {
    private int pointer;
    private Memory memory;
    private Code code;
    
    public void init(Memory memory, Code code){
        this.memory=memory;
        for (int i = 0; i < memory.length(); i++) {
            this.memory.set(i, (byte)0);
        }
        this.code=code;
    }
    
    void reset(){
        pointer=0;
    }
    
    void step(InputStream in, OutputStream out) throws IOException{
        if(!code.hasNext())throw new IllegalStateException("No more code!");
        Instruction inst = code.next();
        switch(inst){
            case GT:
                pointer++;
                break;
            case LT:
                pointer--;
                break;
            case PLUS:
                memory.inc(pointer);
                break;
            case MINUS:
                memory.dec(pointer);
                break;
            case DOT:
                out.write(memory.get(pointer));
                out.flush();
                break;
            case COMMA:
                memory.set(pointer, (byte) (in.read()&0xff));
                break;
            case BRAC_O:
                if(memory.get(pointer)==0){
                    goToCorrespondentBracC(code);
                }
                break;
            case BRAC_C:
                if(memory.get(pointer)!=0){
                    goToCorrespondentBracO(code);
                }
                break;
        }
    }
    
    boolean hasNext(){
        return code.hasNext();
    }
    
    public int pointer(){
        return pointer;
    }

    private void goToCorrespondentBracC(Code code) {
        int count = 0;
        while(code.hasNext()){
            Instruction inst = code.next();
            if(inst==Instruction.BRAC_O){
                count++;
            }else if(inst==Instruction.BRAC_C){
                count--;
                if(count==0)return;
            }
        }
    }
    
    private void goToCorrespondentBracO(Code code){
        int count = 0;
        while(code.hasBefore()){
            Instruction inst = code.previous();
            
            if(inst==Instruction.BRAC_C){
                count++;
            }else if(inst==Instruction.BRAC_O){
                count--;
                if(count==0)return;
            }
        }
    }
}
