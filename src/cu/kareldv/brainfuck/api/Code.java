package cu.kareldv.brainfuck.api;

/**
 *
 * @author Karel
 */
public interface Code {
    public Instruction next();
    public boolean hasNext();
    public byte[] dumpProgram();
    public boolean hasBefore();
    public Instruction previous();
    public boolean empty();

    public void reset();
}
