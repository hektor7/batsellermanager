<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section class="container">
	<div class="pull-right" >
			<a href="<spring:url value="/appUsers/add" />" class="btn btn-default">
					<span class="glyphicon glyphicon-plus"></span> <spring:message
						code="appUser.list.add" />
				</a>
	</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th><spring:message code="appUser.list.columnHeader.userName" /></th>
				<th><spring:message code="appUser.list.columnHeader.name" /></th>
				<th><spring:message code="appUser.list.columnHeader.firstSurname" /></th>
				<th><spring:message code="appUser.list.columnHeader.secondSurname" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${appUsers}" var="appUser">
				<tr>
					<td>${appUser.userName}</td>
					<td>${appUser.name}</td>
					<td>${appUser.firstSurname}</td>
					<td>${appUser.secondSurname}</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>

</section>