import java.util.*;

public class Runner
{
    private Directory root = new Directory("/");
    private Directory current;
    private final String changeDirectory = "cd";
    private final String listFiles = "ls";
    private final String presentDirectory = "pwd";
    private final String remove = "rm";
    private final String makeNew = "mkdir";
    private final String clear = "session";

    private ICommand commandChangeDirectory = new CommandChangeDirectory();
    private  ICommand commandMakeDirectory = new CommandMakeDirectory();
    private  ICommand commandRemoveDirectory = new CommandRemove();
    private  ICommand commandPresentDirectory = new CommandPresentDirectory();
    private  ICommand commandListFiles = new CommandListFiles();


    public Runner()
    {
        current = new Directory(root);
    }

    public void ExecuteCommand(String commandLine)
    {
        String command = getCommand(commandLine);
        String[] commandData = getCommandData(commandLine);
        try
        {
            switch (command)
            {
                case changeDirectory:
                    current = commandChangeDirectory.Execute(current, commandData);
                    System.out.println("SUCC: REACHED");
                    break;

                case makeNew:
                    commandMakeDirectory.Execute(current, commandData);
                    System.out.println("SUCC: CREATED");
                    break;

                case remove:
                    commandRemoveDirectory.Execute(current, commandData);
                    System.out.println("SUCC: DELETED");
                    break;

                case listFiles:
                    commandListFiles.Execute(current, commandData);
                    break;

                case presentDirectory:
                    commandPresentDirectory.Execute(current, commandData);
                    break;

                case clear :
                    current = root;
                    break;

                default: System.out.println("ERR: CANNOT RECOGNIZE INPUT.");
                    break;
            }
        }
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    private String[] getCommandData(String commandLine)
    {
        String[] split = commandLine.split(" ");
        String[] data = new String[split.length - 1];

        for(int i =1; i<split.length;i++)
        {
            data[i-1] = split[i];
        }
        return data;
    }

    private String getCommand(String commandLine)
    {
        return commandLine.split(" ")[0];
    }

    private void displayPath()
    {
        System.out.println(getPath());
    }

    private String getPath()
    {
        List<String> names = getDirectoryNamesTillRoot();
        StringBuilder path = new StringBuilder();
        names.forEach(name -> {
            path.append(name);
            path.append("/");
        });
        return path.toString();
    }

    private List<String> getDirectoryNamesTillRoot()
    {
        List<String> names = new ArrayList<>();
        Directory thisDirectory = new Directory(current);
        while(thisDirectory!=null)
        {
            names.add(thisDirectory.getName());
            thisDirectory = thisDirectory.getParent();
        }
        Collections.reverse(names);
        return names;
    }
}
