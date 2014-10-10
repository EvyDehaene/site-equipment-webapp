<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="nl">
<head>

<title>All wagos</title>
<link rel='stylesheet'
	href='${pageContext.servletContext.contextPath}/styles/default.css' />
<script src="http://www.karssenberg.nl/sorttable.js"
	type="text/javascript"></script>
</head>
<body id="wrapper">
	<c:import url="/WEB-INF/JSP/menu.jsp" />
	<h1>All wagos</h1>
	<c:forEach items="${wagos}" var="wago">
		<h2>WAGO: ${wago.name} (<c:url value="http://${wago.address}" var="URL"/>
							<a href='${URL}' target="_blank">${wago.address}</a>)</h2>
		<h3>Inputs</h3>
		<c:if test="${not empty wago.inputs}">
			<table class='sortable'>
				<thead>
					<tr>
						<th>Name</th>
						<th>Number</th>
						<th>On</th>
						<th>Off</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${wago.inputs}" var="io">
						<tr>
							<td>${io.ioName}</td>
							<td>${io.number}</td>
							<td>${io.on}</td>
							<td>${io.off}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<c:if test="${not empty wago.outputs}">
			<h3>Outputs</h3>
			<table class="sortable">
				<thead>
					<tr>
						<th>Name</th>
						<th>Number</th>
						<th>On</th>
						<th>Off</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${wago.outputs}" var="io">
						<tr>
							<td>${io.ioName}</td>
							<td>${io.number}</td>
							<td>${io.on}</td>
							<td>${io.off}</td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</c:forEach>
</body>
</html>