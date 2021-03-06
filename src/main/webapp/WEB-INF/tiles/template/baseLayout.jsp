<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Attribute for i18n, this allow us to use springmessages' keys in tile-definition.xml -->
<tilesx:useAttribute id="titleKey" name="title"/>
<title><spring:message code="${titleKey}"/></title>

<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">


<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<link href="http://getbootstrap.com/examples/jumbotron/jumbotron.css"
	rel="stylesheet">

<!-- required includes -->

<!-- Latest compiled and minified JavaScript -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</head>

<body>

	<div class="pull-right" style="padding-right: 50px">
		<sec:authorize access="isAuthenticated()">
			
			<c:url var="logoutUrl" value="/j_spring_security_logout"/>
			<form action="${logoutUrl}" id="logout" method="post">
    			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
			<a href="#" onclick="document.getElementById('logout').submit();"><spring:message code="baselayout.link.logout"/></a>
			
		</sec:authorize>
		<sec:authorize access="isAnonymous()">
			<a href="<spring:url value="/loginAccess"/>">
				<spring:message code="baselayout.link.login"/>
			</a>
		</sec:authorize>
		
	</div>
	<div class="pull-right" style="padding-right: 10px">
		<!-- This allow to maintain all params and then it adds the new one param (language) -->
		<c:url var="engUrl" value="">
			<c:forEach items="${param}" var="entry">
			    <c:if test="${entry.key != 'language'}">
			        <c:param name="${entry.key}" value="${entry.value}" />
			    </c:if>
			</c:forEach>
			<c:param name="language" value="en" />
		</c:url>
		<c:url var="esUrl" value="">
			<c:forEach items="${param}" var="entry">
			    <c:if test="${entry.key != 'language'}">
			        <c:param name="${entry.key}" value="${entry.value}" />
			    </c:if>
			</c:forEach>
			<c:param name="language" value="es" />
		</c:url>
		<a href="${engUrl}"><spring:message
					code="languages.english.label" />
		</a> | 
		<a href="${esUrl}"><spring:message
					code="languages.spanish.label" />
		</a>
	</div>

	<div class="container">
		<div class="header">
			<ul class="nav nav-pills pull-right">
				<tiles:insertAttribute name="navigation" />
			</ul>
			<h3 class="text-muted"><spring:message code="baseLayout.batsellermanager.header"/></h3>
		</div>

		<div class="jumbotron">
			<h1>
				<!-- Attribute for i18n, this allow us to use springmessages' keys in tile-definition.xml -->
				<tilesx:useAttribute id="headingKey" name="heading"/>
				<spring:message code="${headingKey}"/>
			</h1>
			<p>
				<!-- Attribute for i18n, this allow us to use springmessages' keys in tile-definition.xml -->
				<tilesx:useAttribute id="taglineKey" name="tagline"/>
				<spring:message code="${taglineKey}"/>
			</p>
		</div>

		<div class="row">
			<tiles:insertAttribute name="content" />
		</div>

		<div class="footer">
			<tiles:insertAttribute name="footer" />
		</div>

	</div>
</body>
</html>