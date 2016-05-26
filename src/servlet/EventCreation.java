package servlet;

//index.jspの画面

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
 * Servlet implementation class EventCreation
 */
@WebServlet("/EventCreation")
public class EventCreation extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventCreation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
/*	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		//----------------リクエストパラメータの取得----------------
		request.setCharacterEncoding("UTF-8");
		String eventName = request.getParameter("eventName"); // イベント名
		String organizarName = request.getParameter("organizarName"); // 幹事の名前
		//String[] eventVenueA = request.getParameterValues("eventVenue"); // 場所
		String autherName = request.getParameter("autherName"); // イベント製作者の名前
		String autherPass = request.getParameter("autherPass"); //イベント製作者の編集用パスワード
		//String[] autherRemarkA = request.getParameterValues("autherRemark"); // イベント製作者の備考欄
		String eventOpenFlagS = request.getParameter("eventOpenFlag"); // イベントの公開フラグ.1:公開,0:非公開
		String numberOfEvent = request.getParameter("numberOfEvent");//イベント会数
		//String[] pricePerPersonA = request.getParameterValues("pricePerPerson"); // イベント一人当たりの料金

		String deadlineDayYearS = request.getParameter("deadlineYear"); // 締切日　年
		String deadlineDayMonthS = request.getParameter("deadlineDayMonth"); //締切日　
		String deadlineDateS = request.getParameter("deadlineDay"); //締切日　日
	//	String deadlineHourS = request.getParameter("deadlineDay"); //締切日　日

		//String[] yearS = request.getParameterValues("year"); // 年 日程候補日
		//String[] monthS = request.getParameterValues("month"); // 月　日程候補日
		//String[] dateS = request.getParameterValues("day"); // 日　日程候補日
		//String[] hourS = request.getParameterValues("hour"); // 時間


		// 場所 - リクエストパラメータ
		int m=0;//会数カウンタ
		ArrayList<String> eventVenue = new ArrayList<String>(); //ArrayList
		for (int i = 0; i < 5; i++) {
			String eventVenueS = request.getParameter("eventVenue" + i);

			if (eventVenueS == null || eventVenueS.length()==0) {
				break;
			} else {
				eventVenue.add(eventVenueS);
				m++;
			}
		}
		numberOfEvent=String.valueOf(m);

		// 備考 - リクエストパラメータ
		ArrayList<String> autherRemark = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			String autherRemarkS = request.getParameter("autherRemark" + i);

			if (autherRemarkS == null || autherRemarkS.length()==0) {
				break;
			} else {
				autherRemark.add(autherRemarkS);
			}
		}


		// 費用 - リクエストパラメータ
		ArrayList<String> pricePerPersonA = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			String pricePerPersonS = request.getParameter("pricePerPerson" + i);

			if (pricePerPersonS == null || pricePerPersonS.length()==0) {
				break;
			} else {
				pricePerPersonA.add(pricePerPersonS);
			}
		}


		//年月日時間
		ArrayList<String> yearS = new ArrayList<String>();
		ArrayList<String> monthS = new ArrayList<String>();
		ArrayList<String> dateS = new ArrayList<String>();
		ArrayList<String> hourS = new ArrayList<String>();

		for (int i = 0; i < 30; i++) {

			String yearA = request.getParameter("year" + i);
			String monthA = request.getParameter("month" + i);
			String dateA = request.getParameter("date" + i);
			String hourA = request.getParameter("hour" + i);

			if (yearA == null || yearA.length()==0 || monthA == null || monthA.length()==0
					|| dateA== null || dateA.length() ==0|| hourA == null || hourA.length()==0) {

			}else{

				yearS.add(yearA);
				monthS.add(monthA);
				dateS.add(dateA);
				hourS.add(hourA);
			}
		}

		//----------------リクエストパラメータの取得終了----------------



		//投稿日時
		Calendar  registDay = Calendar.getInstance();


		//締切日
		Calendar deadlineDay = Calendar.getInstance();


		//確定日時
		Calendar determinedDay =  Calendar.getInstance();


		//イベント確定のフラグ　１：確定　０：未確定
		int determinedFlag = 0;


		//イベント公開・非公開
		int eventOpenFlag = Integer.parseInt(eventOpenFlagS);


		//URL
		String eventUrl = "NO DATA";


		//イベントページファイルの名前
		String eventPageFileName = "NO DATA";


		//候補日
		ArrayList<Calendar> candidate = new ArrayList<Calendar>();


		//Eventインスタンスの生成
		Event event = new Event(eventName, organizarName, eventVenue,
			registDay, autherName, autherPass, deadlineDay,
			autherRemark, determinedDay, determinedFlag, eventOpenFlag,
			numberOfEvent, eventUrl, eventPageFileName, pricePerPersonA,
			candidate);

		// 候補日
		for (int i = 0; i < yearS.size(); i++) {

			// 候補日Stringからintへ
			int year = Integer.parseInt(yearS.get(i));
			int month = Integer.parseInt(monthS.get(i));
			int date = Integer.parseInt(dateS.get(i));
			int hour = Integer.parseInt(hourS.get(i));

			month -= 1;

			// Calendarクラスにまとめる
			Calendar candidateA = Calendar.getInstance();
			Event.setYear(candidateA, year);
			Event.setMonth(candidateA, month);
			Event.setDate(candidateA, date);
			Event.setHour(candidateA, hour);

			// ArrayListにいれる
			event.addCandidate(candidateA);
		}


		//締め切り日時Stringからintへ
		int deadlineDayYear = Integer.parseInt(deadlineDayYearS);
		int deadlineDayMonth= Integer.parseInt(deadlineDayMonthS);
		int deadlineDate = Integer.parseInt(deadlineDateS);
		deadlineDayMonth -= 1;


		//締切日をセット
		event.setDeadlineYear(deadlineDayYear);
		event.setDeadlineMonth(deadlineDayMonth);
		event.setDeadlineDate(deadlineDate);


		//----------------DAO----------------

		//eventID
		String eventIdS = event.getEventId();
		String eventUrl2 = event.getEventUrl();
		Calendar deadlineDay2 = event.getDeadlineDay();


		//インスタンス生成
		Event2 event2 = new Event2(eventIdS, eventName, organizarName, registDay, autherName,
				autherPass, deadlineDay2, determinedDay, determinedFlag, eventOpenFlag,
				numberOfEvent, eventUrl2, eventPageFileName);
		EventDAO dao = new EventDAO();
		//dao.createEvent();


		//Event Table
		int eventId = dao.insertEvent2List(event2);
		event2.setEventId(String.valueOf(eventId));
		String randurl=CreateAndAnnihilateEventPage.getUrlbyId(eventId);
		event2.setEventUrl(randurl);
		dao.updateEvent2List(event2);



		//eventVenue Table
		dao.createEventVenueTable(eventId);
		dao.insertEventVenueList(eventId, eventVenue);


		//autherRemark Table
		dao.createAutherRemarkTable(eventId);
		dao.insertAutherRemarkList(eventId, autherRemark);


		//ArrayList<Integer>に
		//イベント一人当たりの料金
		ArrayList<Integer> intPricePerPerson = new ArrayList<Integer>();
		for (int i = 0; i < pricePerPersonA.size(); i++){
			intPricePerPerson.add(Integer.parseInt(pricePerPersonA.get(i)));
		}


		//pricePerPerson Table
		dao.createPricePerPersonTable(eventId);
		dao.insertPricePerPersonList(eventId, intPricePerPerson);


		//candidate Table
		dao.createCandidateTable(eventId);
		dao.insertCandidateList(eventId, event.getCandidate());

		//BordItems
		dao.createBordItemTable(eventId);
		BordItems2 item = new BordItems2(2,2,"a","a","a",Calendar.getInstance());
		dao.insertBordItemList2(item);

		//----------------DAO終了----------------


		//eventId
		event.setEventId(String.valueOf(eventId));


		//URL
		CreateAndAnnihilateEventPage.createEventPageUrl(event);


		//セッションスコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("event",event);


		//フォワード(イベント作成決定後のページ）
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/eventconfirmed.jsp");
		dispatcher.forward(request, response);


	}


}