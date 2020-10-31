package com.human.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.human.dto.QandAVo;
import com.human.dto.ReviewVO;
import com.human.util.DBConn;

public class ReviewDao {

	public ReviewDao() {
	}

	private static ReviewDao instance = new ReviewDao();

	public static ReviewDao getInstance() {
		return instance;
	}

//-----------------���� ��� ---------------------
	public void reviewInsert(ReviewVO reviewVO) {
		// TODO Auto-generated method stub
		String sql = "insert into review(reviewnumber,dressid,dressname,id,reviewtitle,reviewcontent,reviewImg,reviewscore) "
				+ "VALUES(review_seq.nextval,?,?,?,?,?,?,?)";
		System.out.println(sql);
		System.out.println("���� ���� insert ��....");
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			System.out.println("");
			pstmt.setInt(1, reviewVO.getDressId());
			System.out.println("���� ��ǰ �ѹ� : " + reviewVO.getDressId());
			pstmt.setString(2, reviewVO.getDressname());
			System.out.println("������ ��ǰ �̸� : " + reviewVO.getDressname());
			pstmt.setString(3, reviewVO.getId());
			System.out.println("���� �ۼ� ���̵� : " + reviewVO.getId());
			pstmt.setString(4, reviewVO.getReviewtitle());
			System.out.println("���� ���� : " + reviewVO.getReviewtitle());
			pstmt.setString(5, reviewVO.getReviewcontent());
			System.out.println("���� ���� : " + reviewVO.getReviewcontent());
			pstmt.setString(6, reviewVO.getReviewimg());
			System.out.println("���� ���� : " + reviewVO.getReviewimg());
			pstmt.setString(7, reviewVO.getReviewscore());
			System.out.println("���� ���� : " + reviewVO.getReviewscore());
			System.out.println("");
			System.out.println("���� ��� �Ϸ�!");
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);

		}
	}

	// ��ǰ �������� ���� ����Ʈ ���--------------------------------------------
	/*
	 * public ArrayList<ReviewVO> selectReview(String dressid) { // TODO
	 * Auto-generated method stub ArrayList<ReviewVO> ReviewdressidList = new
	 * ArrayList<ReviewVO>(); Connection conn = DBConn.getConnection(); String sql =
	 * String.
	 * format("select * from review where dressid='%s' order by reviewnumber desc",
	 * dressid); System.out.println(sql); Statement st = null; ResultSet rs = null;
	 * 
	 * try { st = conn.createStatement(); rs = st.executeQuery(sql); while
	 * (rs.next()) { ReviewdressidList .add(new ReviewVO(rs.getInt("reviewnumber"),
	 * rs.getInt("dressId"), rs.getString("dressname"), rs.getString("id"),
	 * rs.getString("reviewtitle"), rs.getString("reviewcontent"),
	 * rs.getString("reviewimg"), rs.getString("reviewscore"),
	 * rs.getTimestamp("reviewDate"))); } } catch (Exception e) {
	 * e.printStackTrace(); } return ReviewdressidList; }
	 */
	// ������ ������ ��� ���� ��� -------------------------------------------
	public ArrayList<ReviewVO> selectAllReview() {
		// TODO Auto-generated method stub
		ArrayList<ReviewVO> ReviewAllList = new ArrayList<ReviewVO>();
		Connection conn = DBConn.getConnection();
		String sql = "select * from review order by reviewnumber desc";
		System.out.println(sql);
		Statement st = null;
		ResultSet rs = null;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ReviewAllList
						.add(new ReviewVO(rs.getInt("reviewnumber"), rs.getInt("dressId"), rs.getString("dressname"),
								rs.getString("id"), rs.getString("reviewtitle"), rs.getString("reviewcontent"),
								rs.getString("reviewimg"), rs.getString("reviewscore"), rs.getTimestamp("reviewDate")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ReviewAllList;
	}

	// ��ǰ �������� ���� ����Ʈ ���-----����¡ 1�������� 3�� ���� �Խù�
	// ���---------------------------------------
	public ArrayList<ReviewVO> selectReviewpageging(String page, String pageDataCount, String dressid) {
		// TODO Auto-generated method stub
		ArrayList<ReviewVO> ReviewdressidList = new ArrayList<ReviewVO>();
		Connection conn = DBConn.getConnection();
		String sql = String.format(
				"select * from (select rownum m,reviewnumber,dressId,dressname,id,reviewtitle,reviewcontent,reviewimg,reviewscore,reviewDate "
						+ "from review where rownum <=%s*%s order by reviewnumber desc) "
						+ "where m>=(%s-1)*%s+1 and dressid='%s'",
				page, pageDataCount, page, pageDataCount, dressid);
		System.out.println(sql);
		Statement st = null;
		ResultSet rs = null;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ReviewdressidList
						.add(new ReviewVO(rs.getInt("reviewnumber"), rs.getInt("dressId"), rs.getString("dressname"),
								rs.getString("id"), rs.getString("reviewtitle"), rs.getString("reviewcontent"),
								rs.getString("reviewimg"), rs.getString("reviewscore"), rs.getTimestamp("reviewDate")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ReviewdressidList;
	}

	public static int dataCount() {
		// TODO Auto-generated method stub
		int returnValue=0;
		Connection conn = DBConn.getConnection();
		String sql = "select count(reviewnumber) bCount from review";
				System.out.println(sql);
		Statement st = null;
		ResultSet rs = null;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				returnValue=rs.getInt("bCount");
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}

}