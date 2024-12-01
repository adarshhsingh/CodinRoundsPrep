package leetcode.heirarchy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class File {
    String name;
    int size;

    File(String n, int s) {
        this.name = n;
        this.size = s;
    }
}
class Folder {
    String name;
    int size;
    Folder parent;
    PriorityQueue<File> subFiles;
    PriorityQueue<Folder> subFolders;
    Folder(String name) {
        this.name = name;
        this.parent = null;
        setupDefault();
    }

    Folder(String name, Folder parent) {
        this.name = name;
        this.parent = parent;
        setupDefault();
        parent.addFolder(this);
    }

    void setupDefault() {
        this.size = 0;
        this.subFiles = new PriorityQueue<>(1, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o2.size-o1.size;
            }
        });
        this.subFolders = new PriorityQueue<>(1, new Comparator<Folder>() {
            @Override
            public int compare(Folder o1, Folder o2) {
                return o2.size-o1.size;
            }
        });
    }

    void addFile(File file) {
        subFiles.add(file);
        size+=file.size;
        if(this.parent!=null) {
            parent.subFolders.remove(this);
            parent.subFolders.add(this);
        }
    }

    void addFolder(Folder folder) {
        subFolders.add(folder);
        size+=folder.size;
        if(this.parent!=null) {
            parent.subFolders.remove(this);
            parent.subFolders.add(this);
        }
    }

    public int totalSizeInACollection(Folder folder) {
        int size = 0;
        for(File file: folder.subFiles) {
            size+=file.size;
        }
        size+=totalSizeInASetOfCollection(folder.subFolders);
        return size;
    }

    public int totalSizeInASetOfCollection(PriorityQueue<Folder> folders) {
        int size = 0;
        for(Folder folder: folders) {
            size+=totalSizeInACollection(folder);
        }
        return size;
    }
}

public class TopNFolders {

    public List<String> topNCollections(Folder folder, int n) {
        List<String> topNFolders = new ArrayList<>();
        PriorityQueue<Folder> copySubFolder = new PriorityQueue<>(folder.subFolders);
        while (!copySubFolder.isEmpty() && n>0) {
            topNFolders.add(copySubFolder.poll().name);
            --n;
        }
        return topNFolders;
    }

    public List<String> topNFiles(Folder folder, int n) {
        List<String> topNFiles = new ArrayList<>();
        PriorityQueue<File> copySubFiles = new PriorityQueue<>(folder.subFiles);
        while (!copySubFiles.isEmpty() && n>0) {
            topNFiles.add(copySubFiles.poll().name);
            --n;
        }
        return topNFiles;
    }

    public static void main(String[] args) {
        TopNFolders topN = new TopNFolders();

        Folder ROOT = new Folder("ROOT");
        ROOT.addFile(new File("file1",10));
        ROOT.addFile(new File("file2",20));
        ROOT.addFile(new File("file3",30));

        System.out.println(topN.topNFiles(ROOT, 2));

        Folder downloads = new Folder("downloads", ROOT);
        downloads.addFile(new File("movie1",10));
        downloads.addFile(new File("movie2",20));
        downloads.addFile(new File("movie3",30));

        System.out.println("Size of downloads folder :"+downloads.size);

        Folder documents = new Folder("documents", ROOT);
        documents.addFile(new File("docu1",20));
        documents.addFile(new File("docu2",30));
        documents.addFile(new File("docu3",60));

        System.out.println("Size of documents folder :"+documents.size);

        Folder desktop = new Folder("desktop", ROOT);
        desktop.addFile(new File("desktop1",10));
        desktop.addFile(new File("desktop2",10));
        desktop.addFile(new File("desktop3",10));

        System.out.println("Size of desktop folder :"+desktop.size);

        System.out.println(topN.topNCollections(ROOT, 2));

        desktop.addFile(new File("desktop4",100));

        System.out.println(topN.topNCollections(ROOT, 2));
    }
}
