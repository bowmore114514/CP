package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Event2;
import dao.EventDAO;
import model.Event;

/**
 * Servlet implementation class Master
 */
@WebServlet("/Master")
public class Master extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Master() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String masterPass = request.getParameter("masterPass"); //リクエストパラメータ
		EventDAO dao = new EventDAO();
		ArrayList<Event2> eventList2 =dao.getEvent2List();
		boolean keyRes;
		if(masterPass == Event.masterKey ){
			keyRes = true;
		}
		else{
			keyRes = false;
		}

		//セッションコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("event",eventList2);
		HttpSession session2 = request.getSession();
		session2.setAttribute("keyRes",keyRes);

		//フォワード
		RequestDispatcher dispatcher
		= request.getRequestDispatcher("/CarolinaReaper/WEB-INF/master.jsp");
		dispatcher.forward(request,response);



	}

}
