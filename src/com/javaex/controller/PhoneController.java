package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")
public class PhoneController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/pbc --> doGet");
		
		String action = request.getParameter("action");
		
		if("list".equals(action)) {
			//리스트일때
			System.out.println("list");
			
			PhoneDao dao = new PhoneDao();
			List<PersonVo> pList = dao.getPersonList();
			
			//포워드
			//데이터 리퀘스트에 추가
			request.setAttribute("personList", pList);
			
			//forword
			WebUtil.forword(request,response,"/WEB-INF/list.jsp");
			
		} else if("wform".equals(action)) {
			System.out.println("주소록 등록폼");
			//forword
			WebUtil.forword(request,response,"/WEB-INF/writeForm.jsp");
		} else if("insert".equals(action)) {
			System.out.println("번호저장");
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			PersonVo vo = new PersonVo(name,hp,company);
			PhoneDao dao = new PhoneDao();
			dao.personInsert(vo);
			
			//리다이렉트
			WebUtil.redirect(request, response, "/pb2/pbc?action=list");
			
		} else if("upform".equals(action)) {
			System.out.println("주소록 수정폼");
			
			int personId = Integer.parseInt(request.getParameter("personid"));
			PhoneDao phoneDao = new PhoneDao();
			PersonVo vo = phoneDao.getPerson(personId);
			
			request.setAttribute("personvo", vo);
			
			//포워드
			WebUtil.forword(request, response, "/WEB-INF/updateForm.jsp");
			
		} else if("update".equals(action)) {
			System.out.println("정보수정");
			int personId = Integer.parseInt(request.getParameter("personid"));
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			PersonVo vo = new PersonVo(personId,name,hp,company);
			PhoneDao dao = new PhoneDao();
			dao.personUpdate(vo);
			
			//리다이렉트
			WebUtil.redirect(request, response, "/pb2/pbc?action=list");
		} else if("delete".equals(action)) {
			System.out.println("삭제");
			
			int personId = Integer.parseInt(request.getParameter("personid"));
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personDelete(personId);
			
			response.sendRedirect("/pb2/pbc?action=list");
			
		}
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
