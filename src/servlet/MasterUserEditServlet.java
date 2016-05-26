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
 * Servlet implementation class MasterUserEditServlet
 */
@WebServlet("/MasterUserEditServlet")
public class MasterUserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MasterUserEditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=" + "UTF-8");
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String itemIdS = request.getParameter("itemId");

		int itemId = Integer.parseInt(itemIdS);

		// セッションスコープから取得
		HttpSession session = request.getSession();
		ArrayList<BordItems2> b2 = (ArrayList<BordItems2>) session.getAttribute("bordItems2");
		ArrayList<ArrayList<Integer>> pFlagSet = (ArrayList<ArrayList<Integer>>) session
				.getAttribute("preferredFlagSet");

		// 適当な初期値
		String userName = "a";
		String userPass = "a";
		String userRemark = "a";
		Calendar userRegistDay = Calendar.getInstance();

		int count = 0;

		for (BordItems2 item : b2) {
			if (itemId == item.getItemId()) {
				userName = item.getUserName();
				userPass = item.getUserPass();
				userRemark = item.getUserRemark();
				userRegistDay = item.getUserRegistDay();

				count++;
			}
		}

		ArrayList<Integer> preferredFlagSet = pFlagSet.get(count);

		// BordItemsインスタンス生成
		// preferredFlagSetにもいれた頑張った
		BordItems b1 = new BordItems(itemIdS, preferredFlagSet, userName, userPass, userRemark, userRegistDay);

		// セッションスコープに保存
		session.setAttribute("bordItems", b1);

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/masterUserEdit2.jsp");
		dispatcher.forward(request, response);

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
		//doGet(request, response);

		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");// 一般投稿者名
		String userPass = request.getParameter("userPass");// Pass

		// 参加・不参加・未定
		ArrayList<Integer> preferredFlag = new ArrayList<Integer>();
		for (int i = 0; i < 30; i++) {
			String preferredFlagS = request.getParameter("preferredFlagSet" + i);

			if (preferredFlagS == null ||preferredFlagS.length()==0) {
				break;
			} else {
				int intpreferredFlagSet = Integer.parseInt(preferredFlagS);
				preferredFlag.add(intpreferredFlagSet);
			}
		}

		String userRemark = request.getParameter("userRemark");// 備考
		String action = request.getParameter("action");
		String itemIdS = request.getParameter("itemId");

		// 投稿日時
		Calendar userRegistDay = Calendar.getInstance();

		// BordItemsインスタンスの生成
		BordItems bordItems = new BordItems(itemIdS, preferredFlag, userName, userPass, userRemark, userRegistDay);

		// 参加・不参加・未定
		for (int i = 0; i < preferredFlag.size(); i++) {

			// ArrayListにいれる
			bordItems.addPreferredFlagSet(preferredFlag.get(i));

		}

		// ----------------DAO----------------
		// DAO
		EventDAO dao = new EventDAO();

		// セッションスコープからeventIdを取得
		HttpSession session = request.getSession();
		Event event = (Event) session.getAttribute("event");
		String eventIdS = event.getEventId();

		// String → int
		int eventId = Integer.parseInt(eventIdS);
		int intItemId = Integer.parseInt(itemIdS);

		// bordItems2 //ここチェックが必要
		BordItems2 bordItems2 = new BordItems2(eventId, intItemId, userName, userPass, userRemark, userRegistDay);

		if (action.equals("userEdit")) { // 決定が選択された

			ArrayList<BordItems2> b2 = (ArrayList<BordItems2>) session.getAttribute("bordItems2");

			// DAO（変更用）
			// Event Table
			dao.updateBordItemList2(bordItems2);

			// preferredFlagSet Table
			for (BordItems2 item : b2) {
				if (item.getItemId() == intItemId) {
					for (int i = 0; i < dao.getCandidateList(eventId).size(); i++) {
						dao.updatePreferredFlagSet(eventId, intItemId, bordItems.getPreferredFlagSet().get(i));
					}
				}
			}

			// フォワード
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/src/servlet/MasterEventPage?pageid=" + eventId + ".java");
			dispatcher.forward(request, response);

		} else if (action.equals("userDelete")) { // 削除が選択された

			// DAO(削除用)
			dao.deleteBordItemRecord(eventId, intItemId);
			dao.dropPreferredFlagTable(eventId, intItemId);

			// イベント一覧にフォワード 変更の必要あり
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/masterUserEdit.jsp");
			dispatcher.forward(request, response);
		}

	}
}
