import domain.Directory;
import service.DirectoryService;
import service.JsonParser;

public class App
{
    // D:\IdeaProjects\Directory deployment\directories\
    public static void main(String[] args) {
        DirectoryService service = new DirectoryService();
        JsonParser parser = new JsonParser();

        Directory directory = service.createDirectory("D:\\IdeaProjects\\Directory-deployment-maven\\directories");
        String json = parser.dirToJson(directory);

        System.out.println(json);

        Directory directory1 = parser.jsonToDir(json);
        System.out.println(directory1.getDirectoryContainment().size());
    }
}
