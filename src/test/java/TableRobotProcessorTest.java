import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TableRobotProcessorTest
{
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream systemPrintStream = System.out;

    @BeforeEach
    void setUp()
    {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() throws IOException
    {
        outputStream.flush();
        outputStream.reset();
        System.setOut(systemPrintStream);
    }

    @Test
    void test()
    {
        TableRobotProcessor processor = new TableRobotProcessor();
        processor.process("src/test/resources/input.txt");
        String expectedOutput = "Number of robots: 4\n"
                + "Activated robot: 4\n"
                + "Robot 1: 1,1,EAST\n"
                + "Robot 2: 1,1,NORTH\n"
                + "Robot 3: -1,-1,WEST\n"
                + "Robot 4: -1,-1,SOUTH\n";
        assertEquals(expectedOutput, outputStream.toString());
    }
}