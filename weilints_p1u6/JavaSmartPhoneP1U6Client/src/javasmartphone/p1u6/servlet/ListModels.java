/**
 * @author Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * This servlet will receive a GET resuquest from browser 
 * Then get list of automobile names from server 
 * List all choices for user to select.
 * Then pass the user input to send a GET request to ConfigureModel.java
 */ 

package javasmartphone.p1u6.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javasmartphone.p1u6.client.SelectCarOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListModels
 */
@WebServlet("/ListModels")
public class ListModels extends HttpServlet implements IServletConstants {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListModels() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> listOfModels = null;
		
		/* fetch data from server */ 
		SelectCarOption selectCarOption = new SelectCarOption(REMOTE_HOST, PORT);
		if (selectCarOption.openSession()) {
			listOfModels = selectCarOption.getAvailableModel();
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
		out.println("<h3>Please select a car for configuration</h3>");
		out.println("<br/>");
		out.println("<form action='ConfigureModel' onSubmit='return validation()'>");
			
		for(int i = 0; i < listOfModels.size(); ++i) {
			out.println("<input type='radio' name='model' value='" + listOfModels.get(i) + "'/>" + listOfModels.get(i) + "<br/><br/>");
		}
		
		out.println("<br/><input type='submit' value='Continue'/>&nbsp;&nbsp;");
        out.println("<input type='button' value='Back' onClick='javascript:history.go(-1)' />");
		out.println("</form>");
		out.println("</div></body>");

		out.println("<script type='text/Javascript'>");
		
		out.println("function validation() {");
		out.println("for(var i = 0; i < document.getElementsByName('model').length; i++)");
		out.println("if(document.getElementsByName('model')[i].checked)");
		out.println("return true;");
		out.println("alert('Please select a car');");
		out.println("return false;");
		out.println("}");
		out.println("//]]>");
		out.println("</script>");
		out.println("</html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
