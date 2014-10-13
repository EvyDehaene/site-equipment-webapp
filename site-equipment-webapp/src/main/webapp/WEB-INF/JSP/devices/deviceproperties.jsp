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
<h2>Properties</h2>
<table class="sortable">
<thead>
<tr>
<th>Key</th>
<th>Value</th>
</tr>
</thead>
<tbody>
<c:forEach items="${device.properties}" var="property">
<tr>
<td>${property.key}</td>
<td>${property.value}</td>
</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>