public interface ICommand {
    Directory Execute(final Directory current, final String[] commandData) throws ExceptionInvalidPath, ExceptionInvalidCommand, ExceptionDirectoryExists;
}
