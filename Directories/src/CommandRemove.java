public class CommandRemove implements ICommand
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
            String deleteDirName = getDeleteDirectoryName(path);
            String deletionLocation = getDeletionLocation(path);

            if(deleteDirName.isEmpty())
                throw new ExceptionInvalidCommand();

            if(!deletionLocation.isEmpty())
            {
                working = commandChangeDirectory.Execute(current, new String[]{deletionLocation});
            }

            working.removeDirectory(deleteDirName);
        }
        return current;
    }

    private String getDeleteDirectoryName(String path)
    {
        return Utils.getFile(path);
    }

    private String getDeletionLocation(String dirPath)
    {
        return Utils.getPath(dirPath);
    }
}
