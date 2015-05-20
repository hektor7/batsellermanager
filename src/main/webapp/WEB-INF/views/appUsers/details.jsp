<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section class="container">
	<div class="row">
		<div class="col-md-5">
			<h3>${appUser.userName}</h3>

			<p>
				<strong><spring:message code="appUser.details.name.label" /></strong>
				: ${appUser.name}
			</p>
			<p>
				<strong><spring:message
						code="appUser.details.firstSurname.label" /></strong> :
				${appUser.firstSurname}
			</p>
			<p>
				<strong><spring:message
						code="appUser.details.secondSurname.label" /></strong> :
				${appUser.secondSurname}
			</p>
			<p>
				<a href="<spring:url value="/appUsers" />" class="btn btn-default">
					<span class="glyphicon-hand-left glyphicon"></span> <spring:message
						code="appUser.details.back" />
				</a>
			</p>
		</div>
	</div>
</section>