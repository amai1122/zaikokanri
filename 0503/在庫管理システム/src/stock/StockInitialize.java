package stock;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class StockInitialize extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//行先指定
		final String NEXTPAGE_PATH = "/jsp/stock/stockMain.jsp";

		//現在登録済みの商品データを表示のために取得する
		//入力チェック

		StockDBAccess stockSet = new StockDBAccess();
		List<StockBean> stockList = stockSet.stockDBAccessSelect("0000000","all");
		System.out.println("allセレクト");

		//リストからリクエストにセット
		request.setAttribute("stockList", stockList);
		System.out.println("allセレクトをリクエストにセット");

		// パスをセット
		String path = NEXTPAGE_PATH;
		path = java.net.URLDecoder.decode(path, "UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}

}
