package stock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDBAccess {
	//------------------------------------------------

	public List<StockBean> stockDBAccessSelect(String move_id, String dbCom){
		//DBからstock_moveテーブルの在庫入出庫履歴の表示

		//データベースの検索
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlStr = null;
		List<StockBean> stockList = new ArrayList<StockBean>();

		try {
			//ドライバダウンロード
			Class.forName("org.mariadb.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mariadb://localhost/techfun", "root", "");



			//SQL文字列(all= update= )
			if ("all".equals(dbCom)) {
				//商品マスタの表示なら全部持ってくる
				sqlStr = "SELECT * FROM stock_move;";
				//System.out.println("allが選択されました");
			} else if ("update".equals(dbCom)) {
				//編集画面なら指定IDのデータだけ持ってくる
				sqlStr = "SELECT * From stock_move WHERE move_id ='"+ move_id +"';";
				System.out.println("updateが選択されました");
			} else if ("search".equals(dbCom)) {
				//商品名で検索(move_idに商品名item_idが入る)
				sqlStr = "SELECT * From stock_move WHERE item_name LIKE '%"+ move_id +"%';";
				//System.out.println("searchが選択されました");
			}
			// プリペアドステートメント生成
			pstmt = con.prepareStatement(sqlStr);

			//int index = 1;
			//if ("all".equals(dbCom)) {
				//バインド変数なし
			//} else if ("update".equals(dbCom)) {
			//	pstmt.setString(index, item_id);
			//} else if ("search".equals(dbCom)) {
			//	pstmt.setString(index, "%" + item_id + "%");
			//}

			// SQL文実行
			rs = pstmt.executeQuery();
			//System.out.println("セレクトが実行されました");

			//戻り値のlistにセット
			while (rs.next()) {
				StockBean bn = new StockBean();
				bn.setMove_id(rs.getString("move_id"));
				bn.setReason_id(rs.getString("reason_id"));
				bn.setMove_date(rs.getString("move_date"));
				bn.setItem_id(rs.getString("item_id"));
				bn.setItem_name(rs.getString("item_name"));
				bn.setItem_price(rs.getInt("item_price"));
				bn.setCost_price(rs.getInt("cost_price"));
				bn.setStock_qty(rs.getInt("stock_qty"));
				bn.setIn_or_out(rs.getString("in_or_out"));
				bn.setMove_qty(rs.getInt("move_qty"));

				stockList.add(bn);
			}
			//System.out.println("リストにセットされました");


		}catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			}catch (SQLException e) {
                e.printStackTrace();
            }
		}
		return stockList;
	}

	//------------------------------------------------
	public void stockDBAccessInsert(String move_id, String reason_id, String move_date,
			String item_id, String item_name, int item_price, int cost_price, int stock_qty,
			String in_or_out, int move_qty) {
		//データベースへの新規登録または編集

		//データベースの検索
		Connection con = null;
		PreparedStatement psID = null;
		PreparedStatement pstmt = null;
		ResultSet rsID = null;
		String sqlStr = null;

		try {
			//ドライバダウンロード
			Class.forName("org.mariadb.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mariadb://localhost/techfun", "root", "");
			System.out.println("DB接続されましたInsert");

			//IDがDBにあるかを検索
			//プリペアードステートメント生成
			sqlStr = "SELECT move_id FROM stock_move WHERE move_id ='" + move_id + "';";
			psID = con.prepareStatement(sqlStr);
			//psID.setString(1, move_id);
			// SQL文実行
			rsID = psID.executeQuery();
			//System.out.println("セレクトしました");

			String str="";
			while(rsID.next()){
				str=rsID.getString("move_id");
			}
			if (move_id.equals(str)) {
				//IDがあれば更新
				//sqlStr = "UPDATE stock_move SET move_id='"+ move_id +"',reason_id='"+ reason_id +"',move_date='"
				//		+ move_date +"',item_id='" + item_id + "',item_name='" + item_name + "',item_price ="
				//		+ item_price + ",cost_price= " + cost_price + ",stock_qty="+ stock_qty +", in_or_out='"
				//		+ in_or_out +"',move_qty="+ move_qty +" WHERE move_id = '" + move_id + "';";
				//System.out.println("更新が選ばれました");


			} else {
				//IDが無ければ新規登録
				sqlStr = "INSERT INTO stock_move(move_id,reason_id,move_date,item_id,item_name,item_price,"
						+"cost_price,stock_qty,in_or_out,move_qty)VALUES('"+ move_id +"','"+ reason_id +"','"+ move_date
						+"','" + item_id + "','"+ item_name + "'," + item_price + "," + cost_price + ","+ stock_qty
						+",'"+ in_or_out +"',"+ move_qty +");";
				//System.out.println("新規登録が選ばれました");
			}

			//プリペアードステートメント生成
			pstmt = con.prepareStatement(sqlStr);

			//int index = 1;
			//if (item_id.equals(rsID.getString("item_id"))) {
			//	pstmt.setString(index++, item_id);
			//	pstmt.setString(index++, item_name);
			//	pstmt.setString(index++, item_price);
			//	pstmt.setString(index++, cost_price);
			//	pstmt.setString(index++, item_id);
			//} else {
			//	pstmt.setString(index++, item_id);
			//	pstmt.setString(index++, item_name);
			//	pstmt.setString(index++, item_price);
			//	pstmt.setString(index++, cost_price);
			//}

			//SQL実行
			pstmt.executeUpdate();
			System.out.println("保存されました");

			//在庫数を商品マスタに保存



		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("クラス無しエラー");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLエラー");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (rsID != null) {
					rsID.close();
				}
				if (psID != null) {
					psID.close();
				}
				if (con != null) {
					con.close();
				}
			}catch (SQLException e) {
                e.printStackTrace();
            }

		}
	}
	//------------------------------------------------
	public String insertCheckDBAccess(String move_id) {
		//データベースへの新規登録または編集

		//データベースの検索
		Connection con = null;
		PreparedStatement psID = null;
		ResultSet rsID = null;
		String sqlStr = null;
		String check = "OK";

		try {
			//ドライバダウンロード
			Class.forName("org.mariadb.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mariadb://localhost/techfun", "root", "");

			//IDがDBにあるかを検索
			//プリペアードステートメント生成
			sqlStr = "SELECT move_id FROM stock_move WHERE move_id ='" + move_id + "';";
			psID = con.prepareStatement(sqlStr);
			//psID.setString(1, move_id);
			// SQL文実行
			rsID = psID.executeQuery();

			String str="";
			while(rsID.next()){
				str=rsID.getString("move_id");
			}
			if (move_id.equals(str)) {
				//IDがあればNG
				check="NG";
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("クラス無しエラー");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLエラー");
		} finally {
			try {
				if (rsID != null) {
					rsID.close();
				}
				if (psID != null) {
					psID.close();
				}
				if (con != null) {
					con.close();
				}
			}catch (SQLException e) {
                e.printStackTrace();
            }
		}
		return check;
	}
}
