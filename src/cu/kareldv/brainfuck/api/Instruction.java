package cu.kareldv.brainfuck.api;

/**
 *
 * @author Karel
 */
public enum Instruction {
    GT('>'),
    LT('<'),
    PLUS('+'),
    MINUS('-'),
    DOT('.'),
    COMMA(','),
    BRAC_O('['),
    BRAC_C(']');
    
    private byte to;

    private Instruction(char to) {
        this.to = (byte) (to&0xff);
    }
    
    public byte to(){
        return to;
    }
    
    public static boolean is(byte val){
        switch(val){
            case '>':
            case '<':
            case '+':
            case '-':
            case '.':
            case ',':
            case '[':
            case ']':
                return true;
        }
        return false;
    }
    
    public static Instruction from(byte val){
        switch(val){
            case '>':
                return GT;
            case '<':
                return LT;
            case '+':
                return PLUS;
            case '-':
                return MINUS;
            case '.':
                return DOT;
            case ',':
                return COMMA;
            case '[':
                return BRAC_O;
            case ']':
                return BRAC_C;
        }
        return null;
    }
}
