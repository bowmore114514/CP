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
		// TODO Auto-generated method stub
		// doGet(request, response);

		// 判別用リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action"); // 判別用

		String eventName = request.getParameter("eventName"); // イベント名
		String organizarName = request.getParameter("organizarName"); // 幹事の名前
		String eventVenueA = request.getParameter("eventVenue"); // 場所
		String autherName = request.getParameter("autherName"); // イベント製作者の名前
		String autherPass = request.getParameter("autherPass"); // イベント製作者の編集用パスワード
		String autherRemarkA = request.getParameter("autherRemark"); // イベント製作者の備考欄
		String eventOpenFlgaS = request.getParameter("eventOpenFlga"); // イベントの公開フラグ.1:公開,0:非公開
		String numberOfEvent = request.getParameter("numberOfEvent");// イベント会数
		String pricePerPersonA = request.getParameter("pricePerPerson"); // イベント一人当たりの料金

		String deadlineDayYearS = request.getParameter("deadlineYear"); // 締切日 年
		String deadlineDayMonthS = request.getParameter("deadlineDayMonth"); // 締切日
																				// 月
		String deadlineDateS = request.getParameter("deadlineDay"); // 締切日 日

		String[] yearS = request.getParameterValues("year"); // 年 日程候補日
		String[] monthS = request.getParameterValues("month"); // 月 日程候補日
		String[] dateS = request.getParameterValues("day"); // 日 日程候補日
		String[] hourS = request.getParameterValues("hour"); // 時間

		// イベントの場所
		ArrayList<String> eventVenue = new ArrayList<String>();
		eventVenue.add(eventVenueA);

		// イベント製作者の備考欄
		ArrayList<String> autherRemark = new ArrayList<String>();
		autherRemark.add(autherRemarkA);

		// イベント一人当たりの料金
		ArrayList<String> pricePerPerson = new ArrayList<String>();
		pricePerPerson.add(pricePerPersonA);

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
		int eventOpenFlga = Integer.parseInt(eventOpenFlgaS);

		// URL
		String eventUrl = "";

		// イベントページファイルの名前
		String eventPageFileName = "";

		// 候補日
		ArrayList<Calendar> candidate = new ArrayList<Calendar>();

		// インスタンスの生成
		Event event = new Event(eventName, organizarName, eventVenue, registDay, autherName, autherPass, deadlineDay,
				autherRemark, determinedDay, determinedFlag, eventOpenFlga, numberOfEvent, eventUrl, eventPageFileName,
				pricePerPerson, candidate);

		// 候補日
		for (int i = 1; i < monthS.length; i++) {

			// 年月日時間のいずれかが入っていなかったらスルーする
			if (yearS[i].equals(null) || yearS[i].equals("") || monthS[i].equals(null) || monthS[i].equals("")
					|| dateS[i].equals(null) || dateS[i].equals("") || hourS[i].equals(null) || hourS[i].equals("")) {

			} else { // 年月日時間がすべて入っていたらArrayListにいれる

				// 候補日Stringからintへ
				int year = Integer.parseInt(yearS[i]);
				int month = Integer.parseInt(monthS[i]);
				int date = Integer.parseInt(dateS[i]);
				int hour = Integer.parseInt(hourS[i]);

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
		}

		if (action.equals("MasterEdit")) { // 決定が選択された

			// DAO（変更用）

		} else if (action.equals("MasterDelete")) { // 削除が選択された

			// DAO(削除用)
		}

		// 管理者用ページにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/master.jsp");
		dispatcher.forward(request, response);
	}

}
