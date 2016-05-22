package servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Event2;
import dao.EventDAO;


/**
 * Servlet implementation class EventPage
 */
@WebServlet("/EventPage")
public class EventPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventPage() {
        super();
        // TODO Auto-generated constructor stub
    }




	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		//URLの取得
		String url = new String(request.getRequestURL());

		String flag = rightstring(url,5);


		//できない
		//DAO
		EventDAO eventPage = new EventDAO();

		String str = "str";
		Calendar cal = Calendar.getInstance();

		Event2 a = new Event2(flag, str, str, cal, str,
				str, cal, cal, 0, 0, str, str, str);

		eventPage.getEvent2List().get(Integer.getInteger(flag));
		String eventId = "";


		//URLからイベントIDを取得して、インスタンスにセット
		






		//インスタンス生成


		//セッションスコープに保存


	}



    public static String rightstring(String value, int length){
    	try{
    		if(value.length() >= length){
    			return value.substring(value.length() - length);
    		}else{
    			return  value.substring(1);
    		}
    	}catch(Exception e){
    		return value;
    	}
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
