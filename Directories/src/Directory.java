import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Directory {
    private Directory parent = null;
    private String name = "";
    private HashMap<String, Directory> directories = new HashMap<>();

    public Directory(String name) {
        this.name = name;
    }

    public Directory(final Directory source)
    {
        this.parent = source.parent;
        this.name = source.name;
        this.directories = source.directories;
    }

    public String getName()
    {
        return name;
    }

    public Directory getParent()
    {
        return parent;
    }

    public Directory getSubDirectory(String dirName)
    {
        if(directories.containsKey(dirName))
        {
            return directories.get(dirName);
        }
        else
            return null;
    }

    public void addDirectory(String name) throws ExceptionDirectoryExists
    {
        Directory newDir = new Directory(name);
        newDir.parent = this;
        if(this.directories.containsKey(name))
            throw new ExceptionDirectoryExists();

        this.directories.put(name, newDir);
    }

    public List<String> getAllSubDirectoryNames()
    {
        List<String> names = new ArrayList<>();
        directories.forEach((name, directory) -> names.add(name));
        return names;
    }

    public void removeDirectory(String name) throws ExceptionInvalidPath
    {
        if(!directories.containsKey(name))
        {
            throw new ExceptionInvalidPath();
        }
        directories.remove(name);
    }
}
