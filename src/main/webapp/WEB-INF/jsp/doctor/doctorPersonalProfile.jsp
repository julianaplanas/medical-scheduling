<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp" />

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2 class="text-center title-doctor-list">Personal Profile</h2>

<div class="table-profile">
    <ul class="list-group">
        <li class="list-group-item"><strong>First Name: </strong> ${doctor.firstName}</li>
        <li class="list-group-item"><strong>Last Name: </strong> ${doctor.lastName}</li>
        <li class="list-group-item"><strong>Email: </strong> ${doctor.email}</li>
        <li class="list-group-item"><strong>Address: </strong> ${doctor.address}</li>
        <li class="list-group-item"><strong>Phone Number: </strong> ${doctor.phoneNumber}</li>
        <li class="list-group-item"><strong>Medical Specialty: </strong> ${doctor.medicalSpecialty}</li>
        <li class="list-group-item"><strong>Hourly Cost: </strong>$ ${doctor.hourCost}</li>
        <li class="list-group-item">
            <p class="text-center"><strong>Available Hours: </strong> Monday to Friday, from ${startTime}:00 to ${endTime}:00</p>
        </li>
    </ul>
</div>
<span class="button-center">
    <c:url var="updateHours" value="/doctor/profile/update"/>
    <button type="button" class="btn btn-primary button-doctor-list"><a href="${updateHours}">Update Hours</a></button>
    <c:url var="updateInfo" value="/users/update/doctor"/>
    <button type="button" class="btn btn-primary button-doctor-list"><a href="${updateInfo}">Update Information</a></button>
</span>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />
