import java.io.Serializable;

public class NumberRangeMessage implements Serializable {
	private long startNumber;
	private long endNumber;

	public NumberRangeMessage(long startNumber, long endNumber) {
		this.startNumber = startNumber;
		this.endNumber = endNumber;
	}

	public long getStartNumber() {
		return this.startNumber;
	}
	
	public long getEndNumber(){
		return this.endNumber;
	}
	public void setStartNumber(long startNumber){
		this.startNumber=startNumber;
	}
	public void setEndNumber(long endNumber){
		this.endNumber=endNumber;
	}
}
