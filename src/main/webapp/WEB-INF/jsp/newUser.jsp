<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="validationJs" value="/js/user-validation.js" />
<script src="${validationJs}"></script>

<c:url var="formAction" value="/users" />
<form:form modelAttribute="user" method="POST" action="${formAction}">
	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
	<div class="row">
		<div class="col-xs-4"></div>
		<div class="col-xs-4 mobile">
			<h2>Sign Up</h2>
			<div class="form-group">
				<form:label path="userName">User Name: </form:label>
				<form:input path="userName" cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="password">Password: </form:label>
				<form:password path="password" cssClass="form-control" />
				<form:errors path="password" cssClass="error" />
			</div>
			<div class="form-group">
				<form:label path="confirmPassword">Confirm Password: </form:label>
				<form:password path="confirmPassword" cssClass="form-control" />
				<form:errors path="confirmPassword" cssClass="error" />
			</div>
			<div class="form-group">
				<form:label path="role">Type: </form:label>
				<form:select  path="role">
					<form:option value="doctor">Doctor</form:option>
					<form:option value="patient">Patient</form:option>
				</form:select>
			</div>
			<button type="submit" id="newUserButton" class="btn btn-primary">Create User</button>
		</div>
		<div class="col-xs-4"></div>
	</div>
</form:form>
		
<c:import url="/WEB-INF/jsp/common/footer.jsp" />

