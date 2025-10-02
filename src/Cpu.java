public class Cpu {
    int program_counter;
    int stack_pointer;
    int registers[] = new int[16];
    char stack[] = new char[16];

    char i_register;
    byte delay_timer, sound_timer;

    Opcodes opcodes = new Opcodes();

    private char get_opcode(Memory memory) {
        int temp = program_counter;
        program_counter += 2;
        return memory.get_short(temp);
    }

    public void step(Memory memory) {
        char opcode = get_opcode(memory);
        char nnn = (char)(opcode & 0x0FFF);
        int n = (opcode & 0x000F);
        int x = (opcode & 0x0F00) >> 8;
        int y = (opcode & 0x00F0) >> 4;
        int kk = (opcode & 0x00FF);

        switch ((opcode & 0xF000) >> 12) {
            case 0x0:
                opcodes.op_00EE(this);
                break;

            case 0x1:
                opcodes.op_1nnn(this, nnn);
                break;

            case 0x2:
                opcodes.op_2nnn(this, nnn);
                break;

            case 0x3:
                opcodes.op_3xkk(this, x, kk);
                break;

            case 0x4:
                opcodes.op_4xkk(this, x, kk);
                break;

            case 0x5:
                opcodes.op_5xy0(this, x, y);
                break;

            case 0x6:
                opcodes.op_6xkk(this, x, kk);
                break;

            case 0x7:
                opcodes.op_7xkk(this, x, kk);
                break;

            case 0x8:
                switch (n) {
                    case 0x0:
                        opcodes.op_8xy0(this, x, y);
                        break;
                }

        }
    }
}
