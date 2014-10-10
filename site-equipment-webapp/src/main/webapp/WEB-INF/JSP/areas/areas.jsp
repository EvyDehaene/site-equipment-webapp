<%@ page contentType="text/html"  pageEncoding="UTF-8" session="false"%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="nl">
<head>

<title>Areas</title>
<link rel='stylesheet' href='${pageContext.servletContext.contextPath}/styles/default.css'/>
	
</head>
<body id="wrapper">
<c:import url="/WEB-INF/JSP/menu.jsp"/>
	<h1>Areas</h1>
	<ul>
		<c:forEach items="${areas}" var="area">
			<li><c:url value="/area" var="areaURL">
				<c:param name = "name" value="${area.name}"/>
			</c:url>
			<a class="list"href="${areaURL}">${area.name} - ${area.description}</a>
			</li>
		</c:forEach>
	</ul>
</body>
</html>