import javax.swing.JPanel;
import java.awt.*;
import java.io.IOException;

public class Display extends JPanel implements Runnable {
    final int width = 64;
    final int height = 32;
    final int scale = 16;
    final int fps = 60;
    final int screen_width = width * scale;
    final int screen_height = height * scale;
    Thread game_thread;
    KeyHandler key_handler = new KeyHandler();

    int grid[][] = new int[32][64];

    RomLoader rom_loader = new RomLoader();
    Memory memory = new Memory(1024 * 4);
    Cpu cpu = new Cpu();

    public Display() {
        this.setPreferredSize(new Dimension(screen_width, screen_height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(key_handler);
        this.setFocusable(true);

        try {
            rom_loader.load_rom("roms/1-chip8-logo.ch8", memory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void start_thread() {
        game_thread = new Thread(this);
        game_thread.start();
    }

    public void update() {
        try {
            cpu.step(memory, grid);
        } catch (Exception e) {
        }
        //decrement timers at 60z
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        for (int y = 0; y < 32; y++) {
            for (int x = 0; x < 64; x++) {
                if (grid[y][x]==1) {
                    g2.fillRect(x * scale, y * scale, scale, scale);
                }
            }
        }

        g2.dispose();
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


}
