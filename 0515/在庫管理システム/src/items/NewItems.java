package items;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NewItems extends HttpServlet {
	//行先指定
	final static private String NEXTPAGE_PATH = "/jsp/items/itemsInsert.jsp";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		// セッションオブジェクト生成
		HttpSession session = request.getSession();
		// InputBeanオブジェクト生成
        ItemsBean items = (ItemsBean) session.getAttribute("items");

        String flag = request.getParameter("flag");
        String errflag = items.getError();

        if ("error".equals(errflag)) {
        	//エラーで戻ってきているので入力を保持
        	request.setAttribute("item_id", items.getItem_id());
    		request.setAttribute("item_name",items.getItem_name());
    		request.setAttribute("item_price", items.getItem_price());
    		request.setAttribute("cost_price", items.getCost_price());
    		request.setAttribute("stock_qty", items.getStock_qty());

        }else {
        	//新規なので空欄で登録画面に遷移
    		//request.setAttribute("item_id", "");
    		request.setAttribute("item_name","");
    		request.setAttribute("item_price", "0");
    		request.setAttribute("cost_price", "0");
    		request.setAttribute("stock_qty", "0");
        }
        request.setAttribute("flag",flag);
    	session.removeAttribute("session");

		// パスをセット
        String path = NEXTPAGE_PATH;

        // itemsInsert.jspに遷移
        RequestDispatcher rd = request.getRequestDispatcher(path);
        rd.forward(request, response);
	}

}
