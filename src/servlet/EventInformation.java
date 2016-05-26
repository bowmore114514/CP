package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BordItems2;
import dao.Event2;
import dao.EventDAO;
import model.CreateAndAnnihilateEventPage;
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


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=" + "UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// リクエストパラメータから取得
				request.setCharacterEncoding("UTF-8");
				String autherPassCheck = request.getParameter("autherPass"); // 幹事用パス

				int eventId = Integer.parseInt(request.getParameter("pageid"));
				String eventIdS = String.valueOf(eventId);
				/*
				// 幹事用パスがあっているかどうかのロジック
				HttpSession session = request.getSession();
				Event e = (Event) session.getAttribute("event");
				String pass = e.getAutherPass();*/

				//Event2インスタンス
				//適当な初期値
				String str = "str";
				int n = 0;
				Calendar cal = Calendar.getInstance();

				Event2 event0 = new Event2(str, str, str, cal, str,
											str, cal, cal, n, n, str, str, str);

				//DAO
				EventDAO dao = new EventDAO();

				ArrayList<Event2> event2 = dao.getEvent2List();

				for (Event2 event:event2){
					if (Integer.parseInt(event.getEventId()) == eventId){

						event0 = event;

					}
				}


				//event0からそれぞれの値を取得
				String eventName = event0.getEventName();
				String organizarName = event0.getOrganizarName();
				Calendar registDay = event0.getRegistDay();
				String autherName = event0.getAutherName();
				String autherPass = event0.getAutherPass();
				Calendar deadlineDay = event0.getDeadlineDay();
				Calendar determinedDay = event0.getDeterminedDay();
				int determinedFlag = event0.getDeterminedFlag();
				int eventOpenFlag = event0.getEventOpenFlag();
				String numberOfEvent = event0.getNumberOfEvent();
				String eventUrl = event0.getEventUrl();
				String eventPageFileName = event0.getEventPageFileName();


				//各DBテーブルからeventIdに応じた値をすべて取得
				ArrayList<String> eventVenue = dao.getEventVenueList(eventId);
				ArrayList<String> autherRemark = dao.getAutherRemarkList(eventId);
				ArrayList<String> pricePerPerson = dao.getPricePerPersonList(eventId);
				ArrayList<Calendar> candidate = dao.getCandidateList(eventId);


				//インスタンス生成
				Event event = new Event(eventName, organizarName, eventVenue,
						registDay, autherName, autherPass, deadlineDay,
						autherRemark, determinedDay, determinedFlag, eventOpenFlag,
						numberOfEvent, eventUrl, eventPageFileName,pricePerPerson,
						candidate);
				event.setEventId(String.valueOf(eventId));
				CreateAndAnnihilateEventPage.createEventPageUrl(event);




				//BordItems2
				ArrayList<BordItems2> bordItems2 = dao.getBordItemList(eventId);
				ArrayList<ArrayList<Integer>> preferredFlagSet = new ArrayList<ArrayList<Integer>>();

				for (BordItems2 item : bordItems2){
					preferredFlagSet.add(dao.getPreferredFlagSet(eventId,item.getItemId()));
				}


				//セッションスコープに保存
				HttpSession session = request.getSession();
				session.setAttribute("event",event);
				session.setAttribute("bordItems2", bordItems2);
				session.setAttribute("preferredFlagSet", preferredFlagSet);


				if (event0.getAutherPass().contains(autherPassCheck)  ) {

					// イベントページにフォワード
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/eventdate.jsp");
					dispatcher.forward(request, response);


				} else {

					// 幹事用イベント編集にフォワード
					RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
					dispatcher.forward(request, response);
				}
		// TODO Auto-generated method stub
		// doGet(request, response);


	}

}
