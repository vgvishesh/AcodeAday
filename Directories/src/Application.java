import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    public static void main(String[] args) throws IOException
    {
        Runner runner = new Runner();
        String command = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(!command.equals("exit"))
        {
            command = reader.readLine();
            runner.ExecuteCommand(command);
        }

    }
}
