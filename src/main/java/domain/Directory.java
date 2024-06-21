package domain;

import java.util.List;

public class Directory extends AbstractObject {
    private List<AbstractObject> directoryContainment;

    public Directory(String path, String name) {
        this.type = Type.DIRECTORY;
        this.path = path;
        this.name = name;
    }

    public Directory(String path, String name, List<AbstractObject> directoryContainment) {
        this.type = Type.DIRECTORY;
        this.path = path;
        this.name = name;
        this.directoryContainment = directoryContainment;
    }

    public List<AbstractObject> getDirectoryContainment() {
        return directoryContainment;
    }
}
