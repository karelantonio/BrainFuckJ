package cu.kareldv.brainfuck;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author roxana
 */
public class BFMachineTest {
    public static String data = "\n" +
"                                >\n" +
"                               + +\n" +
"                              +   +\n" +
"                             [ < + +\n" +
"                            +       +\n" +
"                           + +     + +\n" +
"                          >   -   ]   >\n" +
"                         + + + + + + + +\n" +
"                        [               >\n" +
"                       + +             + +\n" +
"                      <   -           ]   >\n" +
"                     > + + >         > > + >\n" +
"                    >       >       +       <\n" +
"                   < <     < <     < <     < <\n" +
"                  <   [   -   [   -   >   +   <\n" +
"                 ] > [ - < + > > > . < < ] > > >\n" +
"                [                               [\n" +
"               - >                             + +\n" +
"              +   +                           +   +\n" +
"             + + [ >                         + + + +\n" +
"            <       -                       ]       >\n" +
"           . <     < [                     - >     + <\n" +
"          ]   +   >   [                   -   >   +   +\n" +
"         + + + + + + + +                 < < + > ] > . [\n" +
"        -               ]               >               ]\n" +
"       ] +             < <             < [             - [\n" +
"      -   >           +   <           ]   +           >   [\n" +
"     - < + >         > > - [         - > + <         ] + + >\n" +
"    [       -       <       -       >       ]       <       <\n" +
"   < ]     < <     < <     ] +     + +     + +     + +     + +\n" +
"  +   .   +   +   +   .   [   -   ]   <   ]   +   +   +   +   +\n" +
" * * * * * M a d e * B y : * N Y Y R I K K I * 2 0 0 2 * * * * *";
    private BFMachine machine;
    
    public BFMachineTest() {
        machine=new BFMachine.Builder()
                .input(System.in)
                .output(new OutputStream() {
                    @Override
                    public void write(int b) throws IOException {
                        System.out.print((char)b);
                    }
                })
                .code(new CodeInterpreter(data))
                .memory(new ByteArrayMemory())
                .build();
    }

    @Test
    public void testInput() {
        System.out.println("Testing input...");
        assertNotEquals(null, machine.input());
    }

    @Test
    public void testOutput() {
        System.out.println("Testing output...");
        assertNotEquals(null, machine.output());
    }

    @Test
    public void testCode() {
        System.out.println("Testing code...");
        assertNotEquals(null, machine.code());
    }

    @Test
    public void testMemory() {
        System.out.println("Testing memory");
        assertNotEquals(null, machine.memory());
    }
}
