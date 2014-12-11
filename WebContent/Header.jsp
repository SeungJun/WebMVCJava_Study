<%@page import="spms.vo.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- <jsp:useBean id="member" 
             scope ="session"
             class="spms.vo.Member"/> --%>
             
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
                 
<%-- <% Member member = (Member)session.getAttribute("member"); %> --%>
<div style="background-color:#00008b;color:#ffffff;height:20px;padding: 5px;">
SPMS(Simple Project Management System)

<c:if test="${!empty sessionScope.member and 
              !empty sessionScope.member.email}">
<%-- <% if (member.getEmail() != null) { %> --%>
<span style="float:right;">
${sessionScope.member.name}
<%-- <%=member.getName() %> --%>
 <a style="color:white;"
 href="<%=request.getContextPath()%>/auth/logout">로그아웃 </a> 
</span>
<%-- <% } %> --%>
</c:if>
</div>