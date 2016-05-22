package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Event2;
import dao.EventDAO;
import model.Event;



/**
 * Servlet implementation class EventListView
 */
@WebServlet("/EventListView")
public class EventListView extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventListView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

	EventDAO dao = new EventDAO();
	ArrayList<Event> eventList = new ArrayList<Event>();
	ArrayList<Event2> eventList2 =dao.getEvent2List();





	//フォワード
	RequestDispatcher dispatcher
	= request.getRequestDispatcher("/CarolinaReaper/WebContent/WEB-INF/eventlist.jsp");
	dispatcher.forward(request,response);

	//リクエストスコープに保存
	request.setAttribute("eventList",eventList2);
	}
}
