<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="/WEB-INF/jsp/common/header.jsp" />

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url var="validator" value="/js/appointment-validation.js" />
<script src="${validator}"></script>


<h2>New Appointment</h2>
<p>Take an appointment with <strong>${doctor.firstName} ${doctor.lastName}</strong></p>
<p><strong>Medical Specialty: </strong> ${doctor.medicalSpecialty}</p>
<p><strong>Available Hours: </strong> Monday to Friday, from ${startTime}:00 to ${endTime}:00</p>

<button type="button" class="btn btn-outline-primary me-2 button-doctor-list" onclick="history.back()">Go Back</button>

<c:url var="formAction" value="/appointment/doctor/${doctor.doctorId}" />
<form method="POST" action="${formAction}">
    <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
    <div class="container-appointments">
        <div>
            <div class="col-12">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Hours</th>
                        <th scope="col">Monday</th>
                        <th scope="col">Tuesday</th>
                        <th scope="col">Wednesday</th>
                        <th scope="col">Thursday</th>
                        <th scope="col">Friday</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach begin="${startTime}" end="${endTime - 1}" var="step">
                        <tr>
                            <td>
                                ${step}:00
                            </td>
                            <td>
                                <div class="custom-control custom-checkbox">
                                    <input type="radio" class="custom-control-input radio" value="Monday-${step}" name="appOption" >
                                    <label class="custom-control-label" for="Monday-${step}">Select</label>
                                </div>
                            </td>
                            <td>
                                <div class="custom-control custom-checkbox">
                                        <input type="radio" class="custom-control-input radio" value="Tuesday-${step}" name="appOption" >
                                    <label class="custom-control-label" for="Tuesday-${step}">Select</label>
                                </div>
                            </td>
                            <td>
                                <div class="custom-control custom-checkbox">
                                    <input type="radio" class="custom-control-input radio" value="Wednesday-${step}" name="appOption" >
                                    <label class="custom-control-label" for="Wednesday-${step}">Select</label>
                                </div>
                            </td>
                            <td>
                                <div class="custom-control custom-checkbox">
                                    <input type="radio" class="custom-control-input radio" value="Thursday-${step}" name="appOption" >
                                    <label class="custom-control-label" for="Thursday-${step}">Select</label>
                                </div>
                            </td>
                            <td>
                                <div class="custom-control custom-checkbox">
                                    <input type="radio" class="custom-control-input radio" value="Friday-${step}" name="appOption"  >
                                    <label class="custom-control-label" for="Friday-${step}">Select</label>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <input type="text" name="appointmentCount" class="validator-hidden-control">
                <span class="div-button-doctor-list">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" id="makeAppointmentButton">Make appointment</button>
                </span>
                <!-- Modal -->
                <div id="myModal" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Confirm appointment</h4>
                            </div>
                            <div class="modal-body">
                                <p id="confirmMessage">Do you want to confirm your appointment with ${doctor.firstName} ${doctor.lastName}?</p>
                                <p id="errorMessage">Please selecte an appointment time to continue</p>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary" id="confirmButton">Confirm</button>
                                <button type="button" class="btn btn-outline-primary me-2" data-dismiss="modal">Go back</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />
