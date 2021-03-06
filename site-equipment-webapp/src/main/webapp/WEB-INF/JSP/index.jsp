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
	<c:forEach items="${deviceGroups}" var="devicegroup">
			<h3><c:url value="/devicegroup" var="deviceGroupURL">
					<c:param name="name" value="${devicegroup.name}"/>
					</c:url>
			<a class="h" href="${deviceGroupURL}">${devicegroup.name}</a></h3>
			<c:choose>
	<c:when test="${not empty devicegroup.devices}">
	<table class="sortable">
		<thead>
			<tr>
				<th>Name</th>
				<th>Address</th>
				<th>Parent</th>
				<th>Type</th>
				<th>Comments</th>
				<th>Properties</th>
				<th>Remote-accessible</th>
				<th>Username</th>
				<th>Password</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${devicegroup.devices}" var="device">
				<tr>
					<td>
					<c:choose>
						<c:when test="${device.deviceType=='WAGO'}">
							<c:url value="/devices/wago" var="wagoURL">
								<c:param name = "name" value="${device.name}"/>
							</c:url>
							<a href="${wagoURL}">${device.name}</a>
						</c:when>
						<c:otherwise>
							${device.name}
						</c:otherwise>
					</c:choose>
					</td>
					<td>
					<c:choose> 
						<c:when test="${device.remoteAccessibleType == 'HTTP'}">
							<c:url value="http://${device.address}" var="URL"/>
							<a href='${URL}' target="_blank">${device.address}</a>	
						</c:when>
						<c:otherwise>
							${device.address}
						</c:otherwise>
					</c:choose>
					</td>
					<td>${device.parent}</td>
					<td><c:url value="devices/type" var="typeURL">
							<c:param name="type" value="${device.deviceType}"/>
						</c:url>
						<a href = "${typeURL}">${device.deviceType}</a>
					</td>
					<td><c:forEach items="${device.comments}" var="comment">
						${comment}<br/>
					</c:forEach></td>
					<td>
					<c:if test="${not empty device.properties}">
						<c:url value="devices/deviceproperties" var="propertyURL">
							<c:param name="name" value="${device.name}"/>
						</c:url>
						<a href="${propertyURL}">...</a>
					</c:if>
					</td>
					<td>Type: ${device.remoteAccessibleType} Port: ${device.port}</td>
					<td>${device.username}</td>
					<td>${device.password}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</c:when>
	<c:otherwise>
		Er zitten geen devices in de lijst
	</c:otherwise>
		
	</c:choose>
	</c:forEach>
	<c:forEach items="${areas}" var="area">
		<h2><c:url value="/area" var="areaURL">
				<c:param name = "name" value="${area.name}"/>
			</c:url>
			<a class="h"href="${areaURL}">${area.name} - ${area.description}</a></h2>
		<c:forEach items="${area.deviceGroups}" var="devicegroup">
			<h3><c:url value="/devicegroup" var="deviceGroupURL">
					<c:param name="name" value="${devicegroup.name}"/>
					</c:url>
			<a class="h" href="${deviceGroupURL}">${devicegroup.name}</a></h3>
			<c:choose>
	<c:when test="${not empty devicegroup.devices}">
	<table class="sortable">
		<thead>
			<tr>
				<th>Name</th>
				<th>Address</th>
				<th>Parent</th>
				<th>Type</th>
				<th>Comments</th>
				<th>Properties</th>
				<th>Remote-accessible</th>
				<th>Username</th>
				<th>Password</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${devicegroup.devices}" var="device">
				<tr>
					<td>
					<c:choose>
						<c:when test="${device.deviceType=='WAGO'}">
							<c:url value="/devices/wago" var="wagoURL">
								<c:param name = "name" value="${device.name}"/>
							</c:url>
							<a href="${wagoURL}">${device.name}</a>
						</c:when>
						<c:otherwise>
							${device.name}
						</c:otherwise>
					</c:choose>
					</td>
					<td>
					<c:choose> 
						<c:when test="${device.remoteAccessibleType == 'HTTP'}">
							<c:url value="http://${device.address}" var="URL"/>
							<a href='${URL}' target="_blank">${device.address}</a>	
						</c:when>
						<c:otherwise>
							${device.address}
						</c:otherwise>
					</c:choose>
					</td>
					<td>${device.parent}</td>
					<td><c:url value="devices/type" var="typeURL">
							<c:param name="type" value="${device.deviceType}"/>
						</c:url>
						<a href = "${typeURL}">${device.deviceType}</a>
					</td>
					<td><c:forEach items="${device.comments}" var="comment">
						${comment}<br/>
					</c:forEach></td>
					<td>
					<c:if test="${not empty device.properties}">
						<c:url value="devices/deviceproperties" var="propertyURL">
							<c:param name="name" value="${device.name}"/>
						</c:url>
						<a href="${propertyURL}">...</a>
					</c:if>
					</td>
					<td>Type: ${device.remoteAccessibleType} Port: ${device.port}</td>
					<td>${device.username}</td>
					<td>${device.password}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</c:when>
	<c:otherwise>
		Er zitten geen devices in de lijst
	</c:otherwise>
		
	</c:choose>
	</c:forEach>
	</c:forEach>
</body>
</html>