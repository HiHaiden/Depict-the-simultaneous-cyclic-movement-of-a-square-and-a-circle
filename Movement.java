import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class laba3 {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 640;
    private static final int DIAMETER = 26;
    private static final int DIAMETER1 = 18;
    private static final int FREQ = 12;
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color FIGURE_COLOR = Color.magenta;

    private int xPos = 0;
    private int yPos = 0;
    private int xPos1 = 770;
    private int yPos1 = 10;
    private int dX = -1;
    private int dY = -1;
    private int dX1 = -2;
    private int dY1 = -2;
    private final JPanel RectBallPanel;

    laba3() {
        RectBallPanel = new JPanel(){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(WIDTH, HEIGHT);
            }

            @Override
            public void paintComponent(Graphics j) {
                super.paintComponent(j);
                ((Graphics2D)j).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                j.setColor(FIGURE_COLOR);
                j.fillRect(xPos, yPos, DIAMETER, DIAMETER);
                j.fillOval(xPos1, yPos1, DIAMETER1, DIAMETER1);
            }
        };
        RectBallPanel.setOpaque(true);
        RectBallPanel.setBackground(BACKGROUND_COLOR);

        Timer timer = new Timer(FREQ, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if ( xPos < 1 || xPos + DIAMETER > WIDTH - 1 )
                    dX *= -1;
                if ( yPos < 1 || yPos > HEIGHT - DIAMETER - 1 )
                    dY *= -1;
                xPos += dX;
                yPos += dY;
                if ( xPos1 < 1 || xPos1 + DIAMETER > WIDTH - 1 )
                    dX1 *= -1;
                if ( yPos1 < 1 || yPos1 > HEIGHT - DIAMETER - 1 )
                    dY1 *= -1;
                xPos1 += dX1;
                yPos1 += dY1;
                RectBallPanel.repaint();
            }
        });

        JFrame mainFrame = new JFrame("Moving Ball And Rectangle");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(RectBallPanel);
        mainFrame.setSize(WIDTH, HEIGHT + DIAMETER + 4);
        mainFrame.setVisible(true);
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new laba3();
            }
        });
    }
}