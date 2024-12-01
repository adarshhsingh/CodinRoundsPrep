package machinecoding.filesystem;

import java.util.List;

public class InMemoryFileSystem implements FileSystem {
    private final Directory ROOT;

    public InMemoryFileSystem() {
        ROOT = new Directory("root");
    }
    @Override
    public void createFile(String path, String name) {
        Directory dir = navigateToDirectory(path);
        File file = new File(name);
        dir.addFile(file);
    }

    private Directory navigateToDirectory(String path) {
        String [] directoris = path.split("/");
        Directory current = ROOT;
        for( String dirName : directoris) {
            if(!dirName.isEmpty()) {
                current = current.getDirectories().get(dirName);
                if(current == null) {
                    throw new RuntimeException("Directories does not exist");
                }
            }
        }
        return null;
    }

    @Override
    public void deleteFile(String path) {
        String[] parts = splitPath(path);
        Directory dir = navigateToDirectory(parts[0]);
        dir.removeFile(parts[1]);
    }

    private String[] splitPath(String path) {
        // Logic to split the path into directory path and file/directory name
        int lastSlash = path.lastIndexOf('/');
        return new String[]{path.substring(0, lastSlash), path.substring(lastSlash + 1)};
    }

    @Override
    public File readFile(String path) {
        return null;
    }

    @Override
    public void writeFile(String path, byte[] content) {

    }

    @Override
    public void createDirectory(String path, String name) {

    }

    @Override
    public void deleteDirectory(String path) {

    }

    @Override
    public List<String> ls(String path) {
        return null;
    }

    @Override
    public void mkdir(String path) {

    }

    @Override
    public void addContentToFile(String filePath, String content) {

    }

    @Override
    public String readContentFromFile(String filePath) {
        return null;
    }
}
