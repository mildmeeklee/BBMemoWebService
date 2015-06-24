package test.com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.com.jsp.LogDAO;
import test.com.jsp.LogDTO;

/**
 * Servlet implementation class LogonController
 */
public class LogonController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogonController() {
		super();
	}

	// http://localhost:8099/99JSP_myEMP/LogonController?id=aa&pwd=1234
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = (String) request.getParameter("id");
		String pwd = (String) request.getParameter("pwd");

		System.out.println("id :: " + id);
		System.out.println("pwd :: " + pwd);

		LogDAO logDAO = new LogDAO();
		int userCnt = logDAO.findId(id);
		System.out.println("cnt :: " + userCnt);
		if (userCnt > 0) {
			LogDTO logDTO = logDAO.select(id);
			System.out.println(logDTO);
			if (pwd.equals(logDTO.getPwd())) {
				System.out.println("pwd right!!");
			} else {
				System.out.println("pwd wrong!!");

			}
		}

		StringBuffer buffer = new StringBuffer();
		buffer.append("<logon>");		

		buffer.append("<record>");
		buffer.append("<user_cnt>" + userCnt + "</user_cnt>");
		buffer.append("<id>" + id + "</id>");
		buffer.append("<pwd>" + pwd + "</pwd>");
		buffer.append("</record>");

		buffer.append("</logon>");
		buffer.toString();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
