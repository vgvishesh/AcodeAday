import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandPresentDirectory implements ICommand
{
    Directory working;

    @Override
    public Directory Execute(final Directory current, final String[] commandData)
    {
        working = current;
        String path = getPath();
        System.out.println("PATH: " + path);
        return current;
    }

    private String getPath()
    {
        List<String> names = getDirectoryNamesTillRoot();
        StringBuilder path = new StringBuilder(names.get(0));
        names.subList(1, names.size()).forEach(name -> {
            path.append(name);
            path.append("/");
        });

        if(!isAtRoot(path))
            path.deleteCharAt(path.length() - 1);

        return path.toString();
    }

    private boolean isAtRoot(StringBuilder path)
    {
        return path.length() == 1;
    }

    private List<String> getDirectoryNamesTillRoot()
    {
        List<String> names = getNamesFromLeafToRoot();

        Collections.reverse(names);
        return names;
    }

    private List<String> getNamesFromLeafToRoot()
    {
        List<String> names = new ArrayList<>();
        Directory thisDirectory = new Directory(working);
        while(thisDirectory!=null)
        {
            names.add(thisDirectory.getName());
            thisDirectory = thisDirectory.getParent();
        }
        return names;
    }
}
