import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RomLoader {
    public  void load_rom(String filename, Memory memory) throws IOException {
        Debugger.log("----%s----", filename);
        byte[] rom = Files.readAllBytes(Path.of(filename));
        for (int i = 0; i < rom.length; i++) {
            memory.set_byte(0x200 + i, rom[i]);
            //Debugger.log("0x%X: %02X", 0x200+i,  rom[i] & 0xff);
        }
    }
}
