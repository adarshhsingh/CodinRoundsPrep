package datastructures;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

class File {
    String name;
    int size;
    public File(String n, int s) {
        this.name = n;
        this.size = s;
    }
}
class Folder {
    String name;
    PriorityQueue<File> files;
    int size;
    Folder parent;
    PriorityQueue<Folder> subFolders;
    public Folder(String name, Folder parent) {
        setupDefaults(name);
        if (parent != null) parent.addFolder(this);
    }
    public void setupDefaults(String name) {
        this.name = name;
        this.parent = null;
        size = 0;
        files = new PriorityQueue<>(1, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o2.size - o1.size;
            }
        });
        subFolders = new PriorityQueue<>(1, new Comparator<Folder>() {
            @Override
            public int compare(Folder o1, Folder o2) {
                return o2.size - o1.size;
            }
        });
    }
    void addFile(File file) {
        files.add(file);
        System.out.printf("\n file %s of size %d added in %s", file.name, file.size, this.name);
        reCalculateSize();
    }
    void addFolder(Folder folder) {
        System.out.printf("\n folder %s of size %d added in %s", folder.name, folder.size, this.name);
        Folder curretParent = folder.parent;
        if (curretParent != null) {
            curretParent.subFolders.remove(folder);
            reCalculateSize();
        }
        subFolders.add(folder);
        folder.parent = this;
        reCalculateSize();
    }

    void reCalculateSize() {
        AtomicInteger size = new AtomicInteger(0);
        if (!files.isEmpty()) {
            files.forEach(file -> {
                size.addAndGet(file.size);
            });
        }
        if (!subFolders.isEmpty())
            subFolders.forEach(folder -> {
               size.addAndGet(folder.size);
            });
        this.size = size.get();
        System.out.printf("\n Folder %s size changed to %d", this.name, this.size);
        if(parent != null) parent.reCalculateSize();
    }

    void updateSize(File file) {
        this.size = this.size + file.size;
        System.out.printf("\n Folder %s size changed to %d", this.name, this.size);
        if(parent != null) parent.reCalculateSize();
    }

    void updateSize(Folder folder) {
        this.size = this.size + folder.size;
        System.out.printf("\n Folder %s size changed to %d", this.name, this.size);
        if(parent != null) parent.reCalculateSize();
    }


}

public class TotalSizeOfAllFoldersAndFiles {
    public static void main(String[] args) {

        Folder ROOT = new Folder("ROOT", null);
// Create files
        File file1 = new File("file1.txt", 500);
        File file2 = new File("file2.txt", 200);
        File file3 = new File("file3.txt", 900);
        File file4 = new File("file4.txt", 600);
        ROOT.addFile(file4);

        // Create folders and add files to them
        Folder folder1 = new Folder("folder1", ROOT);
        folder1.addFile(file1);
        folder1.addFile(file2);

        Folder folder2 = new Folder("folder2",folder1);
        folder2.addFile(file3);

        // Calculate total size
        int totalSize = ROOT.size;
        System.out.println("\n Total size: " + totalSize); // Expected output: 600
        topNFilesInFolder(ROOT, 1);
        topNFilesInFolder(folder1, 1);
        topNFolderInFolder(ROOT, 1);
    }

    public static void topNFilesInFolder(Folder folder, int N) {
        // Your implementation here
        System.out.println("topNFilesInFolder in " + folder.name + " : ");
        PriorityQueue<File> files = folder.files;
        while (N > 0 && !files.isEmpty()) {
            System.out.println(files.poll().name);
            N--;
        }
    }

    public static void topNFolderInFolder(Folder folder, int N) {
        // Your implementation here
        System.out.println("topNFolderInFolder in " + folder.name + " : ");
        PriorityQueue<Folder> folders = folder.subFolders;
        while (N > 0 && !folders.isEmpty()) {
            System.out.println(folders.poll().name);
            N--;
        }
    }
}
