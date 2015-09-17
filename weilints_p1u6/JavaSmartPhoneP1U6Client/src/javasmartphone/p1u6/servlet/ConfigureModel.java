/**
 * @author Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * This servlet will receive a GET resuquest from ListModels.java 
 * It will communicate with server to get a whole automobile object 
 * Then provide a table form for user selection. 
 * Whenever user push continue, it will issue a POST request to itself 
 * with user specified values.
 * Finally, it will configure the automobile object and forward the 
 * object to ViewConfiguration.jsp
 * 
 */ 

package javasmartphone.p1u6.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javasmartphone.p1u6.client.SelectCarOption;
import javasmartphone.p1u6.model.Automobile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConfigureModel
 */
@WebServlet("/ConfigureModel")
public class ConfigureModel extends HttpServlet implements IServletConstants {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfigureModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Automobile auto = null;

		/* fetch data from server */
		SelectCarOption selectCarOption = new SelectCarOption(REMOTE_HOST, PORT);
		if (selectCarOption.openSession()) {
			String modelName = request.getParameter("model");
			selectCarOption.getAvailableModel();
			auto = selectCarOption.getAuto(modelName);
			selectCarOption.closeSession();
		}

		/* Do the display part */
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 "
				+ "Transitional//EN\">\n";
		out.println(docType + "<HTML>\n"
				+ "<HEAD><TITLE>ListModels</TITLE></HEAD>\n");

		out.println("<body><div style='padding-left:200px;'>");
		out.println("<h3>Please configure a car</h3>");
		out.println("<br/>");
		
		/* Show form here for selection */
		out.println("<form method = 'post' action='ConfigureModel' onSubmit='return 1'>");
		out.println("<table action='ConfigureModel' border='1px'>"); // TODO
		out.println("<tr><td>Make</td><td>" + auto.getMake() + "</td></tr>");
		out.println("<tr><td>Model</td><td>" + auto.getModel() + "</td></tr>");
		out.println("<tr><td>Base Price</td><td>" + auto.getBaseprice() + "</td></tr>");


		List<String> optionSetName = auto.getAllOptionSetName();
		// for each optionSet 
		for (int i = 0; i < optionSetName.size(); ++i) {
			out.println("<tr><td>" + optionSetName.get(i) + "</td>");
			out.println("<td><select name='" + optionSetName.get(i) + "'>");
			List<String> optionListAndPrice = auto.getOptionsAndPrices(optionSetName.get(i));
			for (int j = 0; j < optionListAndPrice.size(); j += 2) {
				out.println("<option value='" + optionListAndPrice.get(j) + "'>"
			                 + optionListAndPrice.get(j) + ": " + optionListAndPrice.get(j+1)
			                 + "</option>");
			}
			out.println("</select></td>");
			out.println("</tr>");
		}
		
		out.println("<br/><input type='submit' value='Continue'/>&nbsp;&nbsp;");
		out.println("<input type='hidden' name='auto' value='" + auto.getModel() +"'>");
		out.println("<input type='button' value='Back' onClick='javascript:history.go(-1)' />");
		out.println("</table>");
		out.println("</form>");
		out.println("</div></body>");
		out.println("</html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String autoName = request.getParameter("auto");
		Automobile auto = null;
		
		/* fetch data from server */
		SelectCarOption selectCarOption = new SelectCarOption(REMOTE_HOST, PORT);
		if (selectCarOption.openSession()) {
			selectCarOption.getAvailableModel();
			auto = selectCarOption.getAuto(autoName);
			selectCarOption.closeSession();
			// do the settings 
			List<String> optionSetName = auto.getAllOptionSetName();
			for (int i = 0; i < optionSetName.size(); i++) {
				String aSetName = optionSetName.get(i);
				String choice = request.getParameter(aSetName);
				auto.setOptionChoice(aSetName, choice);
			}
		}
		
		// do forward directly
		request.setAttribute("auto", auto);
		request.getRequestDispatcher("ViewConfiguration.jsp").forward(request, response);
	}

}
