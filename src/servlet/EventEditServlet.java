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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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

			//パスワードが合っているかどうか
			HttpSession session = request.getSession();
			BordItems b = (BordItems) session.getAttribute("bordItems");

			boolean userCheck = b.isUserPassEquals(userPassCheck);

			if (userCheck == false) {

				// イベントページにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/eventEdit.jsp");
				dispatcher.forward(request, response);

			} else {

				// 編集ページにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/eventDatails.jsp");
				dispatcher.forward(request, response);
			}


		}else if(action.equals("decide")){ //一般投稿者情報入力
			//リクエストパラメータの取得
			request.setCharacterEncoding("UTF-8");
			String userName = request.getParameter("userName");//一般投稿者名
			String userPass = request.getParameter("userPass");//一般投稿者パスワード
			String[] preferredFlagS = request.getParameterValues("preferredFlagSet"); //参加・不参加・未定
			String userRemark = request.getParameter("userRemark");//備考

			//投稿ID
			String itemIdS = "aaa";


			//参加・不参加・未定
			ArrayList<Integer> preferredFlagSet = new ArrayList<Integer>();


			//投稿日時
			Calendar  userRegistDay = Calendar.getInstance();


			//BordItemsインスタンスの生成
			BordItems bordItems = new BordItems(itemIdS, preferredFlagSet,
					userName, userPass, userRemark, userRegistDay);


			//参加・不参加・未定
			for (int i = 0; i < preferredFlagS.length; i++){

				//Stringからintへ
				int preferredFlag = Integer.parseInt(preferredFlagS[i]);

				//ArrayListにいれる
				bordItems.addPreferredFlagSet(preferredFlag);
			}







			//----------------DAO----------------

			//String → int
			int intItemId = Integer.parseInt(itemId);

			//インスタンス生成
			//itemIdてどーなってんだべか
			BordItems2 bordItems2 = new BordItems2(intItemId, userName, userPass, userRemark,userRegistDay);
			EventDAO dao = new EventDAO();

			//セッションスコープからイベントIDを取得 //保存時にイベントIDセットすること
			HttpSession session = request.getSession();
			Event e = (Event) session.getAttribute("event");

			int eventId = Integer.parseInt(e.getEventId());


			//BordItems teable
			int itemId = dao.insertBordItemList2(eventId, intItemId, userName, userPass, userRemark, userRegistDay, bordItems2);


			//preferredFlagSet Table
			dao.insertPreferredFlagSet(eventId, itemId, bordItems.getPreferredFlagSet());

			//----------------DAO終了----------------


			//セッションスコープに保存
			session.setAttribute("bordItems",bordItems);


			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/eventEdit.jsp");
			dispatcher.forward(request, response);
		}
	}

}
