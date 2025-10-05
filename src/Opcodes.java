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

    //skips next instruction if Vx != Vy.
    public void op_9xy0(Cpu cpu, int x, int y) {
        cpu.program_counter += cpu.registers[x] != cpu.registers[y] ? 2 : 0;
    }

    //sets I = nnn;
    public void op_Annn(Cpu cpu, int nnn) {
        cpu.i_register = (char)nnn;
    }

    //jumps to location nnn + V0.
    public void op_Bnnn(Cpu cpu, int nnn) {
        cpu.program_counter = nnn + cpu.registers[0];
        cpu.program_counter &= 0xffff;
    }

    //sets Vx = random byte AND kk.
    public void op_Cxkk(Cpu cpu, int x, int kk) {
        cpu.registers[x] = (int)(Math.random()*255) & kk;
    }

    //draws n-byte sprite at memory location I at (Vx, Vy), sets VF = collision.
    public void op_Dxyn(Cpu cpu, int x, int y, int n) {

    }

    //skips next instruction if key with value of Vx is pressed.
    public void op_Ex9E(Cpu cpu, int x) {

    }

    //skips next instruction if key with value of Vx is not pressed.
     public void op_ExA1(Cpu cpu, int x) {

     }

    //sets Vx = delay timer.
    public void op_Fx07(Cpu cpu, int x) {
        cpu.registers[x] = cpu.delay_timer;
    }

    //waits for a key press, stores the value of the key in Vx.
    public void op_Fx0A(Cpu cpu, int x) {

    }

    //sets delay timer = Vx.
    public void op_Fx15(Cpu cpu, int x) {
        cpu.delay_timer = cpu.registers[x];
    }

    //sets sound timer = Vx.
    public void op_Fx18(Cpu cpu, int x) {
        cpu.sound_timer = cpu.registers[x];
    }

    //sets I = I + Vx.
    public void op_Fx1E(Cpu cpu, int x) {
        cpu.i_register += cpu.registers[x];
    }

    //sets I = location of sprite for digit Vx.
    public void op_Fx29(Cpu cpu, int x) {
        cpu.i_register = (char)(x*5); //every sprite is 5 bytes high.
    }

    //stores BCD representation of Vx in memory locations I, I+1, and I+2.
    public void op_Fx33(Cpu cpu, Memory memory, int x) {
        memory.set_byte((int)cpu.i_register,  (byte)(cpu.registers[x]/100));
        memory.set_byte((int)cpu.i_register+1,  (byte)(cpu.registers[x]/10-cpu.registers[x]%10));
        memory.set_byte((int)cpu.i_register+2,  (byte)(cpu.registers[x]%10));
    }

    //stores registers V0 through Vx in memory starting at location I.
    public void op_Fx55(Cpu cpu, Memory memory, int x) {
        for (int i  = 0; i < x; i++) {
            memory.set_byte((int)cpu.i_register+i, (byte)cpu.registers[i]);
        }
    }

    //read registers V0 through Vx from memory starting at location I.
    public void op_Fx65(Cpu cpu, Memory memory, int x) {
        for (int i  = 0; i < x; i++) {
            cpu.registers[i] = (int)memory.get_byte((int)cpu.i_register+i);
        }
    }
}
