package machinecoding.filesystem;

import java.util.HashMap;
import java.util.Map;

public class Directory {
    private final String name;
    private final Map<String, File> files;
    private final Map<String, Directory> directories;

    public Directory(String name) {
        this.name = name;
        this.files = new HashMap<>();
        this.directories = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addFile(File file) {
        files.put(file.getName(), file);
    }

    public void addDirector(Directory directory) {
        directories.put(directory.getName(), directory);
    }

    public void removeFile(String fileName) {
        files.remove(fileName);
    }

    public void removeDirectory(String directoryName) {
        directories.remove(directoryName);
    }

    public Map<String, File> getFiles() {
        return files;
    }
    public Map<String, Directory> getDirectories() {
        return directories;
    }
}
