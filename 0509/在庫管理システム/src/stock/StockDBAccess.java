package stock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDBAccess {

	final static private String dbDriver="org.mariadb.jdbc.Driver";
	final static private String dbPATH="jdbc:mariadb://localhost/techfun";
	final static private String dbID= "root";
	final static private String dbPASS= "";

	//------------------------------------------------

	public List<StockBean> stockDBAccessSelect(String move_id, String dbCom){
		//DBからstock_moveテーブルから在庫入出庫履歴の表示または商品名検索表示

		//データベースの検索
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlStr = null;
		List<StockBean> stockList = new ArrayList<StockBean>();

		try {
			//ドライバダウンロード
			Class.forName(dbDriver);

			con = DriverManager.getConnection(dbPATH, dbID, dbPASS);

			//SQL文字列(all= update= )
			if ("all".equals(dbCom)) {
				//商品マスタの表示なら全部持ってくる
				sqlStr = "SELECT * FROM stock_move;";
			} else if ("search".equals(dbCom)) {
				//商品名で検索(move_idに商品名item_idが入る)
				sqlStr = "SELECT * From stock_move WHERE item_name LIKE ?";
			}
			// プリペアドステートメント生成
			pstmt = con.prepareStatement(sqlStr);

			int index = 1;
			if ("all".equals(dbCom)) {
				//バインド変数なし
			} else if ("search".equals(dbCom)) {
				pstmt.setString(index, "%" + move_id + "%");
			}

			// SQL文実行
			rs = pstmt.executeQuery();

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
			}catch (SQLException e) {
                e.printStackTrace();
            }
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			}catch (SQLException e) {
                e.printStackTrace();
            }
			try {
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
	public StockBean stockDBAccessSelectByID(String move_id){
		//DBからstock_moveテーブルの照会の表示

		//データベースの検索
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlStr = null;
		StockBean upbean = new StockBean();

		try {
			//ドライバダウンロード
			Class.forName(dbDriver);

			con = DriverManager.getConnection(dbPATH, dbID, dbPASS);

			//SQL文字列(all= update= )

			//編集画面なら指定IDのデータだけ持ってくる
			sqlStr = "SELECT * From stock_move WHERE move_id = ?";

			// プリペアドステートメント生成
			pstmt = con.prepareStatement(sqlStr);

			int index = 1;
			pstmt.setString(index, move_id);

			// SQL文実行
			rs = pstmt.executeQuery();

			//戻り値のBeanにセット
			rs.next();
			upbean.setMove_id(rs.getString("move_id"));
			upbean.setReason_id(rs.getString("reason_id"));
			upbean.setMove_date(rs.getString("move_date"));
			upbean.setItem_id(rs.getString("item_id"));
			upbean.setItem_name(rs.getString("item_name"));
			upbean.setItem_price(rs.getInt("item_price"));
			upbean.setCost_price(rs.getInt("cost_price"));
			upbean.setStock_qty(rs.getInt("stock_qty"));
			upbean.setIn_or_out(rs.getString("in_or_out"));
			upbean.setMove_qty(rs.getInt("move_qty"));

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
			}catch (SQLException e) {
                e.printStackTrace();
            }
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			}catch (SQLException e) {
                e.printStackTrace();
            }
			try {
				if (con != null) {
					con.close();
				}
			}catch (SQLException e) {
                e.printStackTrace();
            }
		}

		return upbean;
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
		PreparedStatement pstmtItem = null;
		ResultSet rsID = null;
		String sqlStr = null;
		String sqlStrStock = null;
		String sqlStrItems = null;

		try {
			//ドライバダウンロード
			Class.forName(dbDriver);

			con = DriverManager.getConnection(dbPATH, dbID, dbPASS);

			//IDがDBにあるかを検索
			//プリペアードステートメント生成
			sqlStr = "SELECT move_id FROM stock_move WHERE move_id =?";
			psID = con.prepareStatement(sqlStr);
			psID.setString(1, move_id);
			// SQL文実行
			rsID = psID.executeQuery();

			String str="";
			while(rsID.next()){
				str=rsID.getString("move_id");
			}
			if (move_id.equals(str)) {
				//IDがあっても更新しない

			} else {
				//IDが無ければ新規登録
				//在庫入出庫登録
				sqlStrStock = "INSERT INTO stock_move(move_id,reason_id,move_date,item_id,item_name,item_price,"
						+"cost_price,stock_qty,in_or_out,move_qty)VALUES(?,?,?,?,?,?,?,?,?,?)";
				//商品テーブル在庫数入力
				sqlStrItems = "UPDATE items SET stock_qty=? WHERE item_id = ?";
			}
			//オートコミットOFF
			con.setAutoCommit(false);

			//プリペアードステートメント生成（在庫入出庫登録）
			pstmt = con.prepareStatement(sqlStrStock);

			int index = 1;
			if (move_id.equals(str)) {

			} else {
				pstmt.setString(index++, move_id);
				pstmt.setString(index++, reason_id);
				pstmt.setString(index++, move_date);
				pstmt.setString(index++, item_id);
				pstmt.setString(index++, item_name);
				pstmt.setInt(index++, item_price);
				pstmt.setInt(index++, cost_price);
				pstmt.setInt(index++, stock_qty);
				pstmt.setString(index++, in_or_out);
				pstmt.setInt(index++, move_qty);
			}
			//SQL実行（在庫入出庫登録）
			pstmt.executeUpdate();

			//プリペアードステートメント生成（在庫数入力）
			pstmtItem = con.prepareStatement(sqlStrItems);
			index = 1;
			pstmtItem.setInt(index++, stock_qty);
			pstmtItem.setString(index++, item_id);
			//SQL実行
			pstmtItem.executeUpdate();

			//コミット
			con.commit();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				//ロールバック
				con.rollback();
			}catch (SQLException ex) {
				e.printStackTrace();
			}
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			}catch (SQLException e) {
                e.printStackTrace();
            }
			try {
				if (rsID != null) {
					rsID.close();
				}
			}catch (SQLException e) {
                e.printStackTrace();
            }
			try {
				if (psID != null) {
					psID.close();
				}
			}catch (SQLException e) {
                e.printStackTrace();
            }
			try {
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
		//入力チェック用

		//データベースの検索
		Connection con = null;
		PreparedStatement psID = null;
		ResultSet rsID = null;
		String sqlStr = null;
		String check = "OK";

		try {
			//ドライバダウンロード
			Class.forName(dbDriver);

			con = DriverManager.getConnection(dbPATH, dbID, dbPASS);

			//IDがDBにあるかを検索
			//プリペアードステートメント生成
			sqlStr = "SELECT move_id FROM stock_move WHERE move_id =?";
			psID = con.prepareStatement(sqlStr);
			psID.setString(1, move_id);
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rsID != null) {
					rsID.close();
				}
			}catch (SQLException e) {
                e.printStackTrace();
            }
			try {
				if (psID != null) {
					psID.close();
				}
			}catch (SQLException e) {
                e.printStackTrace();
            }
			try {
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
