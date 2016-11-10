package com.senla.training.interfaces;

public interface IFileWorker {
	public void writeToFile(IStorage storage);

	public void readFromFile(IStorage storage);
}
