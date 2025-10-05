import javax.swing.JPanel;
import java.awt.*;

public class Display extends JPanel implements Runnable {
    final int width = 64;
    final int height = 32;
    final int scale = 16;
    final int screen_width = width * scale;
    final int screen_height = height * scale;
    Thread game_thread;

    public void start_thread() {
        game_thread = new Thread(this);
        game_thread.start();
    }

    @Override
    public void run() {
        while (game_thread !=  null) {
            update();
            repaint();
        }

    }

    public void update() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(10*scale, 4*scale , scale, scale);
        g2.dispose();
    }

    public Display() {
        this.setPreferredSize(new Dimension(screen_width, screen_height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }
}
