import java.awt.Graphics;
public class PullString
{
  private int x1, y1, x2, y2;
  public PullString(int xCoordinate1, int yCoordinate1, int xCoordinate2, int yCoordinate2)
  {
    x1=xCoordinate1;
    y1=yCoordinate1;
    x2=xCoordinate2;
    y2=yCoordinate2;
  }
  public void draw(Graphics g)
  {
    g.drawLine(x1, y1, x2, y2);
  }
  public int getXDisplacement()
  {
    return x2-x1;
  }
  public int getYDisplacement()
  {
    return y2-y1;
  }
}