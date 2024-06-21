package domain;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Directory extends AbstractObject {
    private List<AbstractObject> directoryContainment;

    public Directory(Path path, String name) {
        this.type = Type.DIRECTORY;

        this.path = path;
        this.name = name;

        File directory = path.toFile();
        this.directoryContainment = Arrays.stream(directory.listFiles())
            .map(obj -> {
                if (obj.isDirectory()) {
                    return new Directory(obj.toPath(), obj.getName());
                }
                else {
                    return new domain.File(obj.toPath(), obj.getName());
                }
            })
            .toList();
    }

    public List<AbstractObject> getDirectoryContainment() {
        return directoryContainment;
    }
}
