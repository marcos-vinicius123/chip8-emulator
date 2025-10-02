public class Opcodes {

    //returns from a subroutine.
    public void op_00EE(Cpu cpu) {
        cpu.program_counter = cpu.stack[--cpu.stack_pointer];
    }

    //jumps to adress nnn.
    public void op_1nnn(Cpu cpu, char nnn) { cpu.program_counter = nnn;}

    //calls subroutine at nnn.
    public void op_2nnn(Cpu cpu, char nnn) {
        cpu.stack[cpu.stack_pointer++] = (char)cpu.program_counter;
        cpu.program_counter = nnn;
    }

    //skips next instruction if Vx == kk.
    public void op_3xkk(Cpu cpu, int x, int kk) {
        cpu.program_counter += cpu.registers[x]==kk ? 2 : 0;
    }

    //skips next instruction if Vx != kk.
    public void op_4xkk(Cpu cpu, int x, int kk) {
        cpu.program_counter += cpu.registers[x]!=kk ? 2 : 0;
    }

    //skips next instruction if Vx == Vy.
    public void op_5xy0(Cpu cpu, int x, int y) {
        cpu.program_counter += cpu.registers[x]==cpu.registers[y] ? 2 : 0;
    }

    //sets Vx = kk.
    public void op_6xkk(Cpu cpu, int x, int kk) {
        cpu.registers[x] = kk;
    }

    //sets Vx = Vx + kk.
    public void op_7xkk(Cpu cpu, int x, int kk) {
        cpu.registers[x] += kk;
        cpu.registers[x] &= 0xff;
    }

    //sets Vx = Vy.
    public void op_8xy0(Cpu cpu, int x, int y) {
        cpu.registers[x] = cpu.registers[y];
    }

    //sets Vx = Vx OR Vy.
    public void op_8xy1(Cpu cpu, int x, int y) {
        cpu.registers[x] |= cpu.registers[y];
    }

    //sets Vx = Vx AND Vy.
    public void op_8xy2(Cpu cpu, int x, int y) {
        cpu.registers[x] &= cpu.registers[y];
    }

    //sets Vx = Vx XOR Vy.
    public void op_8xy3(Cpu cpu, int x, int y) {
        cpu.registers[x] ^= cpu.registers[y];
    }

    //sets Vx = Vx + Vy, sets VF = carry.
    public void op_8xy4(Cpu cpu, int x, int y) {
        int temp = cpu.registers[x] + cpu.registers[y];
        cpu.registers[x] = temp & 0xff;
        cpu.registers[0xf] = temp>0xff ? 1 : 0;
    }

    //sets Vx = Vx - Vy, sets VF = NOT borrow.
    public void op_8xy5(Cpu cpu, int x, int y) {
        int temp = cpu.registers[x] - cpu.registers[y];
        cpu.registers[0xf] = cpu.registers[x] > cpu.registers[y] ? 1 : 0;
        cpu.registers[x] = temp & 0xff;
    }

    //sets Vx = Vx SHR 1.
    public void op_8xy6(Cpu cpu, int x, int y) {
        cpu.registers[0xf] = cpu.registers[x]&0X1;
        cpu.registers[x] /= 2;
        cpu.registers[x] &= 0xff;
    }

    //sets Vx = Vy - Vx, sets VF = NOT borrow.
    public void op_8xy7(Cpu cpu, int x, int y) {
        cpu.registers[0xf] = cpu.registers[y] > cpu.registers[x] ? 1 : 0;
        cpu.registers[x] = (cpu.registers[y] - cpu.registers[x]) & 0xff;
    }

    //sets Vx = Vx SHL 1.
    public void op_8xyE(Cpu cpu, int x, int y) {
        cpu.registers[0xf] = cpu.registers[x]&0x80;
        cpu.registers[x] *= 2;
        cpu.registers[x] &= 0xff;
    }
}
