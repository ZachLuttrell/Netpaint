package Demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DemoGraphics2D extends JFrame {

  public static void main(String[] args) {
    new DemoGraphics2D().setVisible(true);
   }
   
  private JPanel canvas = new DrawingPanel();

  public DemoGraphics2D() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setSize(screenSize);
    
    add(canvas);
  }
}

class DrawingPanel extends JPanel {

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    // Default color is Color.BLACK
    g2.fillOval(20, 20, 100, 200);

    // Any drawing later will be in this new Color
    g2.setColor(Color.RED);
    // Create a polygon and draw it. Points are 100,600  200,700  200,110  400,120
    int[] xPoints = { 200, 800, 300, 400 };
    int[] yPoints = { 800, 700, 110, 120 };
    g2.fillPolygon(xPoints, yPoints, 4);

    g2.setColor(Color.BLUE);
    g2.drawLine(100, 45, 200, 700);
  }

}
