package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.training.DI.DI;
import com.senla.training.exceptions.MyException;
import com.senla.training.interfaces.IFacade;
import com.senla.training.model.Book;

public class BooksServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4993647477136586611L;
	private final Logger log = Logger.getLogger(BooksServlet.class);
	private ObjectMapper mapper;
	private IFacade facade;

	public void init() {
		mapper = new ObjectMapper();
		facade = (IFacade) DI.load(IFacade.class);
		facade.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			if (request.getParameter("id") != null) {
				Book book = facade.viewBookDescription(Integer.valueOf(request.getParameter("id")));
				mapper.writeValue(out, book);
			} else if (request.getParameter("showUnwanted") != null) {
				List<Book> books = facade.showUnwantedBooks(request.getParameter("criterion"));
				mapper.writeValue(out, books);
			} else {
				List<Book> books = facade.showAllBooks(request.getParameter("criterion"));
				mapper.writeValue(out, books);
			}
		} catch (IOException | MyException e) {
			log.error(e.getMessage());
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			Book book = mapper.readValue(request.getReader(), Book.class);
			facade.addBook(book);
		} catch (IOException | MyException e) {
			log.error(e.getMessage());
		}
	}

	public void doPut(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getParameter("writeToCSV") != null) {
				facade.writeBooksToCsv();
			} else if (request.getParameter("readFromCSV") != null) {
				facade.readBooksFromCsv();
			} else {
				Book book = mapper.readValue(request.getReader(), Book.class);
				facade.updateBook(book);
			}
		} catch (IOException | MyException e) {
			log.error(e.getMessage());
		}
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response) {
		try {
			facade.deleteBook(Integer.valueOf(request.getParameter("id")));
		} catch (MyException e) {
			log.error(e.getMessage());
		}
	}
}
