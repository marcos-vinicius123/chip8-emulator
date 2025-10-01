public class Cpu {
    private int program_counter;
    private int stack_pointer;
    private byte registers[] = new byte[16];
    private char stack[] = new char[16];

    private char i_register;
    private byte delay_timer, sound_timer;

    private char get_opcode(Memory memory) {
        int temp = program_counter;
        program_counter += 2;
        return memory.get_short(temp);
    }

    public void step(Memory memory) {
        char opcode = get_opcode(memory);
        char nnn = (char)(opcode & 0x0FFF);
        char n = (char)(opcode & 0x000F);
        char x = (char)(opcode & 0x0F00);
        char y = (char)(opcode & 0x00F0);

        switch (opcode & 0xF000) {
            case 0x0:
                break;

            case 0x1:
                break;

            case 0x2:
                break;

            case 0x3:
                break;

            case 0x4:
                break;

            case 0x6:
                break;
        }
    }
}
