package cu.kareldv.brainfuck;

import cu.kareldv.brainfuck.api.Code;
import cu.kareldv.brainfuck.api.Memory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Karel
 */
public final class BFMachine {
    private InputStream in;
    private OutputStream out;
    private Code code;
    private Memory memory;
    private Processor processor;

    BFMachine(){
        this.processor=new Processor();
    }

    public InputStream input() {
        return in;
    }

    public OutputStream output() {
        return out;
    }

    public Code code() {
        return code;
    }
    
    public Memory memory(){
        return memory;
    }
    
    public void execute(){
        memory.initialize(30000);
        code.reset();
        processor.init(memory, code);
        if(!code.empty())
        do{
            try {
                processor.step(in, out);
            } catch (IOException ex) {
                throw new Error("Wrapped!", ex);
            }
        }while(code.hasNext());
    }
    
    public Builder builder(){
        return new Builder(this);
    }
    
    private String selfCheckErrors() {
        if(in==null)return "Input stream not configurated";
        if(out==null)return "Output stream not configurated";
        if(code==null)return "Code not configured";
        if(memory==null)return "Memory not configured";
        return null;
    }
    
    public static final class Builder{
        private BFMachine machine;

        public Builder() {
            machine=new BFMachine();
        }

        private Builder(BFMachine aThis) {
            machine=aThis;
        }
        
        public Builder input(InputStream in){
            machine.in=in;
            return this;
        }
        
        public Builder output(OutputStream out){
            machine.out=out;
            return this;
        }
        
        public Builder code(Code code){
            machine.code=code;
            return this;
        }
        
        public Builder memory(Memory memory){
            machine.memory=memory;
            return this;
        }
        
        public BFMachine build(){
            String msg = machine.selfCheckErrors();
            if(msg!=null)throw new IllegalStateException(msg);
            return machine;
        }
    }
}
