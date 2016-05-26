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
import model.Event;

/**
 * Servlet implementation class MasterEditServlet
 */
@WebServlet("/MasterEditServlet")
public class MasterEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MasterEditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=" + "UTF-8");
		// TODO Auto-generated method stub
		// doGet(request, response);

		//----------------リクエストパラメータの取得----------------
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action"); // 判別用
		String eventName = request.getParameter("eventName"); // イベント名
		String organizarName = request.getParameter("organizarName"); // 幹事の名前
		// String[] eventVenueA = request.getParameterValues("eventVenue"); //
		// 場所
		String autherName = request.getParameter("autherName"); // イベント製作者の名前
		String autherPass = request.getParameter("autherPass"); // イベント製作者の編集用パスワード
		String eventOpenFlagS = request.getParameter("eventOpenFlag"); // イベントの公開フラグ.1:公開,0:非公開
		String numberOfEvent = request.getParameter("numberOfEvent");// イベント会数

		String deadlineDayYearS = request.getParameter("deadlineYear"); // 締切日 年
		String deadlineDayMonthS = request.getParameter("deadlineDayMonth"); // 締切日
		String deadlineDateS = request.getParameter("deadlineDay"); // 締切日 日

		String eventId = request.getParameter("eventid");


		// 場所 - リクエストパラメータ
		ArrayList<String> eventVenue = new ArrayList<String>(); //ArrayList
		for (int i = 0; i < 5; i++) {
			String eventVenueS = request.getParameter("eventVenue" + i);

			if (eventVenueS == null) {
				break;
			} else {
				eventVenue.add(eventVenueS);
			}
		}


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

		int noe=0;
		// 費用 - リクエストパラメータ
		ArrayList<String> pricePerPersonA = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			String pricePerPersonS = request.getParameter("pricePerPerson" + i);

			if (pricePerPersonS == null || pricePerPersonS.length() ==0) {
				break;
			} else {
				pricePerPersonA.add(pricePerPersonS);
				noe++;
			}
		}
		numberOfEvent =  String.valueOf(noe);//String.valueOf(pricePerPersonA.size());

		/*//年月日時間
		ArrayList<String> yearS = new ArrayList<String>();
		ArrayList<String> monthS = new ArrayList<String>();
		ArrayList<String> dateS = new ArrayList<String>();
		ArrayList<String> hourS = new ArrayList<String>();

		for (int i = 0; i < 30; i++) {

			String yearA = request.getParameter("year" + i);
			String monthA = request.getParameter("month" + i);
			String dateA = request.getParameter("date" + i);
			String hourA = request.getParameter("hour" + i);

			if (yearA.equals(null) || yearA.equals("") || monthA.equals(null) || monthA.equals("")
					|| dateA.equals(null) || dateA.equals("") || hourA.equals(null) || hourA.equals("")) {

			}else{

				yearS.add(yearA);
				monthS.add(monthA);
				dateS.add(dateA);
				hourS.add(hourA);
			}
		}*/
		//----------------リクエストパラメータの取得終了----------------



		// 投稿日時
		Calendar registDay = Calendar.getInstance();

		// 締め切り日時Stringからintへ
		int deadlineDayYear = Integer.parseInt(deadlineDayYearS);
		int deadlineDayMonth = Integer.parseInt(deadlineDayMonthS);
		int deadlineDate = Integer.parseInt(deadlineDateS);

		// 締切日
		Calendar deadlineDay = Calendar.getInstance();
		Event.setYear(deadlineDay, deadlineDayYear);
		Event.setMonth(deadlineDay, deadlineDayMonth);
		Event.setDate(deadlineDay, deadlineDate);

		// 確定日時
		Calendar determinedDay = Calendar.getInstance();

		// イベント確定のフラグ １：確定 ０：未確定
		int determinedFlag = 0;

		// イベント公開・非公開
		int eventOpenFlag = Integer.parseInt(eventOpenFlagS);

		// URL
		HttpSession session = request.getSession();
		Event eventE = (Event)session.getAttribute("event");
		String eventUrl = eventE.getEventUrl();


		// イベントページファイルの名前
		String eventPageFileName = "";

		// 候補日
		ArrayList<Calendar> candidate = new ArrayList<Calendar>();

		// インスタンスの生成
		Event event = new Event(eventName, organizarName, eventVenue, registDay, autherName, autherPass, deadlineDay,
				autherRemark, determinedDay, determinedFlag, eventOpenFlag, numberOfEvent, eventUrl, eventPageFileName,
				pricePerPersonA, candidate);
		event.setEventId(eventId);

		/*// 候補日
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
		}*/



		//DAO
		EventDAO dao = new EventDAO();



		Event2 event2 = new Event2(eventId, eventName, organizarName, registDay, autherName,
				autherPass, deadlineDay, determinedDay, determinedFlag, eventOpenFlag,
				numberOfEvent, eventUrl, eventPageFileName);
		Event2 eventtemp = new Event2(eventId, eventName, organizarName, registDay, autherName,
				autherPass, deadlineDay, determinedDay, determinedFlag, eventOpenFlag,
				numberOfEvent, eventUrl, eventPageFileName);

		ArrayList<Event2> eventList = dao.getEvent2List();
		for(Event2 event3 :eventList){
			if(event3.getEventId().equals(eventId)){
				eventtemp = event3;
			}
		}
		event2.setAutherPass(eventtemp.getAutherPass());
		event2.setEventPageFileName("NoDATE");


		//eventId String → int
		int intEventId = Integer.parseInt(eventId);

		//ArrayList<Integer>に
		//イベント一人当たりの料金
		ArrayList<Integer> intPricePerPerson = new ArrayList<Integer>();
		for (int i = 0; i < pricePerPersonA.size(); i++){
			intPricePerPerson.add(Integer.parseInt(pricePerPersonA.get(i)));
		}


		if (action.equals("0")) { // 更新が選択された

			// DAO（変更用）

			//Event Table
			dao.updateEvent2List(event2);

			//drop
			dao.dropEventVenueTable(intEventId);
			dao.dropAutherRemarkTable(intEventId);
			dao.dropPricePerPersonTable(intEventId);
			//dao.dropCandidateTable(intEventId);
			//CREATE
			dao.createEventVenueTable(intEventId);
			dao.createAutherRemarkTable(intEventId);
			dao.createPricePerPersonTable(intEventId);
			//INSERT文
			dao.insertEventVenueList(intEventId, eventVenue);
			dao.insertAutherRemarkList(intEventId, autherRemark);
			dao.insertPricePerPersonList(intEventId, intPricePerPerson);
			//dao.insertCandidateList(intEventId, event.getCandidate());



		} else if (action.equals("1")) { // 削除が選択された

			// DAO(削除用)

			//イベント情報削除
			dao.deleteEventTable(intEventId);
			dao.dropEventVenueTable(intEventId);
			dao.dropAutherRemarkTable(intEventId);
			dao.dropPricePerPersonTable(intEventId);
			dao.dropCandidateTable(intEventId);


			//参加者情報削除
			ArrayList<BordItems2> bordItems2 = dao.getBordItemList(intEventId);
			for (BordItems2 item : bordItems2){
				dao.dropPreferredFlagTable(intEventId, item.getItemId());
				dao.deleteBordItemRecord(intEventId, item.getItemId());
			}



		}else if (action.equals("2")) {
			event2.setDeterminedFlag(1);
			dao.updateEvent2List(event2);
		}
		//セッションスコープ削除


		// 管理者用ページにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

}
