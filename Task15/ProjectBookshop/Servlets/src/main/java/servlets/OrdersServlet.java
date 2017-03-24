package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.training.DI.DI;
import com.senla.training.exceptions.MyException;
import com.senla.training.interfaces.IFacade;
import com.senla.training.model.Order;

public class OrdersServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 983948654284734288L;
	private final Logger log = Logger.getLogger(OrdersServlet.class);
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
				Order order = facade.viewOrderDetail(Integer.valueOf(request.getParameter("id")));
				mapper.writeValue(out, order);
			} else if (request.getParameter("startDate") != null && request.getParameter("criterion") != null) {
				List<Order> orders = facade.showExecOrdersByPeriod(request.getParameter("startDate"),
						request.getParameter("endDate"), request.getParameter("criterion"));
				mapper.writeValue(out, orders);
			} else if (request.getParameter("executedOrdersMoney") != null) {
				Map<String, Long> sum = facade.showMoneyByPeriod(request.getParameter("startDate"),
						request.getParameter("endDate"));
				mapper.writeValue(out, sum);

			} else if (request.getParameter("executedOrdersCount") != null) {
				Map<String, Long> count = facade.showExecOrdersCount(request.getParameter("startDate"),
						request.getParameter("endDate"));
				mapper.writeValue(out, count);
			} else {
				String paramValue = request.getParameter("criterion");
				List<Order> orders = facade.showOrders(paramValue);
				mapper.writeValue(out, orders);
			}
		} catch (IOException | MyException e) {
			log.error(e.getMessage());
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getParameter("clone") != null) {
				Order order = mapper.readValue(request.getReader(), Order.class);
				facade.cloneOrder(order);
			} else {
				Order order = mapper.readValue(request.getReader(), Order.class);
				facade.addOrder(order);
			}
		} catch (IOException | MyException e) {
			log.error(e.getMessage());
		}
	}

	public void doPut(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getParameter("writeToCSV") != null) {
				facade.writeOrdersToCsv();
			} else if (request.getParameter("readFromCSV") != null) {
				facade.readOrdersFromCsv();
			} else {
				Order order = mapper.readValue(request.getReader(), Order.class);
				facade.updateOrder(order);
			}
		} catch (IOException | MyException e) {
			log.error(e.getMessage());
		}
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response) {
		try {
			facade.deleteOrder(Integer.valueOf(request.getParameter("id")));
		} catch (MyException e) {
			log.error(e.getMessage());
		}
	}
}
