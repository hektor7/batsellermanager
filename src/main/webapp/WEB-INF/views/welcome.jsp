<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title><spring:message code="welcome.windowTitle" /></title>
</head>
<body>
	<section>
		<div class="pull-right" style="padding-right: 50px">
			<a href="?language=en"> <spring:message
					code="languages.english.label" />
			</a>| <a href="?language=es"> <spring:message
					code="languages.spanish.label" />
			</a>
		</div>
		<div class="jumbotron">
			<div class="container">
				<h1>
					<spring:message code="welcome.header" />
				</h1>
				<p></p>
			</div>
		</div>
	</section>
</body>
</html>