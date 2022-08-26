<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp" />

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2 class="text-center title-doctor-list">Update Personal Information</h2>

<c:url var="formAction" value="/users/update/doctor" />
<form method="POST" action="${formAction}" modelAttribute="doctor">
    <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
    <div class="row">
        <div class="col-xs-4"></div>
        <div class="col-xs-4 mobile">
            <input type="hidden" id="doctorId" name="doctorId" class="form-control" value="${doctor.doctorId}" />
            <div class="form-group">
                <label for="email">Email: </label>
                <input type="text" id="email" name="email" placeHolder="Email" class="form-control" value="${doctor.email}" />
            </div>
            <div class="form-group">
                <label for="address">Address: </label>
                <input type="text" id="address" name="address" placeHolder="Address" class="form-control" value="${doctor.address}"/>
            </div>
            <div class="form-group">
                <label for="phoneNumber">Phone Number: </label>
                <input type="number" id="phoneNumber" name="phoneNumber" placeHolder="Phone Number" class="form-control" value="${doctor.phoneNumber}"/>
            </div>
            <div class="form-group">
                <label for="medicalSpecialty">Medical Specialty:</label>
                <select name="medicalSpecialty"	id="medicalSpecialty">
                    <c:forEach var="specialty" items="${specialtyList}">
                        <c:if test="${!specialty.equals('All')}">
                            <c:if test="${specialty.equalsIgnoreCase(doctor.medicalSpecialty)}">
                                <option value="${specialty}" selected>${specialty} </option>
                            </c:if>
                            <c:if test="${!specialty.equalsIgnoreCase(doctor.medicalSpecialty)}">
                                <option value="${specialty}">${specialty} </option>
                            </c:if>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="hourCost">Cost Per Hour: </label>
                <input type="number" id="hourCost" name="hourCost" placeHolder="Cost Per Hour" class="form-control" value="${doctor.hourCost}"/>
            </div>
                <button type="submit" class="btn btn-primary">Update Profile</button>
        </div>
        <div class="col-xs-4"></div>
    </div>
</form>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />