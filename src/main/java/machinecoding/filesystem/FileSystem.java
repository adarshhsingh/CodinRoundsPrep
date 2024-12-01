package machinecoding.filesystem;

import java.util.List;

public interface FileSystem {
    void createFile(String path, String name);
    void deleteFile(String path);
    File readFile(String path);
    void writeFile(String path, byte[] content);
    void createDirectory(String path, String name);
    void deleteDirectory(String path);
    List<String> ls(String path);
    void mkdir(String path);
    void addContentToFile(String filePath, String content);
    String readContentFromFile(String filePath);
}
