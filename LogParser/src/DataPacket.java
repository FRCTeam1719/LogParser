
public class DataPacket {

	private final byte[] data;
	//Offset that the voltage data starts at
	private final int OFFSET = 7;
	
	public DataPacket(byte[] data){
		this.data = data;
	}
	
	public double getVoltage(int channel){
		return (double) data[channel + OFFSET];
	}
	
}
