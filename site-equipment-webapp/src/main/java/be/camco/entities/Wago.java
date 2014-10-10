package be.camco.entities;

import java.util.SortedSet;

import be.camco.enums.IOType;
import be.camco.valueobjects.IO;

public class Wago extends RemoteAccessibleDevice{
	private SortedSet<IO> inputs;
	private SortedSet<IO> outputs;
	private SortedSet<IO> inputsEnOutputs;
	
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
		
	}
	public SortedSet<IO> getInputsEnOutputs(){
		return inputsEnOutputs;
	}
	
	
}
