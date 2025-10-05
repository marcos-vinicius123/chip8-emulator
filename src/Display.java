import javax.swing.JPanel;
import java.awt.*;

public class Display extends JPanel implements Runnable {
    final int width = 64;
    final int height = 32;
    final int scale = 16;
    final int fps = 5;
    final int screen_width = width * scale;
    final int screen_height = height * scale;
    Thread game_thread;
    KeyHandler key_handler = new KeyHandler();

    Memory memory = new Memory(1024 * 4);
    Cpu cpu = new Cpu();

    public Display() {
        this.setPreferredSize(new Dimension(screen_width, screen_height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(key_handler);
        this.setFocusable(true);
    }


    public void start_thread() {
        game_thread = new Thread(this);
        game_thread.start();
    }

    @Override
    public void run() {
        double interval = 1000000000/fps;
        double next_draw = System.nanoTime() + interval;

        while (game_thread !=  null) {
            update();
            repaint();


            try {
                double remaining_time = next_draw - System.nanoTime();
                remaining_time /= 1000000;
                if (remaining_time < 0) {
                    remaining_time = 0;
                }
                Thread.sleep((long)remaining_time);
                next_draw += interval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void update() {
        try {
            cpu.step(memory);
        } catch (Exception e) {
        }
        //decrement timers at 60z
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(10*scale, 4*scale , scale, scale);
        g2.dispose();
    }


}
