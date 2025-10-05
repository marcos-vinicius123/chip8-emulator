import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("chip-8 emulator");

        Display display  = new Display();
        frame.add(display);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        display.start_thread();
    }
}