<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<section class="homepage">
    <div class="homepage-text">
        <h1>Welcome to iSchedule</h1>
        <p>The world's number one Patient and Doctor Appointment Scheduling Software</p>
        <c:url var="doctorList" value="/doctor-list"/>
        <c:if test="${!currentUser.isDoctor()}">
            <a href="${doctorList}">
                <button class="btn btn-primary homepage-button">Find a Doctor</button>
            </a>
        </c:if>
    </div>
</section>

<c:if test="${not empty currentUser && currentUser.isDoctor()}">
    <div style="position:absolute; top: 200px; right: 50px;">
        <div style="display: flex; flex-direction: column;">

            <c:forEach var="appointment" items="${appointments}" begin="0" end="1">
                <div class="toast ${!appointment.key.confirmed ? "toast-cancelled" : ""}" role="alert" aria-live="assertive" aria-atomic="true" id="notification">
                    <div class="toast-header" style="display: block">
                        <strong class="mr-auto">${!appointment.key.confirmed ? "Appointment Cancelled!" : "Appointment Alert!"}</strong>
                        <small>${appointment.value.firstName} ${appointment.value.lastName}</small>
                        <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close" onclick="removeContent(this)">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="toast-body">
                        <c:if test="${appointment.key.confirmed}">
                            You have an appointment ${appointment.key.dayOfWeek} at ${appointment.key.startingTime}hs.
                        </c:if>
                        <c:if test="${!appointment.key.confirmed}">
                            Your appointment of ${appointment.key.dayOfWeek} at ${appointment.key.startingTime}hs was cancelled.
                        </c:if>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</c:if>

<c:if test="${not empty currentUser && currentUser.isPatient()}">
    <div style="position:absolute; top: 200px; right: 50px;">
        <div style="display: flex; flex-direction: column;">

            <c:forEach var="patientApp" items="${patientApps}" begin="0" end="${patientApps.size()}">
                <c:if test="${!patientApp.key.confirmed}">
                    <div class="toast toast-cancelled" role="alert" aria-live="assertive" aria-atomic="true" id="notification">
                        <div class="toast-header" style="display: block">
                            <strong class="mr-auto">Appointment Cancelled!</strong>
                            <small>${patientApp.value.firstName} ${patientApp.value.lastName}</small>
                            <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close" onclick="removeContent(this)">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="toast-body">
                            Your appointment of ${patientApp.key.dayOfWeek} at ${patientApp.key.startingTime}hs was cancelled.
                        </div>
                    </div>
                </c:if>
            </c:forEach>

        </div>
    </div>
</c:if>

<c:import url="/WEB-INF/jsp/common/footer.jsp"/>