import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Robot
{
    private int x;
    private int y;
    private Face face;

    public void move() throws Exception
    {
        switch (face) {
            case EAST:
                x++;
                break;
            case WEST:
                x--;
                break;
            case NORTH:
                y++;
                break;
            case SOUTH:
                y--;
                break;
            default:
                throw new Exception();
        }
    }

    public void left() throws Exception
    {
        switch (face) {
            case EAST:
                face = Face.NORTH;
                break;
            case NORTH:
                face = Face.WEST;
                break;
            case WEST:
                face = Face.SOUTH;
                break;
            case SOUTH:
                face = Face.EAST;
                break;
            default:
                throw new Exception();
        }
    }

    public void right() throws Exception
    {
        switch (face) {
            case EAST:
                face = Face.SOUTH;
                break;
            case NORTH:
                face = Face.EAST;
                break;
            case WEST:
                face = Face.NORTH;
                break;
            case SOUTH:
                face = Face.WEST;
                break;
            default:
                throw new Exception();
        }
    }
}
