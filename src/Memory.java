public class Memory {
    private int size;
    private byte rom[];

    public Memory(int size) {
        this.size = size;
        this.rom = new byte[size];
        addFont();
    }

    public byte get_byte(int addr) {
        return rom[addr];
    }

    public char get_addr(int addr) {
        return (char)(get_byte(addr+1) << 8+get_byte(addr));
    }

    public char get_short(int addr) {
        return (char)(get_byte(addr)<<8 + get_byte(addr+1));
    }

    public void set_byte(int addr, byte value) {
        rom[addr] = value;
    }

    public void set_addr(int addr, char value) {
        rom[addr] = (byte) ((byte)value&0x00ff);
        rom[addr+1] = (byte) ((byte)value&0xff00);
    }

    public void set_short(int addr, char value) {
        rom[addr] = (byte) ((byte)value&0xff00);
        rom[addr+1] = (byte) ((byte)value&0x00ff);
    }

    public void addFont() {
        byte[][] font = new byte[][] {
                // 0
                {(byte)0xF0, (byte)0x90, (byte)0x90, (byte)0x90, (byte)0xF0},
                // 1
                {(byte)0x20, (byte)0x60, (byte)0x20, (byte)0x20, (byte)0x70},
                // 2
                {(byte)0xF0, (byte)0x10, (byte)0xF0, (byte)0x80, (byte)0xF0},
                // 3
                {(byte)0xF0, (byte)0x10, (byte)0xF0, (byte)0x10, (byte)0xF0},
                // 4
                {(byte)0x90, (byte)0x90, (byte)0xF0, (byte)0x10, (byte)0x10},
                // 5
                {(byte)0xF0, (byte)0x80, (byte)0xF0, (byte)0x10, (byte)0xF0},
                // 6
                {(byte)0xF0, (byte)0x80, (byte)0xF0, (byte)0x90, (byte)0xF0},
                // 7
                {(byte)0xF0, (byte)0x10, (byte)0x20, (byte)0x40, (byte)0x40},
                // 8
                {(byte)0xF0, (byte)0x90, (byte)0xF0, (byte)0x90, (byte)0xF0},
                // 9
                {(byte)0xF0, (byte)0x90, (byte)0xF0, (byte)0x10, (byte)0xF0},
                // A
                {(byte)0xF0, (byte)0x90, (byte)0xF0, (byte)0x90, (byte)0x90},
                // B
                {(byte)0xE0, (byte)0x90, (byte)0xE0, (byte)0x90, (byte)0xE0},
                // C
                {(byte)0xF0, (byte)0x80, (byte)0x80, (byte)0x80, (byte)0xF0},
                // D
                {(byte)0xE0, (byte)0x90, (byte)0x90, (byte)0x90, (byte)0xE0},
                // E
                {(byte)0xF0, (byte)0x80, (byte)0xF0, (byte)0x80, (byte)0xF0},
                // F
                {(byte)0xF0, (byte)0x80, (byte)0xF0, (byte)0x80, (byte)0x80},
        };

        int index = 0;
        for (byte[] glyph : font) {
            for (byte row : glyph) {
                rom[index++] = row;
            }
        }
    }
}
