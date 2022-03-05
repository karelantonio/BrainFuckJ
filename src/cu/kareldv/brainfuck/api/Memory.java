package cu.kareldv.brainfuck.api;

/**
 *
 * @author Karel
 */
public interface Memory {
    public void initialize(int len);
    public byte[] dump();
    public void set(int pos, byte val);
    public byte get(int pos);
    public void inc(int pos);
    public void dec(int pos);
    public int length();
}
