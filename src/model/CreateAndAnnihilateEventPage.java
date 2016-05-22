package model;
import java.util.Random;

public class  CreateAndAnnihilateEventPage {


	public static String createEventPageId(String strId){//EventIdからURL用のEventIdに変換。
		Random rand=new Random();//乱数を用いてURL推定しにくくする
		return String.valueOf(( rand.nextInt(9999) + 1 )*10000 + Integer.parseInt(strId));
	}

	public static void createEventPageUrl(Event event){///EventClassからEventURLを生成。ID生成後に実行すべし
		String servletUrl ="";//event詳細ページのサーブレットのURL
		event.setEventUrl(servletUrl + "?pageid=" + createEventPageId(event.getEventId()));
	}



}
