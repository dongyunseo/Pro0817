package com.human.categorycommand;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.dao.CartDao;
import com.human.dao.OrderDao;
import com.human.dao.ProDao;
import com.human.dao.ReviewDao;
import com.human.dto.CartVO;
import com.human.dto.ProDto;
import com.human.dto.ReviewCountDto;
import com.human.dto.ReviewVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ReviewCommand implements ProCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String saveFolder = "C:/sql/0817Pro/WebContent/img/uploadflie";
		final int maxSize = 10 * 1024 * 1024; // 10mb
		try {
			MultipartRequest multi = new MultipartRequest(request, saveFolder, maxSize, "utf-8",
					new DefaultFileRenamePolicy());
			String id = multi.getParameter("id");
			String dressid = multi.getParameter("dressid");

// if(리뷰를 작성할 상품을 주문여부 확인 dressDetail에서 접속한 ID 와 상세페이지의 Dressid를 가져와 Order테이블에서 select를 함) {
			OrderDao oDao = new OrderDao();
			int result = oDao.checkOrder(id, dressid);
			request.setAttribute("result", result);

			System.out.println("주문을 한 사용자인가? " + result + "(Yes=1, No=-1)");
			if (result == 1) {
// -------------------Review 등록 ------------------------------------------------

				dressid = multi.getParameter("dressid");
				id = multi.getParameter("id");
				String reviewtitle = multi.getParameter("reviewtitle");
				String reviewcontent = multi.getParameter("reviewcontent");
				String reviewImg = multi.getFilesystemName("reviewImg");
				String reviewscore = multi.getParameter("reviewscore");
				String dressname = multi.getParameter("dressname");
				System.out.println("");
				System.out.println("---------------------------");
				System.out.println("리뷰 작성 중.....");
				System.out.println("리뷰한 상품 아이디 : " + dressid);
				System.out.println("리뷰한 상품 이름 : " + dressname);
				System.out.println("리뷰를 작성한 사용자 ID : " + id);
				System.out.println("리뷰 제목 : " + reviewtitle);
				System.out.println("리뷰 내용 : " + reviewcontent);
				System.out.println("리뷰 사진 : " + reviewImg);
				System.out.println("리뷰 점수 : " + reviewscore);
				System.out.println("---------------------------");
				System.out.println("");
				ReviewVO reviewVO = new ReviewVO();
				
				reviewVO.setDressId(Integer.parseInt(dressid));
				reviewVO.setDressname(dressname);
				reviewVO.setId(id);
				reviewVO.setReviewtitle(reviewtitle);
				reviewVO.setReviewcontent(reviewcontent);
				reviewVO.setReviewimg("<img src='../img/uploadflie/" + reviewImg + "'>");
				reviewVO.setReviewscore(reviewscore);
				ReviewDao reviewDao = ReviewDao.getInstance();
				reviewDao.reviewInsert(reviewVO);

// -------------------상세페이지 리뷰출력----------------------------------------------
				String page = (String)request.getParameter("page");
				String pageDataCount = (String)request.getParameter("pageDataCount");
				// 예외처리
				if(page==null) { 
					page="1";
				}
				if(pageDataCount==null) {
					pageDataCount="3";
				}
			
				ReviewDao rDao = ReviewDao.getInstance();
				ArrayList<ReviewVO> ReviewdressidList = rDao.selectReviewpageging(page, pageDataCount, dressid);
				request.setAttribute("ReviewdressidList", ReviewdressidList);
			
				// 페이징
				int totalDataCount=ReviewDao.dataCount();
				
				ReviewCountDto reviewCountDto = new ReviewCountDto();
				reviewCountDto.makePage(Integer.parseInt(page)
						,Integer.parseInt(pageDataCount)
						,totalDataCount);
				request.setAttribute("reviewCountDto", reviewCountDto);
				System.out.println(page);
				System.out.println(pageDataCount);
				System.out.println(reviewCountDto);
//-----------------------------리뷰 작성한 상세 페이지로 이동------------------------------
				ProDao dao = new ProDao();
				ProDto dto = dao.select(dressid);
				request.setAttribute("dto", dto);
				
//---------------------로그인한 고객이 dressid 상품을 주문 한 데이터가 없다면 아래 코드 실행-----------
			} else {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('주문을 한 상품만 리뷰작성이 가능합니다.');history.go(-1);</script>");
				writer.close();
			
				ProDao dao = new ProDao();
				ProDto dto = dao.select(dressid);
				request.setAttribute("dto", dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
