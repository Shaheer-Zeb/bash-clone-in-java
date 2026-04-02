import java.nio.file.*;

public class ChangeDirectory
{
	static char pathType(String pathToDirectory)
	{
		if (pathToDirectory.charAt(0) == '/')
		{
			return 'a';
		}
		else if (pathToDirectory.charAt(0) != '/' || (pathToDirectory.charAt(0) == '.' && pathToDirectory.charAt(1) == '/' && pathToDirectory.charAt(0) != '~'))
		{
			return 'r';
		}
		return 'n';
	}
	
	static boolean absolutePathExists(String absolutePath)
	{
		Path path = Paths.get(absolutePath);
		
		if (Files.exists(path) && Files.isDirectory(path))
		{
			return true;
		}
		return false;
	}
	
	static boolean relativePathExists(String currentWorkingDirectory, String relativePath)
	{
		Path path = Paths.get(currentWorkingDirectory + "/" + relativePath);
		
		if (Files.exists(path) && Files.isDirectory(path))
		{
			return true;
		}
		return false;
	}
	
	static String goDirectoryBack(String currentWorkingDirectory, String arguments)
	{	
		int directoriesToGoBack = 0;
		
		for (int index = 0; index < arguments.length(); index++)
		{
			if (arguments.charAt(index) == '.')
			{
				directoriesToGoBack++;
			}
		}
		
		directoriesToGoBack /= 2;
		
		for (; directoriesToGoBack > 0; directoriesToGoBack--)
		{
			int lengthOfCWDPath = currentWorkingDirectory.length(); // CWD means the currentWorkingDirectory
			
			int lastOccurenceOfSlash = 0;
			
			for (int index = 0; index < currentWorkingDirectory.length(); index++)
			{
				if (currentWorkingDirectory.charAt(index) == '/')
				{
					lastOccurenceOfSlash = index;
				}
			}
			
			for (int index = lengthOfCWDPath - 1; index >= lastOccurenceOfSlash; index--)
			{
					StringBuilder tempCurrentWorkingDirectory = new StringBuilder(currentWorkingDirectory);
					tempCurrentWorkingDirectory.deleteCharAt(index);
					currentWorkingDirectory = tempCurrentWorkingDirectory.toString();
			}
		}
		return currentWorkingDirectory;
	}
}
