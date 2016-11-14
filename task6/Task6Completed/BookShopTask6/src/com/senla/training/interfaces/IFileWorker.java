package com.senla.training.interfaces;

public interface IFileWorker {
	public void writeBooksToCsv(IStorage storage);
	public void writeOrdersToCsv(IStorage storage);
	public void writePreordersToCsv(IStorage storage);
	
	public void readBooksFromCsv(IStorage storage);
	public void readOrdersFromCsv(IStorage storage);
	public void readPreordersFromCsv(IStorage storage);
}
