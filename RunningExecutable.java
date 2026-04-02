import java.io.*;
import java.util.*;

public class RunningExecutable
{
	
	public static boolean execute(String executablePath, String[] arguments)
	{
		
		ArrayList<String> commandAndArguments = new ArrayList<>();

		commandAndArguments.addAll(Arrays.asList(arguments));
		
		Runtime run = Runtime.getRuntime();
		
		try
		{
			Process process = run.exec(arguments);
			printStream(process.getInputStream());
			printStream(process.getErrorStream());

			return true;
		}
		catch (IOException e)
		{
			
		}
		return false;
	}
	
	public static boolean execute(String executablePath, String argument)
	{
		

		String commandAndArgument = executablePath + " " + argument; 
		
		Runtime run = Runtime.getRuntime();
		
		try
		{
			Process process = run.exec(commandAndArgument);
			printStream(process.getInputStream());
			return true;
		}
		catch (IOException e)
		{
			
		}
		return false;
	}
	
	public static void printStream(InputStream inputStream) 
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		
		try
		{
			while ((line = reader.readLine()) != null) 
			{
				System.out.println(line);
			}
		}
		catch (IOException e)
		{
		
		}
	}
}
