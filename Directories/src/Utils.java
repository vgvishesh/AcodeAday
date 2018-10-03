public class Utils
{
    static public String getFile(String path)
    {
        int index = path.lastIndexOf("/");
        if(index == -1)
        {
            return path;
        }
        else
        {
            return path.substring(index+1);
        }
    }

    static public String getPath(String path)
    {
        int index = path.lastIndexOf("/");
        if(index == -1)
        {
            return "";
        }
        return path.substring(0, index+1);
    }
}
