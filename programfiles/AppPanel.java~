import javax.swing.JPanel;
import java.awt.Graphics;
import javax.swing.Timer;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.lang.Runnable; 
public class AppPanel extends JPanel implements MouseListener,ActionListener,Runnable
{
  private static final int refreshRate=100;
  private long lastRefresh;
  private Ball ball;
  private GameField gameField;
  private ArrayList<PullString> pullString=new ArrayList<PullString>();
  private Rectangle ballBounds;
  private Rectangle gameFieldBounds;
  private Timer timer;
  private boolean gameSwitch;
  private Thread gameLoop;
  public AppPanel()
  {
    setBackground(new Color(0, 255, 0));
    gameField=new GameField(0, 0, 500, 300);
    gameFieldBounds=gameField.getBounds();
    ball=new Ball(gameFieldBounds.width/4, gameFieldBounds.height/4, 20);
    ball.setDirection(1, -1);
    ballBounds=ball.getBounds();
    addMouseListener(this);
    timer=new Timer(5000, this);
    timer.addActionListener(this);
    gameLoop=new Thread(this);
    gameSwitch=true;
    gameLoop.start();
  }
  public void run()
  {
    while(gameSwitch==true)
    {
      long currentTime=System.currentTimeMillis();
      checkBallCollision();
      ball.animate(currentTime);
      timer.start();
      if((currentTime-lastRefresh)>refreshRate)    
      {                                                 
        repaint();                                    
        lastRefresh=currentTime;                    
      }
      if(gameLoop.interrupted())
      {
        gameSwitch=false;
      }
    }
  }
  public void paintComponent(Graphics g)
  {    
    super.paintComponent(g);
    draw(g);
  } 
  private void draw(Graphics g)
  {
    g.setColor(new Color(0, 0, 255));
    gameField.draw(g);
    if(pullString.size()>0)
    {
      g.setColor(new Color(0, 0, 0));
      (pullString.get(0)).draw(g);
    }
    g.setColor(new Color(255, 0, 0));
    ball.draw(g);
  }
  private void checkBallCollision()
  {
    double ballDirectionX=ball.getDirectionX();
    double ballDirectionY=ball.getDirectionY();
    int ballXCoordinate=ball.getXCoordinate();
    int ballYCoordinate=ball.getYCoordinate();
    if(ballBounds.y<gameFieldBounds.y)
    {
      ball.collideY(gameFieldBounds.y);
    }
    else if((ballBounds.y+ballBounds.height)>(gameFieldBounds.y+gameFieldBounds.height))
    {
      ball.collideY(gameFieldBounds.y+gameFieldBounds.height-ballBounds.height);
    }
    else if(ballBounds.x<gameFieldBounds.x)
    {
      ball.collideX(gameFieldBounds.x);
    }
    else if((ballBounds.x+ballBounds.width)>(gameFieldBounds.x+gameFieldBounds.width))
    {
      ball.collideX(gameFieldBounds.x+gameFieldBounds.width-ballBounds.width);
    }
  }
  public void mousePressed(MouseEvent e)
  {
    int x=e.getX();
    int y=e.getY();
    if(x>=gameFieldBounds.x && x<=(gameFieldBounds.x+gameFieldBounds.width) && y>=gameFieldBounds.y && y<=(gameFieldBounds.y+gameFieldBounds.height) && pullString.size()==0)
    {
      ball.halt();
      pullString.add(new PullString(x, y, ballBounds.x+ballBounds.width/2, ballBounds.y+ballBounds.height/2));
      repaint();
    }
  }
  public void mouseReleased(MouseEvent e)
  {
    if(pullString.size()>0)
    {
      PullString string=pullString.get(0);
      double xDisplacement=string.getXDisplacement();
      double yDisplacement=string.getYDisplacement();
      pullString.clear();
      ball.setDirection(xDisplacement, yDisplacement);
      ball.changeSpeed(xDisplacement, yDisplacement);
    }
  }
  public void mouseClicked(MouseEvent e)
  {
  }
  public void mouseEntered(MouseEvent e)
  {
  }
  public void mouseExited(MouseEvent e)
  {
  }
  public void actionPerformed(ActionEvent e)
  {
    Object source=e.getSource();
    if(source==timer)
    {
      timer.stop();
      ball.decelerate();
    }
  }
  public void quitGameLoop()
  {
    gameLoop.interrupt();
  }
}