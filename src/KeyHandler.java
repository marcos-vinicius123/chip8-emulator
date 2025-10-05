import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import java.util.HashMap;

public class KeyHandler implements KeyListener {
    private Map<Integer, Integer> keys_table = new HashMap<>();
    private Map<Integer, Boolean> keys = new HashMap<>();

    public KeyHandler() {
        keys_table.put(KeyEvent.VK_1, 0x1);
        keys_table.put(KeyEvent.VK_2, 0x2);
        keys_table.put(KeyEvent.VK_3, 0x3);
        keys_table.put(KeyEvent.VK_4, 0xc);

        keys_table.put(KeyEvent.VK_Q, 0x4);
        keys_table.put(KeyEvent.VK_W, 0x5);
        keys_table.put(KeyEvent.VK_E, 0x6);
        keys_table.put(KeyEvent.VK_R, 0xd);

        keys_table.put(KeyEvent.VK_A, 0x7);
        keys_table.put(KeyEvent.VK_S, 0x8);
        keys_table.put(KeyEvent.VK_D, 0x9);
        keys_table.put(KeyEvent.VK_F, 0xe);

        keys_table.put(KeyEvent.VK_Z, 0xa);
        keys_table.put(KeyEvent.VK_X,  0x0);
        keys_table.put(KeyEvent.VK_C, 0xb);
        keys_table.put(KeyEvent.VK_V,  0xf);

        for (int i = 0; i < 16; i++) {
            keys.put(i, false);
        }
    }

    public boolean get_key(int key) {
        return keys.get(key);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (!keys_table.containsKey(code)) { return; }
        int key = keys_table.get(code);
        keys.put(key, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (!keys_table.containsKey(code)) { return; }
        int key = keys_table.get(code);
        keys.put(key, false);
        System.out.println("released: "+keys_table.get(code));
    }
}
