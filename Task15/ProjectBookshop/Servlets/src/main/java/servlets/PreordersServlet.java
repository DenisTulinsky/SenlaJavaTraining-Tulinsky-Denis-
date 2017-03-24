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
import com.senla.training.interfaces.IFacade;
import com.senla.training.model.Preorder;

public class PreordersServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8089982937146603778L;
	private final Logger log = Logger.getLogger(PreordersServlet.class);
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
			List<Preorder> preorders = facade.showPreorders(request.getParameter("criterion"));
			mapper.writeValue(out, preorders);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			Preorder preorder = mapper.readValue(request.getReader(), Preorder.class);
			facade.addPreorder(preorder);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	public void doPut(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getParameter("writeToCSV") != null) {
				facade.writePreordersToCsv();
			} else if (request.getParameter("readFromCSV") != null) {
				facade.readPreordersFromCsv();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
