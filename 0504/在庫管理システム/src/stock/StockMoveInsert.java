package stock;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StockMoveInsert extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//行先指定
		final String NEXTPAGE_PATH = "/jsp/stock/stockInsert.jsp";

		request.setCharacterEncoding("UTF-8");

		// DBの引数を取得
		String move_id = request.getParameter("move_id");

		final String dbcom ="update";//

		//該当IDの商品情報をDBから取得
		try {
			StockDBAccess up = new StockDBAccess();
			List<StockBean> stockList = up.stockDBAccessSelect(move_id, dbcom);
			System.out.println("IDからデータを取得");

			//リストから商品ID、商品名、商品単価をリクエストにセット
			for (StockBean stock : stockList) {
				request.setAttribute("move_id", stock.getMove_id());
				request.setAttribute("reason_id", stock.getReason_id());
				request.setAttribute("move_date", stock.getMove_date());
				request.setAttribute("item_id", stock.getItem_id());
				request.setAttribute("item_name",stock.getItem_name());
				request.setAttribute("item_price", stock.getItem_price());
				request.setAttribute("cost_price", stock.getCost_price());
				request.setAttribute("stock_qty", stock.getStock_qty());
				request.setAttribute("in_or_out", stock.getIn_or_out());
				request.setAttribute("move_qty", stock.getMove_qty());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// パスをセット
		String path = NEXTPAGE_PATH;

		// itemsInsert.jspに遷移
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);


	}

}
