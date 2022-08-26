<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<h2 class="text-center title-doctor-list">Upcoming Appointments</h2>


<div class="flex-container-upcoming-appointment">

  <c:forEach var="appointment" items="${appointments}">

    <div class="container-upcoming-appointment">
      <p><strong>Day:</strong> ${appointment.key.dayOfWeek}</p>
      <p><strong>Hour:</strong> ${appointment.key.startingTime}</p>
      <p><strong>Patient:</strong> ${appointment.value.firstName} ${appointment.value.lastName}</p>
      <c:if test="${appointment.key.confirmed}">
        <p><strong> Status: Confirmed</strong></p>
      </c:if>
      <c:if test="${appointment.key.confirmed == false}">
        <p><strong> Status: Canceled</strong></p>
      </c:if>


      <div class="buttons-appointments">
        <span>
          <c:if test="${appointment.key.confirmed == true}">
          <button type="button" class="btn btn-info btn-danger" data-toggle="modal" data-target="#myModal">Cancel
            Appointment
          </button>
          <!-- Modal -->
            <span id="myModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
              <!-- Modal content-->
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
                  <h4 class="modal-title">Cancel Appointment</h4>
                </div>
                <div class="modal-body">
                  <p>Do you want to cancel your appointment for ${appointment.key.dayOfWeek}
                    at ${appointment.key.startingTime} for
                    Patient: ${appointment.value.firstName} ${appointment.value.lastName} ?</p>
                </div>
                <div class="modal-footer">
                  <c:url var="deleteAppointment" value="/doctor/appointments?id=${appointment.key.appointmentId}"/>
                  <form id="logoutForm" action="${deleteAppointment}" method="POST">
                    <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
                    <button type="submit" class="btn btn-primary" href="#"> Confirm</button>
                    <button type="button" class="btn btn-outline-primary me-2" data-dismiss="modal">Go back</button>
                  </form>
                </div>
              </div>
            </div>
          </span>
        </span>

        <span>
            <c:url var="linkPrescription" value="/doctor/appointments/prescription?id=${appointment.key.patientId}"/>
            <a href="${linkPrescription}">
              <button type="button" class="btn btn-primary">Make prescription</button>
            </a>
        </span>

        </c:if>

        <div>
          <p>
            <button class="btn btn-primary" type="button" onclick="toggleContent(this)" aria-expanded="false"
                    aria-controls="collapseExample"><a>View patient info</a>

            </button>
          </p>
          <div class="collapse" id="collapseExample">
            <div class="card card-body">
              <p><strong>Age:</strong> ${appointment.value.age}</p>
              <p><strong>Gender:</strong> ${appointment.value.gender}</p>
              <p><strong>Email:</strong> ${appointment.value.email}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </c:forEach>
</div>



<c:import url="/WEB-INF/jsp/common/footer.jsp" />