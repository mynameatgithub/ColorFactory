/*
 Author: Teresa Fanchiang
 Date  : 05/08/13
 Program Name: BuildColorFactory.java
 Objective: This program allows
 the user to make any color he wants.
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BuildColorFactory extends JFrame
{
  public final int WIDTH = 1000;
  public final int HEIGHT = 700;
  public final int TITLE_HEIGHT = HEIGHT/10;
  public final int BORDER = 10;
  public final int SPACING = 10;
  public final int THUMB = 12;
  public final int COLOR_MAX = 255;
  public final int SCROLL = 30;
  public final int GRAPH_WIDTH = 60;
  public final int GRAPH_HEIGHT = COLOR_MAX+SPACING;
  public final int LABELS = 13;

  private JPanel jp, p1, p2, p3, p4, p5;
  private ButtonGroup bg;
  private JRadioButton jrb1, jrb2, jrb3, jrb4;
  private JScrollBar sbR, sbG, sbB;
  private JLabel title, red, green, blue, redBar, greenBar, blueBar;

  public BuildColorFactory()
  {
      setTitle("Color Factory");
      setSize(WIDTH,HEIGHT);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      jp = new JPanel();
      setLayout(new BorderLayout());
      buildPanel();
      add(jp, BorderLayout.CENTER);
      setVisible(true);
  }
  private void buildPanel()
  {
      title = new JLabel("Color Factory", JLabel.CENTER);
      title.setFont(new Font("Serif", Font.BOLD, 30));
      jp.setLayout(null);
      title.setBounds(WIDTH/10, BORDER, 4*WIDTH/5, TITLE_HEIGHT);
      jp.add(title);
      p1 = new JPanel();
      p1.setLayout(new GridLayout(4,1)); //Layout for radio buttons.
      p1.setBounds(BORDER, HEIGHT/3, WIDTH/10-BORDER, HEIGHT/3);
      jp.add(p1);
      p2 = new JPanel();
      p2.setLayout(new GridLayout(1,3)); //Layout for bar graphs
      p2.setBounds(WIDTH/3, BORDER+TITLE_HEIGHT+SPACING,
                   3*GRAPH_WIDTH+2*SPACING, GRAPH_HEIGHT);
      jp.add(p2);
      p5 = new JPanel();
      p5.setLayout(new GridLayout(1,3));
      p5.setBounds(WIDTH/3, (BORDER+TITLE_HEIGHT+SPACING+
                             p2.getHeight()), 3*GRAPH_WIDTH+
                             2*SPACING, LABELS);
      jp.add(p5);
      p3 = new JPanel();
      p3.setLayout(new GridLayout(3,2,SPACING,SPACING)); //Layout for scrollbars
      p3.setBounds(BORDER+WIDTH/10+SPACING, title.getHeight()+
                   p2.getHeight()+p5.getHeight()+3*SPACING,
                   WIDTH-BORDER-WIDTH/10, 3*SCROLL+2*SPACING);
      jp.add(p3);
      p4 = new JPanel();
      p4.setLayout(new GridLayout(1,1));
      p4.setBounds(WIDTH-BORDER-WIDTH/5,HEIGHT/4,2*WIDTH/3, 2*WIDTH/3);
      jp.add(p4);

      bg = new ButtonGroup();
      jrb1 = new JRadioButton("Octal", false);
      jrb2 = new JRadioButton("Hex", false);
      jrb3 = new JRadioButton("Decimal", true);
      jrb4 = new JRadioButton("Binary", false);
      bg.add(jrb1);bg.add(jrb2);bg.add(jrb3);bg.add(jrb4);
      //Adding radio buttons to panel container.
      p1.add(jrb1);p1.add(jrb2);p1.add(jrb3);p1.add(jrb4);

      RedBar rc = new RedBar();
      GreenBar gc = new GreenBar();
      BlueBar bc = new BlueBar();

      p2.add(rc);p2.add(gc);p2.add(bc);

      redBar = new JLabel("0", JLabel.CENTER);
      greenBar = new JLabel("0", JLabel.CENTER);
      blueBar = new JLabel("0", JLabel.CENTER);

      p5.add(redBar);p5.add(greenBar);p5.add(blueBar);

      sbR = new JScrollBar(JScrollBar.HORIZONTAL,0,
                           THUMB,0,COLOR_MAX+THUMB);
      sbG = new JScrollBar(JScrollBar.HORIZONTAL,0,
                           THUMB,0,COLOR_MAX+THUMB);
      sbB = new JScrollBar(JScrollBar.HORIZONTAL,0,
                           THUMB,0,COLOR_MAX+THUMB);

      red = new JLabel("Red", JLabel.LEFT);
      green = new JLabel("Green", JLabel.LEFT);
      blue = new JLabel("Blue", JLabel.LEFT);

      p3.add(sbR);p3.add(red);p3.add(sbG);p3.add(green);
      p3.add(sbB);p3.add(blue);

      Oval o = new Oval();
      p4.add(o);
      //CATCHING EVENTS OF RADIO BUTTON SELECTION!
      jrb1.addItemListener(new RadioButtonListener());
      jrb2.addItemListener(new RadioButtonListener());
      jrb3.addItemListener(new RadioButtonListener());
      jrb4.addItemListener(new RadioButtonListener());
      //Catches events for SCROLLBARS.
      sbR.addAdjustmentListener(new ScrollBarListener());
      sbG.addAdjustmentListener(new ScrollBarListener());
      sbB.addAdjustmentListener(new ScrollBarListener());
   }
   class RadioButtonListener implements ItemListener
   {
      //Changes label under respective bar graphs.
      public void itemStateChanged(ItemEvent ie)
      {
         int r = sbR.getValue();  //range of r,g, or b: 0-255
         int g = sbG.getValue();
         int b = sbB.getValue();

         if(jrb1.isSelected())
         {
            redBar.setText(Integer.toOctalString(r));
            greenBar.setText(Integer.toOctalString(g));
            blueBar.setText(Integer.toOctalString(b));
         }
         else if(jrb2.isSelected())
         {
            redBar.setText(Integer.toHexString(r));
            greenBar.setText(Integer.toHexString(g));
            blueBar.setText(Integer.toHexString(b));
         }
         else if(jrb3.isSelected())
         {
            redBar.setText(Integer.toString(r));
            greenBar.setText(Integer.toString(g));
            blueBar.setText(Integer.toString(b));
         }
         else
         {
            redBar.setText(Integer.toBinaryString(r));
            greenBar.setText(Integer.toBinaryString(g));
            blueBar.setText(Integer.toBinaryString(b));
         }
         repaint();
      }
  }
  class RedBar extends JPanel
  {
      public void paintComponent(Graphics g)
      {
          super.paintComponent(g);
          g.setColor(Color.red);
          g.fillRect(SPACING,COLOR_MAX-sbR.getValue(),
                     GRAPH_WIDTH,sbR.getValue());
      }
  }
  class GreenBar extends JPanel
  {
     public void paintComponent(Graphics g)
     {
         super.paintComponent(g);
         g.setColor(Color.green);
         g.fillRect(SPACING,COLOR_MAX-sbG.getValue(),
                    GRAPH_WIDTH,sbG.getValue());
     }
  }
  class BlueBar extends JPanel
  {
     public void paintComponent(Graphics g)
     {
         super.paintComponent(g);
         g.setColor(Color.blue);
         g.fillRect(SPACING,COLOR_MAX-sbB.getValue(),
                    GRAPH_WIDTH,sbB.getValue());
     }
  }
  class Oval extends JPanel
  {
     public void paintComponent(Graphics g)
     {
         super.paintComponent(g);
         g.setColor(Color.black);
         g.setColor(new Color(sbR.getValue(),
                    sbG.getValue(),sbB.getValue()));
         g.fillOval(0,0,100,100);
     }
  }
  class ScrollBarListener implements AdjustmentListener
  {
      //implement action of clicking Scrollbars
      public void adjustmentValueChanged(AdjustmentEvent ae)
      {
         int r = sbR.getValue();
         int g = sbG.getValue();
         int b = sbB.getValue();

         if(jrb1.isSelected())
         {
            redBar.setText(Integer.toOctalString(r));
            greenBar.setText(Integer.toOctalString(g));
            blueBar.setText(Integer.toOctalString(b));
         }
         else if(jrb2.isSelected())
         {
            redBar.setText(Integer.toHexString(r));
            greenBar.setText(Integer.toHexString(g));
            blueBar.setText(Integer.toHexString(b));
         }
         else if(jrb3.isSelected())
         {
            redBar.setText(Integer.toString(r));
            greenBar.setText(Integer.toString(g));
            blueBar.setText(Integer.toString(b));
         }
         else
         {
            redBar.setText(Integer.toBinaryString(r));
            greenBar.setText(Integer.toBinaryString(g));
            blueBar.setText(Integer.toBinaryString(b));
         }
         repaint();
      }
  }
}
