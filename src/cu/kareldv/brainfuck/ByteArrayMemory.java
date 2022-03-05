package cu.kareldv.brainfuck;

import cu.kareldv.brainfuck.api.Memory;

/**
 *
 * @author Karel
 */
public class ByteArrayMemory implements Memory{
    private byte[] data;

    @Override
    public void initialize(int len) {
        data=new byte[len];
    }

    @Override
    public byte[] dump() {
        return data;
    }

    @Override
    public void set(int pos, byte val) {
        data[pos]=val;
    }

    @Override
    public byte get(int pos) {
        return data[pos];
    }

    @Override
    public void inc(int pos) {
        data[pos]++;
    }

    @Override
    public void dec(int pos) {
        data[pos]--;
    }

    @Override
    public int length() {
        return data.length;
    }
}
