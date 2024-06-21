package domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class File extends AbstractObject {
    private byte[] bytes;

    public File(String path, String name) {
        this.type = Type.FILE;
        this.path = path;
        this.name = name;
    }

    public File(String path, String name, byte[] bytes) {
        this.type = Type.FILE;
        this.path = path;
        this.name = name;
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }
}
