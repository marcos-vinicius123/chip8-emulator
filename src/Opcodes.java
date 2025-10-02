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
    public void op_3xkk(Cpu cpu, byte x, byte kk) {
        cpu.program_counter += cpu.registers[x]==kk ? 2 : 0;
    }

    //skips next instruction if Vx != kk.
    public void op_4xkk(Cpu cpu, byte x, byte kk) {
        cpu.program_counter += cpu.registers[x]!=kk ? 2 : 0;
    }

    //skips next instruction if Vx == Vy.
    public void op_5xy0(Cpu cpu, byte x, byte y) {
        cpu.program_counter += cpu.registers[x]==cpu.registers[y] ? 2 : 0;
    }

    //sets Vx = kk.
    public void op_6xkk(Cpu cpu, byte x, byte kk) {
        cpu.registers[x] = kk;
    }

    //sets Vx = Vx + kk.
    public void op_7xkk(Cpu cpu, byte x, byte kk) {
        cpu.registers[x] += kk;
    }

    //sets Vx = Vy.
    public void op_8xy1(Cpu cpu, byte x, byte y) {
        cpu.registers[x] = cpu.registers[y];
    }
}
