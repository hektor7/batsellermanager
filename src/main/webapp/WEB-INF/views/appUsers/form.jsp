<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<section class="container">
	<form:form modelAttribute="newAppUser" class="form-horizontal">
		<form:errors path="*" cssClass="alert alert-danger" element="div" />
		<fieldset>
			<legend>
				<spring:message code="appUser.form.formTitle" />
			</legend>

			<div class="form-group">
				<label class="control-label col-lg-2 col-lg-2" for="iUserName">
					<spring:message code="appUser.form.userName.label" />
				</label>
				<div class="col-lg-10">
					<form:input id="iUserName" path="userName" type="text"
						class="form:input-large" />
					<form:errors path="userName" cssClass="text-danger" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-lg-2 col-lg-2" for="iName">
					<spring:message code="appUser.form.name.label" />
				</label>
				<div class="col-lg-10">
					<form:input id="iName" path="name" type="text"
						class="form:input-large" />
					<form:errors path="name" cssClass="text-danger" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-lg-2 col-lg-2" for="iFirstSurname">
					<spring:message code="appUser.form.firstSurname.label" />
				</label>
				<div class="col-lg-10">
					<form:input id="iFirstSurname" path="firstSurname" type="text"
						class="form:input-large" />
					<form:errors path="firstSurname" cssClass="text-danger" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-lg-2 col-lg-2" for="iSecondSurname">
					<spring:message code="appUser.form.secondSurname.label" />
				</label>
				<div class="col-lg-10">
					<form:input id="iFirstSurname" path="secondSurname" type="text"
						class="form:input-large" />
					<form:errors path="secondSurname" cssClass="text-danger" />
				</div>
			</div>

			
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<input type="submit" id="btnAdd" class="btn btn-primary"
						value="<spring:message code=  "appUser.form.AddButton.label"/>" />
				</div>
			</div>
		</fieldset>
	</form:form>
</section>