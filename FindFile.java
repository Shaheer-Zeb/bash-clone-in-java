import java.io.File;

public class FindFile
{
    public static boolean executableFound(String dirName, String fileName)
    {
        File file = new File(dirName, fileName);

        if (file.exists() && file.isFile() && file.canExecute())
        {
            return true;
        }
        return false;
    }
}

