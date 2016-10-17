package com.senla.training.FileWorker;

import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IFileWorker;
import com.senla.training.interfaces.IStorage;
import com.senla.training.model.Book;

public class FileWorker implements IFileWorker {

	@Override
	public void writeToFile(IStorage storage) {
		//wr books
		List<IBook> allBooks = storage.getAllBooks();

		TextFileWorker tfw = new TextFileWorker("d:/Books.txt");

		String[] str = new String[allBooks.size()];

		for (IBook book : allBooks) {
			String bookString = book.bookToString();

			for (int j = 0; j < str.length; j++) {
				if (str[j] == null) {
					str[j] = bookString;

					break;
				}
			}

		}

		tfw.writeToFile(str);
		
		//wr orders
		
		
	}


	@Override
	public void readFromFile(IStorage storage) {
		TextFileWorker tfw = new TextFileWorker("d:/Books.txt");
		String[] booksString = tfw.readFromFile();
		for (int j = 0; j < booksString.length; j++) {
			if (booksString[j] != null) {
				storage.addBook(new Book(booksString[j]));
				
			}
			
		}
	
	}

}
