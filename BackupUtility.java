import java.util.*;

public class Utility
{
	static int numberOfInstancesOf(char characterToCount, String text)
	{
		int instancesOfCharacterToCount = 0;
		for (int index = 0; index < text.length(); index++)
		{
			if (text.charAt(index) == characterToCount)
			{
				instancesOfCharacterToCount++;
			}
		}
		return instancesOfCharacterToCount;
	}
	
	static String deleteAllOccurences(char characterToReplace, String text)
	{
		for (int index = 0; index < text.length(); index++)
		{
			if (text.charAt(index) == characterToReplace)
			{
				StringBuilder tempText = new StringBuilder(text);
				tempText.deleteCharAt(index);
				text = tempText.toString();
			}
		}
		return text;
	}
	
	static String[] splitFirstWord(String command)
	{
		int lastIndex = 0;
		String[] splittedString = {"", ""};
		for (int i = 0; i < command.length(); i++)
		{
			if (command.charAt(i) == ' ')
			{
				break;
			}
			else
			{
				StringBuilder temp = new StringBuilder(splittedString[0]);
				temp.append(command.charAt(i));
				lastIndex = i;
				splittedString[0] = temp.toString();
			}
		}
		
		for (int i = lastIndex + 1; i < command.length(); i++)
		{
			StringBuilder temp = new StringBuilder(splittedString[1]);
			temp.append(command.charAt(i));
			splittedString[1] = temp.toString();
		}
		
		return splittedString;
	}
	
	public static String[] tokenize(String input) { List<String> tokens = new ArrayList<>(); StringBuilder current = new StringBuilder(); boolean inSingleQuote = false; boolean inDoubleQuote = false; for (int i = 0; i < input.length(); i++) { char c = input.charAt(i); if (c == '\\' && i + 1 < input.length() && !inSingleQuote) { char next = input.charAt(i + 1); if (next == '"' || next == '\\' || next == ' ') { current.append(next); } else { current.append('\\'); current.append(next); } i++; continue; } if (c == '\'' && !inDoubleQuote) { inSingleQuote = !inSingleQuote; continue; } else if (c == '\"' && !inSingleQuote) { inDoubleQuote = !inDoubleQuote; continue; } if (c == ' ' && (!inSingleQuote && !inDoubleQuote)) { if (current.length() > 0) { tokens.add(current.toString()); current.setLength(0); } } else { current.append(c); } } /* if (inQuote) { throw new RuntimeException("You missed closing the ' brother."); } */ if (current.length() > 0) tokens.add(current.toString()); return tokens.toArray(new String[0]); }
	
}
