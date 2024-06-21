package domain;

import java.nio.file.Path;

public abstract class AbstractObject {
    protected Type type;
    protected String name;
    protected Path path;

    public Type getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public Path getPath() {
        return path;
    }
}
