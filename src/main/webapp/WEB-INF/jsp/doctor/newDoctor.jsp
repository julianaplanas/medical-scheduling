<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="/WEB-INF/jsp/common/header.jsp" />

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url var="validationJs" value="/js/doctor-validation.js" />
<script src="${validationJs}"></script>



<c:url var="formAction" value="/users/new/doctor" />
<form:form modelAttribute="doctor" method="POST" action="${formAction}" name="doctorForm">
    <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
    <div class="row">
        <div class="col-xs-4"></div>
        <div class="col-xs-4 mobile">
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
                <form:label path="address">Address: </form:label>
                <form:input path="address" cssClass="form-control" placeHolder="Address" />
                <form:errors path="address" cssClass="error" />
            </div>
            <div class="form-group">
                <form:label path="phoneNumber">Phone Number: </form:label>
                <form:input path="phoneNumber" cssClass="form-control" placeHolder="Phone Number" />
                <form:errors path="phoneNumber" cssClass="error" />
            </div>
            <div class="form-group">
                <form:label path="medicalSpecialty">Medical Specialty: </form:label>
                <form:select path="medicalSpecialty">
                    <c:forEach var="specialty" items="${specialtyList}">--%>
                          <c:if test="${!specialty.equals('All')}">--%>
                                 <form:option value="${specialty}">${specialty} </form:option>--%>
                          </c:if>
                    </c:forEach>
                </form:select>
            </div>
            <div class="form-group">
                <form:label path="hourCost">Cost Per Hour: </form:label>
                <form:input path="hourCost" cssClass="form-control" placeHolder="Cost Per Hour" />
                <form:errors path="hourCost" cssClass="error" />
            </div>

            <button type="submit" id="newUserButton" class="btn btn-primary">Create User</button>
        </div>
        <div class="col-xs-4"></div>
    </div>
</form:form>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />