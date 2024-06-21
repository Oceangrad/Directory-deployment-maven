package domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class File extends AbstractObject {
    private byte[] bytes;

    public File(Path path, String name) {
        this.type = Type.FILE;

        this.path = path;
        this.name = name;

        try {
            this.bytes = Files.readAllBytes(path);
        } catch (IOException e) {
            this.bytes = new byte[0];
            e.printStackTrace();
        }
    }

    public byte[] getBytes() {
        return bytes;
    }
}
