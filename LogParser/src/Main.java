import java.io.IOException;
import java.util.Scanner;

public class Main {

	public void main(String[] args){
		boolean foundFile = false;
		LogFile input;
		Scanner consoleIn = new Scanner(System.in);
		while(!foundFile){
			System.out.println("Input log file name: ");
			String attemptedFile = consoleIn.nextLine();
			try{
				input = new LogFile(attemptedFile);
				foundFile = true;
			}catch(IOException e){
				System.out.println("Error reading file");
			}catch(InvalidLogException e){
				System.out.println(e.getMessage());
			}
		}
		consoleIn.close();
	}
}
