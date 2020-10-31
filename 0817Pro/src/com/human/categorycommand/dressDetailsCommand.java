package com.human.categorycommand;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.human.dao.OrderDao;
import com.human.dao.ProDao;
import com.human.dao.QandADao;
import com.human.dao.ReviewDao;
import com.human.dto.ProDto;
import com.human.dto.QandAVo;
import com.human.dto.ReviewCountDto;
import com.human.dto.ReviewVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class dressDetailsCommand implements ProCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("login_Id");
		String qandaid = request.getParameter("qandaid");
		String loginid = request.getParameter("loginid");
		String dressid = request.getParameter("dressid");
		String qandatitle = request.getParameter("qandatitle");
		qandatitle = request.getParameter("qandatitle");
//--------------------�������� ��ǰ ���-------------------------
		ProDao dao = new ProDao();
		ProDto dto = dao.select(dressid);
		request.setAttribute("dto", dto);
		
//--------------------�������� Q&A Insert-------------------------
		if (qandatitle != null) {
			QandAVo qandAVo = new QandAVo();
			qandAVo.setQandatitle(request.getParameter("qandatitle"));
			qandAVo.setQandacontent(request.getParameter("qandacontent"));
			qandAVo.setId(id);
			qandAVo.setDressid(Integer.parseInt(request.getParameter("dressid")));
			dressid = request.getParameter("dressid");
			System.out.println(dressid);
			QandADao qandADao = QandADao.getInstance();
			qandADao.insertQandA(qandAVo);
		}

//---------------------Q&A ����---------------------------------
		if (qandaid != null && id != null) {
			System.out.println("�Խñ� �ѹ� = " + qandaid);
			System.out.println("���� �α��� �� ���̵� = " + loginid);

			QandADao qandAVo = QandADao.getInstance();
			qandAVo.QandAdelete(qandaid, id);

		}

// -------------------�������� ������� ����¡--------------------------------

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
	
		// �Խñ��� ����� Ȯ���ϴ� ������
		int totalDataCount=ReviewDao.dataCount();
		
		ReviewCountDto reviewCountDto = new ReviewCountDto();
		reviewCountDto.makePage(Integer.parseInt(page)
				,Integer.parseInt(pageDataCount)
				,totalDataCount);
		request.setAttribute("reviewCountDto", reviewCountDto);
		System.out.println("���� ������ �ѹ� = " + page);
		System.out.println("�� �������� ����� �Խù� ���� = " + pageDataCount);
		System.out.println("�� �Խù� ���� = " + totalDataCount);
		System.out.println(reviewCountDto);

//--------------------�������� Q&A ����Ʈ ���-------------------------
		QandADao qDao = QandADao.getInstance();
		dressid = request.getParameter("dressid");
		System.out.println(dressid);
		ArrayList<QandAVo> dressDetails = qDao.QandASelete(dressid);
		request.setAttribute("dressDetails", dressDetails);

	}

}
