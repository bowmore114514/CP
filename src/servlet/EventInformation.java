package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Event;

/**
 * Servlet implementation class EventInformation
 */
@WebServlet("/EventInformation")
public class EventInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventInformation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		// リクエストパラメータから取得
		request.setCharacterEncoding("UTF-8");
		String autherPassCheck = request.getParameter("autherPass"); // 幹事用パス

		// 幹事用パスがあっているかどうかのロジック
		HttpSession session = request.getSession();
		Event e = (Event) session.getAttribute("event");
		String pass = e.getAutherPass();


		if (autherPassCheck == pass) {

			// イベントページにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/eventData.jsp");
			dispatcher.forward(request, response);


		} else {

			// 幹事用イベント編集にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);
		}
	}

}
