package service;

import domain.Directory;

import java.nio.file.Path;

public class DirectoryService {
    public Directory getDirectory(Path path) {
        return new Directory(path, path.getName(path.getNameCount()-1).toString());
    }
}
