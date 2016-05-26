package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BordItems2;
import dao.EventDAO;

/**
 * Servlet implementation class EventUserEdit
 */
@WebServlet("/EventUserEdit")
public class EventUserEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventUserEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		String itemIdS = request.getParameter("itemid");
		String eventId = request.getParameter("eventid");
		String userRemark = request.getParameter("userRemark");
		String userName = request.getParameter("username");
		EventDAO dao =new EventDAO();
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
		BordItems2 item0 = new BordItems2(0,0,"?","?","?",Calendar.getInstance());
		ArrayList<BordItems2> itemList =dao.getBordItemList(Integer.parseInt(eventId));
		for(BordItems2 item : itemList){
			if(Integer.parseInt(eventId) == item.getEventId() && Integer.parseInt(itemIdS) == item.getItemId() ){
				item0 = item;
			}
		}
		BordItems2 bordItems2 = new BordItems2(Integer.parseInt(eventId), Integer.parseInt(itemIdS), userName, item0.getUserPass(), userRemark,item0.getUserRegistDay());

		dao.updateBordItemList2(bordItems2);
	}

}
