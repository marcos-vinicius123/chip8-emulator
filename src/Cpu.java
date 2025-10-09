public class Cpu {
    int program_counter = 0x200;
    int stack_pointer;
    int registers[] = new int[16];
    char stack[] = new char[16];

    char i_register;
    int delay_timer, sound_timer;

    Opcodes opcodes = new Opcodes();

    private char get_opcode(Memory memory) {
        int temp = program_counter;
        program_counter += 2;
        return memory.get_short(temp);
    }

    public void step(Memory memory, int[][] grid) {
        char opcode = get_opcode(memory);
        char nnn = (char)(opcode & 0x0FFF);
        int n = (opcode & 0x000F);
        int x = (opcode & 0x0F00) >> 8;
        int y = (opcode & 0x00F0) >> 4;
        int kk = (opcode & 0x00FF);
        Debugger.log("0x%X: 0x%X", program_counter-2, opcode&0xffff);
        //System.out.printf("adress: %X%n | %X%n, | %X%n\n", program_counter-2, (int)opcode, (int)memory.get_short(program_counter-2));
        if (delay_timer > 0) {
            delay_timer--;
        }

        if (sound_timer > 0) {
            sound_timer--;
        }

        switch ((opcode & 0xF000) >> 12) {
            case 0x0:
                if (kk==0xE0) {
                    opcodes.op_00E0(this, grid);
                    System.out.println("cls");
                } else if (kk==0xEE) {
                    opcodes.op_00EE(this);
                } else {
                    System.out.printf("error: unknown opcode %X%n.\n", (int)opcode);
                }
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

                    case 0x1:
                        opcodes.op_8xy1(this, x, y);
                        break;

                    case 0x2:
                        opcodes.op_8xy2(this, x, y);
                        break;

                    case 0x3:
                        opcodes.op_8xy3(this, x, y);
                        break;

                    case 0x4:
                        opcodes.op_8xy4(this, x, y);
                        break;

                    case 0x5:
                        opcodes.op_8xy5(this, x, y);
                        break;

                    case 0x6:
                        opcodes.op_8xy6(this, x, y);
                        break;

                    case 0x7:
                        opcodes.op_8xy7(this, x, y);
                        break;

                    case 0xe:
                        opcodes.op_8xyE(this, x, y);
                        break;

                    default:
                        System.out.printf("error: unknown opcode %X%n.\n", (int)opcode);
                        break;
                }

            case 0x9:
                opcodes.op_9xy0(this, x, y);
                break;

            case 0xa:
                opcodes.op_Annn(this, nnn);
                break;

            case 0xb:
                opcodes.op_Bnnn(this, nnn);
                break;

            case 0xc:
                opcodes.op_Cxkk(this, x, kk);
                break;

            case 0xd:
                opcodes.op_Dxyn(this, memory, grid, x, y, n);
                break;

            case 0xe:
                if (kk==0x9E) {
                    opcodes.op_Ex9E(this, x);
                } else if (kk==0xA1) {
                    opcodes.op_ExA1(this, x);
                } else {
                    System.out.printf("error: unknown opcode %X%n.\n", (int)opcode);
                }
                break;

            case 0xf:
                switch (kk) {
                    case 0x07:
                        opcodes.op_Fx07(this, x);
                        break;

                    case 0x0A:
                        opcodes.op_Fx0A(this, x);
                        break;

                    case 0x15:
                        opcodes.op_Fx15(this, x);
                        break;

                    case 0x18:
                        opcodes.op_Fx18(this, x);
                        break;

                    case 0x1E:
                        opcodes.op_Fx1E(this, x);
                        break;

                    case 0x29:
                        opcodes.op_Fx29(this, x);
                        break;

                    case 0x33:
                        opcodes.op_Fx33(this, memory, x);
                        break;

                    case 0x55:
                        opcodes.op_Fx55(this, memory, x);
                        break;

                    case 0x65:
                        opcodes.op_Fx65(this, memory, x);
                        break;

                    default:
                        System.out.printf("error: unknown opcode %X%n.\n", (int)opcode);
                        break;
                }
            default:
                System.out.printf("error: unknown opcode %X%n.\n", (int)opcode);
                break;
        }
    }
}
