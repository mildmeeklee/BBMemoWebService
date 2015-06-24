<%@page import="java.util.Date"%>
<%@page import="test.com.jsp.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="test.com.jsp.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	BoardDAO dao = new BoardDAO();
	ArrayList<BoardDTO> list = dao.select();	

	StringBuffer buffer = new StringBuffer();
	buffer.append("<dept>");
	for(BoardDTO dto : list) {
		int b_num = dto.getB_num();
		String b_name = dto.getB_name();
		String b_content = dto.getB_content();
		
			
		buffer.append("<record>");
		buffer.append("<b_num>"+b_num+"</b_num>");
		buffer.append("<b_name>"+b_name+"</b_name>");
		buffer.append("<b_content>"+b_content+"</b_content>");
		buffer.append("</record>");
	}
	buffer.append("</dept>");
%>

<%= buffer.toString()%>