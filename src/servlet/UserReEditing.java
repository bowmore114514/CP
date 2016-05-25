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
import dao.EventDAO;
import model.BordItems;
import model.Event;

/**
 * Servlet implementation class UserReEditing
 */
@WebServlet("/UserReEditing")
public class UserReEditing extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserReEditing() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

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
				break;
			}else{
				int intpreferredFlagSet = Integer.parseInt(preferredFlagS);
				preferredFlag.add(intpreferredFlagSet);
			}
		}


		String userRemark = request.getParameter("userRemark");//備考
		String action = request.getParameter("action");
		String itemIdS = request.getParameter("itemId");




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
		//DAO
		EventDAO dao = new EventDAO();

		//セッションスコープからeventIdを取得
		HttpSession session = request.getSession();
		Event event = (Event)session.getAttribute("event");
		String eventIdS = event.getEventId();

		//String → int
		int eventId = Integer.parseInt(eventIdS);
		int intItemId = Integer.parseInt(itemIdS);

		//bordItems2 //ここチェックが必要
		BordItems2 bordItems2 = new BordItems2 (eventId, intItemId, userName, userPass, userRemark,userRegistDay);



		if (action == null) { //決定が選択された

			ArrayList<BordItems2> b2 = (ArrayList<BordItems2>) session.getAttribute("bordItems2");

			// DAO（変更用）
			// Event Table
			dao.updateBordItemList2(eventId, intItemId, userName, userPass, userRemark, userRegistDay, bordItems2);

			// preferredFlagSet Table
			for (BordItems2 item : b2) {
				if (item.getItemId() == intItemId) {
					for (int i = 0; i < dao.getCandidateList(eventId).size(); i++) {
						dao.updatePreferredFlagSet(eventId, intItemId, bordItems.getPreferredFlagSet().get(i));
					}
				}
			}


		}else if (action.equals("delete")){ //削除が選択された

			//DAO(削除用)
			dao.deleteBordItemRecord(eventId, intItemId);
			dao.dropPreferredFlagTable(eventId, intItemId);


			//イベント一覧にフォワード　変更の必要あり
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WebContent/index.jsp");
			dispatcher.forward(request, response);
		}

	}
}