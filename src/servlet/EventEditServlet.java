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
		String action = request.getParameter("action");


		if (action.equals("edit")){ //一般投稿者が編集ボタン押したとき

			//リクエストパラメータの取得（一般投稿者用パス）
			request.setCharacterEncoding("UTF-8");
			String userPassCheck = request.getParameter("userPass");
			String itemIdS = request.getParameter("itemId");

			int itemId = Integer.parseInt(itemIdS);

			//セッションスコープから取得
			HttpSession session = request.getSession();
			ArrayList<BordItems2> b2 = (ArrayList<BordItems2>) session.getAttribute("bordItems2");
			ArrayList<ArrayList<Integer>> pFlagSet = (ArrayList<ArrayList<Integer>>) session.getAttribute("preferredFlagSet");

			Event e = (Event) session.getAttribute("event");

			//適当な初期値
			String userName = "a";
			String userPass = "a";
			String userRemark = "a";
			Calendar userRegistDay = Calendar.getInstance();

			int count = 0;

			for(BordItems2 item : b2){
				if (itemId == item.getItemId()){
					userName = item.getUserName();
					userPass = item.getUserPass();
					userRemark = item.getUserRemark();
					userRegistDay = item.getUserRegistDay();

					count++;
				}
			}

			ArrayList<Integer> preferredFlagSet = pFlagSet.get(count);


			//BordItemsインスタンス生成
			BordItems b1 = new BordItems(itemIdS, preferredFlagSet,
					userName, userPass, userRemark, userRegistDay);

			//パスワード判定
			boolean userCheck = b1.isUserPassEquals(userPassCheck);

			if (userCheck == false) {

				// イベントページにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/str/servlet/EventPage?pageid=" +e. getEventId() +".java");
				dispatcher.forward(request, response);

			} else {

				//セッションスコープに保存
				session.setAttribute("bordItems", b1);

				// 編集ページにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/eventEdit2.jsp");
				dispatcher.forward(request, response);
			}


		}else if(action.equals("decide")){ //一般投稿者情報入力
			//リクエストパラメータの取得
			request.setCharacterEncoding("UTF-8");
			String userName = request.getParameter("userName");//一般投稿者名
			String userPass = request.getParameter("userPass");//一般投稿者パスワード
			//String[] preferredFlagS = request.getParameterValues("preferredFlagSet"); //参加・不参加・未定

			//参加・不参加・未定
			ArrayList<Integer> preferredFlag = new ArrayList<Integer>();
			for (int i = 0; i < 30; i++){
				String preferredFlagS = request.getParameter("preferredFlagSet" + i);

				if(preferredFlagS == null){
					preferredFlag.add(0);
					//break;
				}else{
					int intpreferredFlagSet = Integer.parseInt(preferredFlagS);
					preferredFlag.add(intpreferredFlagSet);
				}
			}



			String userRemark = request.getParameter("userRemark");//備考

			//投稿ID
			String itemIdS = "123";


			//投稿日時
			Calendar  userRegistDay = Calendar.getInstance();


			//BordItemsインスタンスの生成
			BordItems bordItems = new BordItems(itemIdS, preferredFlag,
					userName, userPass, userRemark, userRegistDay);



			//参加・不参加・未定
			for (int i = 0; i < preferredFlag.size(); i++){

				//ArrayListにいれる
				bordItems.addPreferredFlagSet(preferredFlag.get(i));

			}





			//----------------DAO----------------

			//String → int
			int intItemId = Integer.parseInt(itemIdS);

			//セッションスコープからイベントIDを取得 //保存時にイベントIDセットすること
			HttpSession session = request.getSession();
			Event e = (Event) session.getAttribute("event");

			int eventId = Integer.parseInt(e.getEventId());

			//インスタンス生成
			BordItems2 bordItems2 = new BordItems2(eventId, intItemId, userName, userPass, userRemark,userRegistDay);
			EventDAO dao = new EventDAO();


			//BordItems teable
			int itemId = dao.insertBordItemList2(bordItems2);



			//preferredFlagSet Table
			dao.createPreferredFlagTable(eventId, intItemId);
			dao.insertPreferredFlagSet(eventId, itemId, bordItems.getPreferredFlagSet());

			//----------------DAO終了----------------


			//セッションスコープに保存
			session.setAttribute("bordItems",bordItems);


			//イベントページにフォワードに変更する
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
	}

}