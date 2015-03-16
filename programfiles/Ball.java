import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Point2D.Double;
public class Ball
{
  private Rectangle m_bounds;
  private double m_speed, m_delay, distanceMovedX, distanceMovedY, dh, vx, vy;
  private long m_lastTime, elapsedTime;
  private Double m_direction, m_velocity;
  public Ball()
  {
    //Hello world! I am a worthless default constructor, and I am really stupid!
  }
  public Ball(int x, int y, int diameter)
  {
    m_bounds=new Rectangle(x, y, diameter, diameter);
    m_speed=50;
    m_delay=100;
    m_direction=new Double(1, 0);
    calculateVelocity();
  }
  public void draw(Graphics g)
  {
    g.drawOval(m_bounds.x, m_bounds.y, m_bounds.width, m_bounds.height);
    g.fillOval(m_bounds.x, m_bounds.y, m_bounds.width, m_bounds.height);
    calculateVelocity();
  }
  public void animate(long currentTime)
  {
    if(m_lastTime==0)
    {
      m_lastTime=currentTime;
    }
    elapsedTime=(currentTime-m_lastTime);
    if(elapsedTime>m_delay)
    {
      int distanceMovedX=(int)(m_velocity.x*(double)(elapsedTime)/1000);
      int distanceMovedY=(int)(m_velocity.y*(double)(elapsedTime)/1000);
      m_bounds.y+=distanceMovedY;
      m_bounds.x+=distanceMovedX;
      m_lastTime=currentTime;
    }
    calculateVelocity();
  }
  public Rectangle getBounds()
  {
    return m_bounds;
  }
  public void setPosition(int x, int y)
  {
    m_bounds.x=x;
    m_bounds.y=y;
    calculateVelocity();
  }
  public void calculateVelocity()
  {
    dh=Math.pow((Math.pow(m_direction.x, 2)+Math.pow(m_direction.y, 2)), .5);
    vx=m_speed*(double)m_direction.x/dh;
    vy=m_speed*(double)m_direction.y/dh;
    
    m_velocity = new Double(vx, vy);
  }
  public void setDirection(double x, double y)
  {
    m_direction.x=x;
    m_direction.y=y;
    calculateVelocity();
  }
  public double getDirectionX()
  {
    return m_direction.x;
  }
  public double getDirectionY()
  {
    return m_direction.y;
  }
  public int getXCoordinate()
  {
    return m_bounds.x;
  }
  public int getYCoordinate()
  {
    return m_bounds.y;
  }
  public void decelerate()
  {
    if(m_speed>=5)
    {
      m_speed-=5;
    }
    else if(m_speed<5)
    {
      m_speed=0;
    }
    calculateVelocity();
  }
  public void changeSpeed(double xDistance, double yDistance)
  {
    m_speed=Math.pow((Math.pow(xDistance, 2)+Math.pow(yDistance, 2)), .5);
    calculateVelocity();
  }
  public void collideX(int x)
  {
    setPosition(x, m_bounds.y);
    setDirection(-m_direction.x, m_direction.y);
  }
  public void collideY(int y)
  {
    setPosition(m_bounds.x, y);
    setDirection(m_direction.x, -m_direction.y);
  }
  public void halt()
  {
    setDirection(0, 0);
  }
}