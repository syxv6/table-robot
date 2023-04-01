public class Application
{
    public static void main(String[] args)
    {
        TableRobotProcessor processor = new TableRobotProcessor();
        processor.process("src/test/resources/input.txt");
    }
}
