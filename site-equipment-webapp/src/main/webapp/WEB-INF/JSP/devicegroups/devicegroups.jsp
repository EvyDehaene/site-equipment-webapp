<%@ page contentType="text/html"  pageEncoding="UTF-8" session="false"%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="nl">
<head>
<title>DeviceGroups</title>
<link rel='stylesheet' href='${pageContext.servletContext.contextPath}/styles/default.css'/>
	
</head>
<body id="wrapper">
	<c:import url="/WEB-INF/JSP/menu.jsp"/>
	<h1>DeviceGroups</h1>
	<ul>
		<c:forEach items="${devicegroups}" var="devicegroup">
			<li><c:url value="/devicegroup" var="devicegroupURL">
				<c:param name = "name" value="${devicegroup.name}"/>
			</c:url>
			<a class="list" href="${devicegroupURL}">${devicegroup.name}</a>
			</li>
		</c:forEach>
		
	</ul>
</body>
</html>