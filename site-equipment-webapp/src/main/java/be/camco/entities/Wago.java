package be.camco.entities;

import java.util.SortedSet;
import java.util.TreeSet;

import be.camco.enums.IOType;
import be.camco.valueobjects.IO;

public class Wago extends RemoteAccessibleDevice{
	private SortedSet<IO> inputs = new TreeSet<>();
	private SortedSet<IO> outputs = new TreeSet<>();
	private SortedSet<IO> inputsEnOutputs = new TreeSet<>();
	
	public SortedSet<IO> getInputs() {
		return inputs;
	}
	public void setInputs(SortedSet<IO> inputs) {
		this.inputs = inputs;
	}
	public SortedSet<IO> getOutputs() {
		return outputs;
	}
	public void setOutputs(SortedSet<IO> outputs) {
		this.outputs = outputs;
	}
	
	public void addIO(IO io){
		if (io.getIoType()==IOType.INPUT){
			inputs.add(io);
		} else if (io.getIoType()==IOType.OUTPUT){
			outputs.add(io);
		}
		inputsEnOutputs.add(io);
	}
	
	public void removeIO(IO io){
		if (inputs.contains(io)){
			inputs.remove(io);
		} else if (outputs.contains(io)){
			outputs.remove(io);
		}
	}
	public void setInputsEnOutputs(SortedSet<IO> setIOs) {
		inputsEnOutputs=setIOs;
		for (IO io:setIOs){
			if (io.getIoType()==IOType.INPUT){
				inputs.add(io);
			}
			if (io.getIoType()==IOType.OUTPUT){
				outputs.add(io);
			}
		}
		
	}
	public SortedSet<IO> getInputsEnOutputs(){
		return inputsEnOutputs;
	}
	@Override
	public String toString() {
		return "Wago [inputs=" + inputs + ", outputs=" + outputs
				+ ", inputsEnOutputs=" + inputsEnOutputs + "]";
	}
	
	public int compareTo(Wago wago){
		return this.toString().compareTo(wago.toString());
	}
}
