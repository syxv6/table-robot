import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableRobotProcessor
{
    private static final String PLACE_PATTERN = "^PLACE (\\d+),(\\d+),(NORTH|SOUTH|EAST|WEST)$";
    private static final String ROBOT_PATTERN = "^ROBOT (\\d+)$";
    private static final String MOVE_PATTERN = "^MOVE$";
    private static final String LEFT_PATTERN = "^LEFT$";
    private static final String RIGHT_PATTERN = "^RIGHT";
    private static final String REPORT_PATTERN = "^REPORT";

    private final List<Robot> robots = new ArrayList<>();
    private int activeRobot = 1;

    public void process(String inputPath)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                processCommand(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processCommand(String command) throws Exception
    {
        Matcher matcher = Pattern.compile(PLACE_PATTERN).matcher(command);
        if (matcher.matches()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            Face face = Face.valueOf(matcher.group(3));
            robots.add(Robot.builder().x(x).y(y).face(face).build());
            return;
        }
        matcher = Pattern.compile(ROBOT_PATTERN).matcher(command);
        if (matcher.matches()) {
            activeRobot = Integer.parseInt(matcher.group(1));
            return;
        }
        matcher = Pattern.compile(MOVE_PATTERN).matcher(command);
        if (matcher.matches()) {
            robots.get(activeRobot - 1).move();
            return;
        }
        matcher = Pattern.compile(LEFT_PATTERN).matcher(command);
        if (matcher.matches()) {
            robots.get(activeRobot - 1).left();
            return;
        }
        matcher = Pattern.compile(RIGHT_PATTERN).matcher(command);
        if (matcher.matches()) {
            robots.get(activeRobot - 1).right();
            return;
        }
        matcher = Pattern.compile(REPORT_PATTERN).matcher(command);
        if (matcher.matches()) {
            report();
        }
    }

    private void report()
    {
        System.out.println("Number of robots: " + robots.size());
        System.out.println("Activated robot: " + activeRobot);
        int i = 0;
        for (Robot robot : robots) {
            i++;
            System.out.println("Robot " + i + ": " + robot.getX() + "," + robot.getY() + "," + robot.getFace());
        }
    }
}
