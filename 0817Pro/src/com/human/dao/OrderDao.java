package com.human.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.human.dto.OrderVO;
import com.human.util.DBConn;

public class OrderDao {

	public OrderDao() {

	}

	private static OrderDao instance = new OrderDao();

	public static OrderDao getInstance() {
		return instance;
	}

	// -------------------- 주문 등록-------------------
	public void OrderInsert(OrderVO orderVo) {
		String sql = "insert into tbl_order(ordernum,id,dressid,dressimg,dressname,price,amount,sum,"
				+ "ordername,address,phone,email,orderMessage,depositor,Bank,delivery)"
				+ "VALUES(tbl_order_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'입금 전')";
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderVo.getId());
			System.out.println("주문자 아이디 : " + orderVo.getId());
			pstmt.setInt(2, orderVo.getDressid());
			System.out.println("상품 넘버 :" + orderVo.getDressid());
			pstmt.setString(3, orderVo.getDressimg());
			System.out.println("상품 이미지 주소 : " + orderVo.getDressimg());
			pstmt.setString(4, orderVo.getDressname());
			System.out.println("상품 이름 : " + orderVo.getDressname());
			pstmt.setInt(5, orderVo.getPrice());
			System.out.println("상품 가격 : " + orderVo.getPrice());
			pstmt.setInt(6, orderVo.getAmount());
			System.out.println("상품 수량 : " + orderVo.getAmount());
			pstmt.setInt(7, orderVo.getSum());
			System.out.println("총 금액  : " + orderVo.getSum());
			pstmt.setString(8, orderVo.getOrdername());
			System.out.println("주문자 이름  : " + orderVo.getOrdername());
			pstmt.setString(9, orderVo.getAddress());
			System.out.println("주소 : " + orderVo.getAddress());
			pstmt.setString(10, orderVo.getPhone());
			System.out.println("전화 번호  : " + orderVo.getPhone());
			pstmt.setString(11, orderVo.getEmail());
			System.out.println("이메일 : " + orderVo.getEmail());
			pstmt.setString(12, orderVo.getOrderMessage());
			System.out.println("주문 메시지 : " + orderVo.getOrderMessage());
			pstmt.setString(13, orderVo.getDepositor());
			System.out.println("입금자 명 : " + orderVo.getDepositor());
			pstmt.setString(14, orderVo.getBank());
			System.out.println("입금 은행 : " + orderVo.getBank());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);
		}
	}

	// ------------------주문 리스트 출을 하기전에 주문정보가 null 인 정보 삭제 -----------------
	public void OrderHalfNullDelete() {
		String sql = "delete from tbl_order where ordername IS NULL";
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);
		}
	}

	// ------------------주문 리스트 출력 select -------------------------------
	public ArrayList<OrderVO> OrderList(String userId) {
		ArrayList<OrderVO> ListOrder = new ArrayList<OrderVO>();
		Connection conn = DBConn.getConnection();
		String sql = String.format("select * from tbl_order where id='%s'order by orderDate desc", userId);
		/*	String sql = String.format("select tbl_order.ordernum, tbl_order.id, tbl_order.dressid,tbl_order.dressName, "
				+ "tbl_order.price,tbl_order.amount,tbl_order.sum,tbl_order.ordername,tbl_order.address,tbl_order.phone,"
				+ "tbl_order.email,tbl_order.orderMessage ,tbl_order.depositor ,tbl_order.bank, tbl_order.delivery,tbl_order.orderDate, "
				+ "dress.dressimg from tbl_order,dress where ordername IS NOT NULL and tbl_order.dressid = dress.dressid and id ='%s'order by ordernum desc",
				userId);*/
		System.out.println(sql);

		Statement st = null;
		ResultSet rs = null;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ListOrder.add(new OrderVO(rs.getInt("ordernum"), rs.getString("id"), rs.getInt("dressid"),
						rs.getString("dressname"), rs.getInt("price"), rs.getInt("amount"), rs.getInt("sum"),
						rs.getString("ordername"), rs.getString("address"), rs.getString("phone"),
						rs.getString("email"), rs.getString("orderMessage"), rs.getString("depositor"),
						rs.getString("Bank"), rs.getString("delivery"), rs.getTimestamp("orderDate"),
						rs.getString("dressimg")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ListOrder;
	}

//--------------------주문 테이블 삭제 ------------------------------
	public void OrderDelete(int ordernum) {
		String sql = "delete tbl_order where ordernum=?";
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ordernum);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);
		}
	}

	// 출력----------------------------
	public void OrderHalf(OrderVO orderVo) {
		String sql = "insert into tbl_order(ordernum,id,dressimg,dressid,dressname,price,amount,sum)"
				+ "VALUES(tbl_order_seq.nextval,?,?,?,?,?,?,?)";
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderVo.getId());
			System.out.println("주문자 아이디 : " + orderVo.getId());
			pstmt.setString(2, orderVo.getDressimg());
			System.out.println("이미지 경로 : " + orderVo.getDressimg());
			pstmt.setInt(3, orderVo.getDressid());
			System.out.println("상품 넘버 :" + orderVo.getDressid());
			pstmt.setString(4, orderVo.getDressname());
			System.out.println("상품 이름 : " + orderVo.getDressname());
			pstmt.setInt(5, orderVo.getPrice());
			System.out.println("상품 가격 : " + orderVo.getPrice());
			pstmt.setInt(6, orderVo.getAmount());
			System.out.println("상품 수량 : " + orderVo.getAmount());
			pstmt.setInt(7, orderVo.getSum());
			System.out.println("총 금액  : " + orderVo.getSum());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);
		}
	}

// 관리자 페이지에서 주문 테이블 전체 출력---------------------------------------------------------
	public ArrayList<OrderVO> AdminAllOrderList() {
		// TODO Auto-generated method stub
		ArrayList<OrderVO> adminAllListOrder = new ArrayList<OrderVO>();
		Connection conn = DBConn.getConnection();
		String sql = "select * from tbl_order order by ordernum desc";

		System.out.println(sql);

		Statement st = null;
		ResultSet rs = null;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				adminAllListOrder.add(new OrderVO(rs.getInt("ordernum"), rs.getString("id"), rs.getInt("dressid"),
						rs.getString("dressname"), rs.getInt("price"), rs.getInt("amount"), rs.getInt("sum"),
						rs.getString("ordername"), rs.getString("address"), rs.getString("phone"),
						rs.getString("email"), rs.getString("orderMessage"), rs.getString("depositor"),
						rs.getString("Bank"), rs.getString("delivery"), rs.getTimestamp("orderDate"),
						rs.getString("dressimg")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adminAllListOrder;
	}

//리뷰를 작성하기 전 로그인한 사용자가 상품을 주문을 했을때만 리뷰작성을 할 수 있다.-----------------------
	public int checkOrder(String id, String dressid) {
		// TODO Auto-generated method stub
		DBConn.getInstance();
		int result = 0;
		String sql = "select * from tbl_order where id='" + id + "' and dressid='" + dressid + "'";
		System.out.println(sql);
		ResultSet rs = DBConn.statementQuery(sql);

		try {
			if (rs.next()) {
				result = 1;
				System.out.println(id + " 고객이 " + dressid + "번 상품을 주문 했습니다. ");
			} else {
				result = -1;
				System.out.println(id + " 고객이 " + dressid + "번 상품을 주문 한 데이터가 없습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

		DBConn.dbClose();
		return result;
	}
}