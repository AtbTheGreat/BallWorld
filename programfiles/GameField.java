import java.awt.Graphics;
import java.awt.Rectangle;
public class GameField
{
  private Rectangle m_bounds;
  public GameField(int x, int y, int width, int height)
  {
    m_bounds=new Rectangle(x, y, width, height);
  }
  public void draw(Graphics g)
  {
    g.drawRect(m_bounds.x, m_bounds.y, m_bounds.width, m_bounds.height);
  }
  public Rectangle getBounds()
  {
    return m_bounds;
  }
}