package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BordItems2;
import dao.EventDAO;

/**
 * Servlet implementation class dbTest
 */
@WebServlet("/dbTest")
public class dbTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public dbTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Calendar a = Calendar.getInstance();
		//BordItems2 item2 = new BordItems2(1,2,"str+","str","str",Calendar.getInstance());
		EventDAO dao = new EventDAO();


		//dao.createBordItemTable(4);


		BordItems2 item2 = new BordItems2(4,4,"adsd","str","strafjs<br>",Calendar.getInstance());
		dao.insertBordItemList2(item2);


		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		for(int j=1;j<10;j++) {
			dao.insertBordItemList2(item2);
		}
		for(BordItems2 i :dao.getBordItemList(4)){
		out.println("<html>"+i.getItemId()+"<br>");
		out.println(i.getUserName()+"<br>");
		out.println(i.getUserPass()+"<br>");
		out.println(EventDAO.getStrByCalendar(i.getUserRegistDay())+"<br>"+"</html>");
		}


	}


}
