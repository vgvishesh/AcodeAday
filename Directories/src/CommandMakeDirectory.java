public class CommandMakeDirectory implements ICommand
{
    private ICommand commandChangeDirectory = new CommandChangeDirectory();
    /**
     * - go the correct directory and create a new one there.
     * @param current
     * @param commandData
     * @return
     */
    @Override
    public Directory Execute(final Directory current, final String[] commandData) throws ExceptionInvalidCommand, ExceptionInvalidPath, ExceptionDirectoryExists
    {
        for(int i = 0; i < commandData.length; i++)
        {
            Directory working = current;
            String path = commandData[i].trim();
            String newDirectoryName = getCreationDirectoryName(path);
            String creationLocation = getCreationLocation(path);

            if(newDirectoryName.isEmpty())
                throw new ExceptionInvalidCommand();

            if(!creationLocation.isEmpty())
            {
                working = commandChangeDirectory.Execute(current, new String[]{creationLocation});
            }

            working.addDirectory(newDirectoryName);
        }
        return current;
    }

    private String getCreationDirectoryName(String path)
    {
        return Utils.getFile(path);
    }

    private String getCreationLocation(String dirPath)
    {
        return Utils.getPath(dirPath);
    }
}
