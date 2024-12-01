package machinecoding.filesystem;

public class File {
    private final String name;
    private String content;

    public File(String name) {
        this.name = name;
        this.content = null;
    }

    public void write(String data) {
        this.content = data;
    }

    public void append(String data) {
        if(content != null) this.content += data;
        else write(data);
    }

    public String getName() {
        return name;
    }
}
