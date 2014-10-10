<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="nl">
<head>
<title>Site-equipment</title>
<link rel='stylesheet'
	href='${pageContext.servletContext.contextPath}/styles/default.css' />
<script src="http://www.karssenberg.nl/sorttable.js"
	type="text/javascript"></script>
</head>
<body id="wrapper">

	<c:import url="/WEB-INF/JSP/menu.jsp" />
	<h1>Site-equipment</h1>
	<c:forEach items="${devicegroups}" var="devicegroup">
		<h2>${devicegroup.name}</h2>

		
			<table class="sortable">
				<thead>
					<tr>
						
					</tr>
				</thead>
				<tbody>
					</tbody>
					</table>
	</c:forEach>
</body>
</html>