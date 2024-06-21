package service;

import com.google.gson.internal.LinkedTreeMap;
import domain.AbstractObject;
import domain.Directory;
import domain.File;
import domain.Type;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class DirectoryService {
    public Directory createDirectory(String path) { // From real directory on computer
        Path path1 = Paths.get(path);
        java.io.File dir = new java.io.File(path);
        List<AbstractObject> objects = Arrays.stream(dir.listFiles())
                .map(obj -> {
                    if (obj.isDirectory()) {
                        return createDirectory(obj.getPath());
                    } else {
                        return createFile(obj.getPath());
                    }
                })
                .toList();

        return new Directory(path, path1.getName(path1.getNameCount() - 1).toString(), objects);
    }
    public Directory createDirectory(LinkedTreeMap<String, Object> map) { // From json
        String name = (String) map.get("name");
        String path = (String) map.get("path");
        List<LinkedTreeMap<String, Object>> directoryContainment = (List<LinkedTreeMap<String, Object>>) map.get("directoryContainment");

        List<AbstractObject> objects = directoryContainment.stream()
                .map(obj -> {
                    Type objType = Type.valueOf((String) obj.get("type"));
                    if (objType == Type.DIRECTORY) {
                        return createDirectory(obj);
                    } else {
                        return createFile(obj);
                    }
                })
                .toList();

        Directory directory = new Directory(name, path, objects);
        return directory;
    }

    public File createFile(String path) { // From real file on computer
        Path path1 = Paths.get(path);
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            bytes = new byte[0];
            e.printStackTrace();
        }
        return new File(path, path1.getName(path1.getNameCount() - 1).toString(), bytes);
    }

    public File createFile(LinkedTreeMap<String, Object> map) { // From json
        String name = (String) map.get("name");
        String path = (String) map.get("path");
        byte[] bytes = doubleToBytes((List<Double>) map.get("bytes"));

        return new File(path, name, bytes);
    }

    private byte[] doubleToBytes(List<Double> list) {
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i).byteValue();
        }

        return bytes;
    }
}
