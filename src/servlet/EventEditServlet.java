package servlet;

//eventEdit.jsp
//イベントページ

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
import model.BordItems;
import model.Event;


/**
 * Servlet implementation class EventEditServlet
 */
@WebServlet("/EventEditServlet")
public class EventEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);


		//リクエストパラメータから取得
		request.setCharacterEncoding("UTF-8");
		//String edit = request.getParameter("edit");
		//String decide = request.getParameter("decide");
		int action = Integer.parseInt( request.getParameter("action"));
		EventDAO dao = new EventDAO();


		//適当な初期値
		String str = "a";
		Calendar cal = Calendar.getInstance();

		String userName ="a";
		String userPass = "b";
		String eventId = "c";
		String userRemark = "d";
		//投稿ID
		String itemIdS = "123";
		//投稿日時
		Calendar  userRegistDay = Calendar.getInstance();


		if (action == 1){ //一般投稿者が編集ボタン押したとき

			//リクエストパラメータの取得（一般投稿者用パス）
			request.setCharacterEncoding("UTF-8");
			String userPassCheck = request.getParameter("userPass");
			itemIdS = request.getParameter("itemid");
			eventId = request.getParameter("eventid");
			ArrayList<BordItems2> b2list =dao.getBordItemList(Integer.parseInt(eventId));


			for(BordItems2 item : b2list){
				if (Integer.parseInt(itemIdS) == item.getItemId()){
					userName = item.getUserName();
					userPass = item.getUserPass();
					userRemark = item.getUserRemark();
					userRegistDay = item.getUserRegistDay();
				}
			}

			ArrayList<Integer> preferredFlagSet = dao.getPreferredFlagSet(Integer.parseInt(eventId), Integer.parseInt(itemIdS));


			//BordItemsインスタンス生成
			BordItems b1 = new BordItems(itemIdS, preferredFlagSet,
					userName, userPass, userRemark, userRegistDay);

			ArrayList<Event2> event2List = dao.getEvent2List();
			//参加・不参加・未定

			Event2 e = new Event2(str,str,str,cal,str,str,
						cal,cal,9500,9501,str,str,str);

			for(Event2 ev :event2List){
				if(Integer.parseInt(ev.getEventId())== Integer.parseInt(eventId)){
					 e = ev;
				}
			}

			if (! userPass.equals(userPassCheck)) {

				// イベントページにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
				//response.sendRedirect("/WebContent/index.jsp");
				return;

			} else {
		        //event0からそれぞれの値を取得
		        String eventName = e.getEventName();
		        String organizarName = e.getOrganizarName();
		        Calendar registDay = e.getRegistDay();
		        String autherName = e.getAutherName();
		        String autherPass = e.getAutherPass();
		        Calendar deadlineDay = e.getDeadlineDay();
		        Calendar determinedDay = e.getDeterminedDay();
		        int determinedFlag = e.getDeterminedFlag();
		        int eventOpenFlag = e.getEventOpenFlag();
		        String numberOfEvent = e.getNumberOfEvent();
		        String eventUrl = e.getEventUrl();
		        String eventPageFileName = e.getEventPageFileName();

		        
		        //各DBテーブルからeventIdに応じた値をすべて取得
		        ArrayList<String> eventVenue = dao.getEventVenueList(Integer.parseInt(eventId));
		        ArrayList<String> autherRemark = dao.getAutherRemarkList(Integer.parseInt(eventId));
		        ArrayList<String> pricePerPerson = dao.getPricePerPersonList(Integer.parseInt(eventId));
		        ArrayList<Calendar> candidate = dao.getCandidateList(Integer.parseInt(eventId));


		        //インスタンス生成
		        Event event = new Event(eventName, organizarName, eventVenue,
		                registDay, autherName, autherPass, deadlineDay,
		                autherRemark, determinedDay, determinedFlag, eventOpenFlag,
		                numberOfEvent, eventUrl, eventPageFileName,pricePerPerson,
		                candidate);
				//セッションスコープに保存
				HttpSession session = request.getSession();
				session.setAttribute("bordItems", b1);
				session.setAttribute("event",event);
				// 編集ページにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/eventUserEdit.jsp");
				dispatcher.forward(request, response);
				//response.sendRedirect("/WEB-INF/eventEdit2.jsp");
				return;
			}


		}else if( action == 0){ //一般投稿者情報入力
			//リクエストパラメータの取得
			request.setCharacterEncoding("UTF-8");
			userName = request.getParameter("userName");//一般投稿者名
			userPass = request.getParameter("userPass");//一般投稿者パスワード
			eventId = request.getParameter("eventid");
			userRemark = request.getParameter("userRemark");
			//String[] preferredFlagS = request.getParameterValues("preferredFlagSet"); //参加・不参加・未定

			ArrayList<Event2> event2List = dao.getEvent2List();
			//参加・不参加・未定

			Event2 e = new Event2(str,str,str,cal,str,str,
						cal,cal,9500,9501,str,str,str);

			for(Event2 ev :event2List){
				if(Integer.parseInt(ev.getEventId())== Integer.parseInt(eventId)){
					 e = ev;
				}
			}
			ArrayList<Calendar> canList= dao.getCandidateList(Integer.valueOf(eventId));
			ArrayList<Integer> preferredFlag = new ArrayList<Integer>();
			for (int i = 0; i < canList.size(); i++){
				String preferredFlagS = request.getParameter("preferredFlagSet" + i);

				if(Integer.valueOf(preferredFlagS) ==0){
					//nullだったら不参加として扱う
					preferredFlag.add(0);
				}else{
					int intpreferredFlagSet = Integer.parseInt(preferredFlagS);
					preferredFlag.add(intpreferredFlagSet);
				}
			}

			//BordItemsインスタンスの生成
			BordItems bordItems = new BordItems(itemIdS, preferredFlag,
					userName, userPass, userRemark, userRegistDay);



			//参加・不参加・未定 //無限ループなってる
			/*for (int i = 0; i < preferredFlag.size(); i++){
				//ArrayListにいれる
				bordItems.addPreferredFlagSet(preferredFlag.get(i));
			}*/





			//----------------DAO----------------

			//String → int
			int intItemId = Integer.parseInt(itemIdS);

			//インスタンス生成
			BordItems2 bordItems2 = new BordItems2(Integer.parseInt(eventId), intItemId, userName, userPass, userRemark,userRegistDay);



			//BordItems teable
			int itemId = dao.insertBordItemList2(bordItems2);


			//preferredFlagSet Table
			dao.createPreferredFlagTable(Integer.valueOf(eventId), itemId);
			dao.insertPreferredFlagSet(Integer.valueOf(eventId), itemId, preferredFlag);

			//----------------DAO終了----------------




			//イベントページにフォワードに変更する
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			//response.sendRedirect("/WebContent/index.jsp");
			return;

		}
	}

}