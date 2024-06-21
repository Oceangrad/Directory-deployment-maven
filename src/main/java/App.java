import domain.Directory;
import service.DirectoryService;

import java.io.File;

public class App
{
    // D:\IdeaProjects\Directory deployment\directories\
    public static void main(String[] args) {
        DirectoryService service = new DirectoryService();
        Directory directory = service.getDirectory(new File("D:\\IdeaProjects\\Directory deployment\\directories").toPath());
        System.out.println("Deployed directory successfully");
    }
}
