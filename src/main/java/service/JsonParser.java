package service;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import domain.Directory;

public class JsonParser {
    Gson gson = new Gson();
    DirectoryService service = new DirectoryService();
    public String dirToJson(Directory directory) {
        return gson.toJson(directory);
    }

    public Directory jsonToDir(String json) {
        LinkedTreeMap<String, Object> map = (LinkedTreeMap<String, Object>) gson.fromJson(json, Object.class);
        return service.createDirectory(map);
    }
}
