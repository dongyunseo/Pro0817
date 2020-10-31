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

// if(���並 �ۼ��� ��ǰ�� �ֹ����� Ȯ�� dressDetail���� ������ ID �� ���������� Dressid�� ������ Order���̺��� select�� ��) {
			OrderDao oDao = new OrderDao();
			int result = oDao.checkOrder(id, dressid);
			request.setAttribute("result", result);

			System.out.println("�ֹ��� �� ������ΰ�? " + result + "(Yes=1, No=-1)");
			if (result == 1) {
// -------------------Review ��� ------------------------------------------------

				dressid = multi.getParameter("dressid");
				id = multi.getParameter("id");
				String reviewtitle = multi.getParameter("reviewtitle");
				String reviewcontent = multi.getParameter("reviewcontent");
				String reviewImg = multi.getFilesystemName("reviewImg");
				String reviewscore = multi.getParameter("reviewscore");
				String dressname = multi.getParameter("dressname");
				System.out.println("");
				System.out.println("---------------------------");
				System.out.println("���� �ۼ� ��.....");
				System.out.println("������ ��ǰ ���̵� : " + dressid);
				System.out.println("������ ��ǰ �̸� : " + dressname);
				System.out.println("���並 �ۼ��� ����� ID : " + id);
				System.out.println("���� ���� : " + reviewtitle);
				System.out.println("���� ���� : " + reviewcontent);
				System.out.println("���� ���� : " + reviewImg);
				System.out.println("���� ���� : " + reviewscore);
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

// -------------------�������� �������----------------------------------------------
				String page = (String)request.getParameter("page");
				String pageDataCount = (String)request.getParameter("pageDataCount");
				// ����ó��
				if(page==null) { 
					page="1";
				}
				if(pageDataCount==null) {
					pageDataCount="3";
				}
			
				ReviewDao rDao = ReviewDao.getInstance();
				ArrayList<ReviewVO> ReviewdressidList = rDao.selectReviewpageging(page, pageDataCount, dressid);
				request.setAttribute("ReviewdressidList", ReviewdressidList);
			
				// ����¡
				int totalDataCount=ReviewDao.dataCount();
				
				ReviewCountDto reviewCountDto = new ReviewCountDto();
				reviewCountDto.makePage(Integer.parseInt(page)
						,Integer.parseInt(pageDataCount)
						,totalDataCount);
				request.setAttribute("reviewCountDto", reviewCountDto);
				System.out.println(page);
				System.out.println(pageDataCount);
				System.out.println(reviewCountDto);
//-----------------------------���� �ۼ��� �� �������� �̵�------------------------------
				ProDao dao = new ProDao();
				ProDto dto = dao.select(dressid);
				request.setAttribute("dto", dto);
				
//---------------------�α����� ���� dressid ��ǰ�� �ֹ� �� �����Ͱ� ���ٸ� �Ʒ� �ڵ� ����-----------
			} else {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('�ֹ��� �� ��ǰ�� �����ۼ��� �����մϴ�.');history.go(-1);</script>");
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
