public class CommandListFiles implements ICommand
{
    @Override
    public Directory Execute(Directory current, String[] commandData) throws ExceptionInvalidCommand
    {
        if(!isCommandDataValid(commandData))
        {
            throw new ExceptionInvalidCommand();
        }

        System.out.print("DIRS: ");
        current.getAllSubDirectoryNames().forEach(name -> System.out.print(name + " "));
        System.out.println();
        return current;
    }

    private boolean isCommandDataValid(String[] commandData)
    {
        return commandData.length == 0;
    }
}
