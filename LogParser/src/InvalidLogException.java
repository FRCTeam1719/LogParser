
public class InvalidLogException extends Exception {

	private final String fileName;
	
	public InvalidLogException(String fileName){
		this.fileName = fileName;
	}
	
	public String getMessage(){
		return "The log file: " + fileName + " contains invalid data.";
	}
	
}
