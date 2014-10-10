<%@ page contentType="text/html"  pageEncoding="UTF-8" session="false"%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="nl">
<head>

<title>Areas</title>
<link rel='stylesheet' href='${pageContext.servletContext.contextPath}/styles/default.css'/>
	<script src="http://www.karssenberg.nl/sorttable.js"></script>
</head>
<body>
<c:import url="/WEB-INF/JSP/menu.jsp"/>
	<h1>Areas</h1>
	<ul>
		<c:forEach items="${areas}" var="ares">
			<li><c:url value="areas/area" var="areaURL">
				<c:param name = "area" value="${area}"/>
			</c:url>
			<a href="${areaURL}">${area.name}</a>
			</li>
		</c:forEach>
	</ul>
</body>
</html>