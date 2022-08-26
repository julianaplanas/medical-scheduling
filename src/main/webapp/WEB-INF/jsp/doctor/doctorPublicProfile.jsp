<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp" />

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<h2 class="text-center title-doctor-list">${doctor.firstName} ${doctor.lastName} </h2>

<h3 class="text-center title-doctor-list">Doctor Information</h3>

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
            <p class="text-center"><strong>Available Hours: </strong> Monday to Friday, from ${startTime}:00 to ${endTime}:00</p></li>
        <li class="list-group-item"><strong>Rating: </strong>
            <c:choose>
                <c:when test="${rating <= 0}">
                    <span>No rating available yet.</span>
                </c:when>

                <c:otherwise>
                    <div>
                        <c:forEach begin="1" end="5" var="count">
                            <span class="rating-star-general ${count <= rating? 'filled':'' }">&#9734;</span>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
        </li>
    </ul>
</div>
<span class="button-center">
    <span class="div-button-doctor-list">
        <button type="button" class="btn btn-outline-primary me-2" onclick="history.back()">Go Back</button>
    </span>

    <c:if test="${not empty currentUser && currentUser.isPatient()}">
        <span class="div-button-doctor-list">
            <c:url var="makeAppointment" value="/appointment/doctor?id=${doctor.doctorId}"/>
            <button type="button" class="btn btn-primary"><a href="${makeAppointment}">Make An Appointment</a></button>
        </span>
    </c:if>
    <c:if test="${empty currentUser}">
        <c:url var="loginHref" value="/login" />
        <span>
            <button class="btn btn-primary"><a href="${loginHref}">Log In to book an appointment</a></button>
        </span>
    </c:if>
    <c:if test="${not empty currentUser && currentUser.isPatient()}">
    <span class="div-button-doctor-list">
        <c:url var="makeReview" value="/doctor-list/public-profile/review?id=${doctor.doctorId}"/>
        <button type="button" class="btn btn-primary button-doctor-list"><a href="${makeReview}">Make a Review</a></button>
    </span>
    </c:if>
</span>
<div class="container-reviews--profile">
    <ul>
        <c:forEach var="review" items="${reviews}">
            <li>
                <div class="container-review">
                    <div class="header-review border-bottom">
                        <div>
                            <h3>${review.key.title}</h3>
                            <p>${review.value.firstName} ${review.value.lastName}</p>
                        </div>
                        <div class="rating">
                            <c:forEach begin="1" end="5" var="rating">
                                <span class="rating-star ${rating<= review.key.rating? 'filled':'' }">&#9734;</span>
                            </c:forEach>
                        </div>
                    </div>
                    <p>${review.key.description}</p>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />
