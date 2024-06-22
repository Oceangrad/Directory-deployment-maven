package service;

import com.google.gson.internal.LinkedTreeMap;
import domain.AbstractObject;
import domain.Directory;
import domain.File;
import domain.Type;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
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
        System.out.println("Loading file " + path);
//        bytes = getBytesFromPath(Paths.get(path));
        try {
            bytes = upload_Video(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
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

    private byte[] getBytesFromPath(Path path) {
        RandomAccessFile f = null;
        try {
            f = new RandomAccessFile(path.toString(), "r");
            System.out.println("Trying to get bytes from path " + path);
            byte[] b = new byte[(int)f.length()];
            f.readFully(b);
            f.close();
            return b;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] upload_Video (Path path) throws IOException{
        FileInputStream is = new FileInputStream(path.toFile()); //videorecorder stores video to file

        java.nio.channels.FileChannel fc = is.getChannel();
        java.nio.ByteBuffer bb = java.nio.ByteBuffer.allocate(10000);
        byte[] bytes;

        while(fc.read(bb) >= 0){
            bb.flip();
            bb.clear();
        }

        bytes = bb.array();

        bb.clear();
        fc.close();
        is.close();
        return bytes;
    }
}
