import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LogFile {

	public final String fileName;
	//TODO populate constants with real values
	private final int NUM_CHANNELS = 15;
	private final int START_POINT = 19;
	private final int PACKET_SIZE = 18;
	private final byte[] buffer;
	private ArrayList<ArrayList<Double>> voltages = new ArrayList<ArrayList<Double>>();
	
	public LogFile(String fileName) throws IOException, InvalidLogException{
		this.fileName = fileName;
		Path path = Paths.get(fileName);
		buffer = Files.readAllBytes(path);
		//Populate top level array
		for(int i=0;i<NUM_CHANNELS;i++){
			voltages.add(new ArrayList<Double>());
		}
		parse();
	}
	
	public byte[] getRawFile(){
		return buffer;
	}
	
	public int getRawLengh(){
		return buffer.length;
	}
	
	//Parse the voltages out of the data
	private void parse() throws InvalidLogException{
		int i = START_POINT;
		while(i<buffer.length){
			byte newPacket[] = new byte[PACKET_SIZE];
			for(int j=0;j<PACKET_SIZE;j++){
				newPacket[j] = buffer[i];
				i++;
			}
			DataPacket pack = new DataPacket(newPacket);
			for(int j=0;j<NUM_CHANNELS;j++){
				voltages.get(j).add(pack.getVoltage(j));
			}
		}
		//Verify that all the channels have the same amount of data
		if(!verify(NUM_CHANNELS-1)){
			throw new InvalidLogException(fileName);
		}
	}
	
	private boolean verify(int channel){
		if(channel==0){
			return true;
		}else{
			boolean val = voltages.get(channel).size() == voltages.get(channel-1).size();
			return val && verify(channel - 1);
		}
	}
	
	
}
