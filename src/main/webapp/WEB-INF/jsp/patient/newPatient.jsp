<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="/WEB-INF/jsp/common/header.jsp" />

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url var="validationJs" value="/js/patient-validation.js" />
<script src="${validationJs}"></script>


<c:url var="formAction" value="/users/new/patient" />
<form:form modelAttribute="patient" method="POST" action="${formAction}" name="patientForm">
    <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
    <div class="row">
        <div class="col-xs-4"></div>
        <div class="col-xs-4 mobile" >
            <h2>Sign Up</h2>
            <div class="form-group">
                <form:label path="firstName">First Name: </form:label>
                <form:input path="firstName" cssClass="form-control" placeHolder="First Name" />
                <form:errors path="firstName" cssClass="error" />
            </div>
            <div class="form-group">
                <form:label path="lastName">Last Name: </form:label>
                <form:input path="lastName" cssClass="form-control" placeHolder="Last Name" />
                <form:errors path="lastName" cssClass="error" />
            </div>
            <div class="form-group">
                <form:label path="email">Email: </form:label>
                <form:input path="email" cssClass="form-control" placeHolder="Email" />
                <form:errors path="email" cssClass="error" />
            </div>
            <div class="form-group">
                <form:label path="age">Age: </form:label>
                <form:input path="age" cssClass="form-control" placeHolder="Age" />
                <form:errors path="age" cssClass="error" />
            </div>
            <div class="form-group">
                <form:label path="gender">Gender: </form:label>
                <form:select  path="gender">
                    <form:option value="Male">Male</form:option>
                    <form:option value="Female">Female</form:option>
                    <form:option value="Other">Other</form:option>
                </form:select>
            </div>
            <button type="submit" id="newUserButton" class="btn btn-primary">Create User</button>
        </div>
        <div class="col-xs-4"></div>
    </div>
</form:form>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />

