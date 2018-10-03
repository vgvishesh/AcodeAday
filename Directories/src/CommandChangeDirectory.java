public class CommandChangeDirectory implements ICommand
{
    private Directory working;

    @Override
    public Directory Execute(final Directory current, final String[] commandData) throws ExceptionInvalidPath, ExceptionInvalidCommand
    {
        working = new Directory(current);
        if(!isCommandDataValid(commandData))
        {
            throw new ExceptionInvalidCommand();
        }

        String destinationPath = commandData[0];

        if(destinationPath.equals("../"))
        {
            return getParentDirectory(current);
        }
        else if(destinationPath.equals("/"))
        {
            return getRootDirectory();
        }
        else
        {
            return goToDirectory(destinationPath);
        }
    }

    private Directory getParentDirectory(final Directory current) throws ExceptionInvalidPath
    {
        Directory parent = current.getParent();
        if(parent == null)
            throw new ExceptionInvalidPath();

        working = parent;
        return working;
    }

    private Directory getRootDirectory()
    {
        while(working.getParent()!=null)
        {
            working = working.getParent();
        }
        return working;
    }

    private Directory goToDirectory(String destinationPath) throws ExceptionInvalidPath
    {
        if(destinationPath.charAt(0) == '/')
        {
            getRootDirectory();
        }

        String[] path = destinationPath.trim().split("/");

        int index = 0;
        while(index < path.length)
        {
            String go = path[index++];

            if(go.equals(".."))
            {
                working = getParentDirectory(working);
            }
            else if(!go.equals(""))
            {
                goToSubDirectory(working.getSubDirectory(go));
            }
        }
        return working;
    }

    private void goToSubDirectory(Directory subDirectory) throws ExceptionInvalidPath
    {
        Directory goDirectory = subDirectory;
        if (goDirectory == null) {
            throw new ExceptionInvalidPath();
        }
        working = goDirectory;
    }

    private boolean isCommandDataValid(String[] commandData)
    {
        return commandData.length > 2 ? false : true;
    }
}
