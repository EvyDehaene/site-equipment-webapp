<%@ page contentType="text/html"  pageEncoding="UTF-8" session="false"%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="nl">

<body>
	<nav>
		<ul class="zonderbolletjes">
			<li><c:url value='/' var="indexURL"/>
				<a class="nav" href="${indexURL}">Welcome</a>
			</li>
			<li><c:url value="/devices" var="devicesURL"/>
				<a class="nav" href="${devicesURL}">Devices</a></li>
			<li><c:url value="/areas" var="areasURL"/>
				<a class="nav" href="${areasURL}">Areas</a></li>
			<li><c:url value="/devicegroups" var="devicegroupsURL"/>
				<a class="nav" href="${devicegroupsURL}">DeviceGroups</a></li>
			<li><c:url value="/devices/wagos" var="wagosURL"/>
				<a class="nav" href="${wagosURL}">All wagos</a></li>
			<li><a class="nav" href="">Refresh</a></li>
		</ul>
	</nav>
</body>
</html>