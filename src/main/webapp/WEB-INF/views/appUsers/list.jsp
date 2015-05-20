<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section class="container">
	<div class="row">
		<c:forEach items="${appUsers}" var="appUser">
			<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
				<div class="thumbnail">
					<div class="caption">
						<h3>${appUser.userName}</h3>
						<p>${appUser.name}</p>
						<p>$${appUser.firstSurname}</p>
						<p>$${appUser.secondSurname}</p>
						<p>
							<a
								href="<spring:url value="/appUsers/appUser?id=${appUser.id}" htmlEscape="true"/>"
								class="btn btn-primary"> <span
								class="glyphicon-info-sign glyphicon" /></span> <spring:message
									code="products.list.details" />
							</a>
						</p>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</section>