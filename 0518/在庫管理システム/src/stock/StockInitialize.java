package stock;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class StockInitialize extends HttpServlet {
	//行先指定
	final static private String NEXTPAGE_PATH = "/jsp/stock/stockMain.jsp";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//現在登録済みの商品データを表示のために取得する
		//入力チェック

		StockDBAccess stockSet = new StockDBAccess();
		List<StockBean> stockList = stockSet.stockDBAccessSelect("0000000","all");

		//リストからリクエストにセット
		request.setAttribute("stockList", stockList);

		// パスをセット
		String path = NEXTPAGE_PATH;
		path = java.net.URLDecoder.decode(path, "UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}

}
