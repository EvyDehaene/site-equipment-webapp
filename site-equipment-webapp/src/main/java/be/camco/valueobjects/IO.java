package be.camco.valueobjects;

import be.camco.enums.IOType;

public class IO implements Comparable<IO>{
	private IOType ioType;
	private int number;
	private String ioName;
	private String on;
	private String off;
	
	public IO(IOType ioType, int number, String name, String on, String off){
		this.setIoType(ioType);
		this.setNumber(number);
		this.setIoName(name);
		this.setOn(on);
		this.setOff(off);
	}
	
	public IOType getIoType() {
		return ioType;
	}
	public void setIoType(IOType ioType) {
		this.ioType = ioType;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getIoName() {
		return ioName;
	}
	public void setIoName(String name) {
		this.ioName = name;
	}
	public String getOn() {
		return on;
	}
	public void setOn(String on) {
		this.on = on;
	}
	public String getOff() {
		return off;
	}
	public void setOff(String off) {
		this.off = off;
	}

	@Override
	public String toString() {
		return "IO [ioType=" + ioType + ", number=" + number + ", name=" + ioName
				+ ", on=" + on + ", off=" + off + "]";
	}

	@Override
	public int compareTo(IO o) {
		return this.toString().compareTo(o.toString());
	}
	
}
