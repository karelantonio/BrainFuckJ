package cu.kareldv.brainfuck;

import cu.kareldv.brainfuck.api.Code;
import cu.kareldv.brainfuck.api.Instruction;
import static cu.kareldv.brainfuck.api.Instruction.is;

/**
 *
 * @author Karel
 */
public class CodeInterpreter implements Code{
    private Instruction[] program;
    private int pos=0;

    public CodeInterpreter(Instruction[] program) {
        this.program = program;
    }
    
    public CodeInterpreter(String data){
        this(data.getBytes());
    }

    public CodeInterpreter(byte[] data) {
        Instruction[] arr = new Instruction[data.length];
        int pos = 0;
        
        for (byte b : data) {
            if(is(b)){
                arr[pos++]=Instruction.from(b);
            }
        }
        program=new Instruction[pos];
        if(pos!=0)
            System.arraycopy(arr, 0, program, 0, pos);
    }

    @Override
    public Instruction next() {
        if(pos>=program.length)return null;
        return program[pos++];
    }

    @Override
    public boolean hasNext() {
        return pos<program.length;
    }

    @Override
    public boolean hasBefore() {
        return pos>0;
    }

    @Override
    public Instruction previous() {
        return program[--pos];
    }

    @Override
    public byte[] dumpProgram() {
        byte[] data = new byte[program.length];
        for (int i = 0; i < data.length; i++) {
            data[i]=program[i].to();
        }
        return data;
    }

    @Override
    public boolean empty() {
        return program.length==0;
    }

    @Override
    public void reset() {
        pos=0;
    }
}
