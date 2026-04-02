/* Author: ShaheerK
   Date: 07/01/26
*/

import java.util.Scanner;
import java.io.*;

public class Main 
{
	static Scanner scanner = new Scanner(System.in);
	
	static String[] commandList = {"exit", "echo", "type", "pwd", "cd", "whothehellami"};
	
	static String currentWorkingDirectory = System.getProperty("user.dir");

	public static void main(String[] args) throws Exception
	{
		while (true)
		{
			// System.out.print(currentWorkingDirectory + "$ "); Apparently the Codecrafters' verifier doesn't like this
			System.out.print("$ ");
			String message = scanner.nextLine();
			String command = "";
			
			// Separating the command and the arguments
			for (int index = 0; index < message.length(); index++)
			{
				if (message.charAt(index) == ' ')
				{
					break;
				}
				else
				{
					command += message.charAt(index);
				}
			}
			
			if (message.equals(""))
			{
				continue;
			}
			
			switch (command)
			{
				case "exit" -> System.exit(0);
				case "echo" ->
				{
					String[] echoArguments = Utility.tokenize(message);
					
					int index = 0;
					for (String i : echoArguments)
					{
						if (index == 0)
						{
							index++;
							continue;
						}
						System.out.print(i + " ");
						
					}
					System.out.println();
				}
				
				case "type" -> 
				{
					String typeArgument = "";
					for (int index = 5; index < message.length(); index++)
					{
						typeArgument += message.charAt(index);
					}
					
					boolean commandFound = false;
					
					for (String i: commandList)
					{
						if (i.equals(typeArgument))
						{
							commandFound = true;
							System.out.println(typeArgument + " is a shell builtin");
						}
					}
					if (commandFound == false)
					{
						String[] path = System.getenv("PATH").split(":");
						
						for (String i : path)
						{
							if (FindFile.executableFound(i, typeArgument))
							{
								System.out.println(typeArgument + " is " + i + "/" + typeArgument);
								commandFound = true;
								break;
							}
						}
					}
					if (!commandFound)
					{
						System.out.println(typeArgument + ": not found");
					}
				}
				
				case "pwd" ->
				{
					System.out.println(currentWorkingDirectory);
				}
				
				case "cd" ->
				{
					String cdArguments = "";
					
					for (int index = 3; index < message.length(); index++)
					{
						cdArguments += message.charAt(index);
					}
					if (cdArguments.contains("..") || cdArguments.contains("../"))
					{
						
						currentWorkingDirectory = ChangeDirectory.goDirectoryBack(currentWorkingDirectory, cdArguments);
						
					}
					else if (cdArguments.equals("~"))
					{
						currentWorkingDirectory = System.getenv("HOME");
					}
					else if (ChangeDirectory.pathType(cdArguments) == 'a')
					{
						if (ChangeDirectory.absolutePathExists(cdArguments))
						{
							currentWorkingDirectory = cdArguments;
						}
						else
						{
							System.out.println("cd: " + cdArguments + ": No such file or directory");
						}
					}
					else if (ChangeDirectory.pathType(cdArguments) == 'r')
					{
						if (ChangeDirectory.relativePathExists(currentWorkingDirectory, cdArguments))
						{
							if (cdArguments.contains("./"))
							{
								cdArguments = cdArguments.replace("./", "");
							}
							String tempCurrentWorkingDirectory = currentWorkingDirectory; 
							currentWorkingDirectory = "";
							currentWorkingDirectory = tempCurrentWorkingDirectory + "/" + cdArguments;
						}
						else
						{
							System.out.println("cd: " + cdArguments + ": No such file or directory");
						}
					}
				}
				
				case "whothehellami" ->
				{
					String mainArgument = "-un";
					RunningExecutable.execute("/usr/bin/id", mainArgument);
				}
				case "ls" ->
				{
					RunningExecutable.execute("/usr/bin/ls", currentWorkingDirectory);
				}
				default ->
				{
					String[] commandArgs = Utility.tokenize(message);
					
					
					String[] path = System.getenv("PATH").split(":");
					boolean executableFound = false;
					
					for (String i : path)
					{
						if (FindFile.executableFound(i, commandArgs[0]))
						{
							executableFound = true;
							
							RunningExecutable.execute(i + "/" + commandArgs[0], commandArgs);
							
							// System.out.println(i + "/" + commandArgs[0]);
							break;
						}
					}
					if (!executableFound)
					{
						System.out.println(commandArgs[0] + ": not found");
					}
				}
			}
		}
	}
}
