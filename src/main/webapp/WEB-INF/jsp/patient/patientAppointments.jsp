<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<h2 class="text-center title-doctor-list">Upcoming Appointments</h2>

<div class="flex-container-upcoming-appointment">

  <c:forEach var="appointment" items="${appointments}">

    <div class="container-upcoming-appointment">
      <p><strong>Day:</strong> ${appointment.key.dayOfWeek}</p>
      <p><strong>Hour:</strong> ${appointment.key.startingTime}</p>
      <p><strong>Doctor:</strong> ${appointment.value.firstName} ${appointment.value.lastName}</p>
      <c:if test="${appointment.key.confirmed}">
        <p><strong> Status: Confirmed</strong></p>
      </c:if>
      <c:if test="${appointment.key.confirmed == false}">
        <p><strong> Status: Canceled</strong></p>
      </c:if>

      <div class="buttons-appointments">

        <c:if test="${appointment.key.confirmed == true}">
          <button type="button" class="btn btn-info btn-danger" data-toggle="modal" data-target="#myModal">Cancel
            Appointment
          </button>

          <!-- Modal -->
          <div id="myModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
              <!-- Modal content-->
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
                  <h4 class="modal-title">Cancel Appointment</h4>
                </div>
                <div class="modal-body">
                  <p>Do you want to cancel your appointment for ${appointment.key.dayOfWeek}
                    at ${appointment.key.startingTime} with
                    Dr: ${appointment.value.firstName} ${appointment.value.lastName}
                    ?</p>
                </div>
                <div class="modal-footer">
                  <c:url var="deleteAppointment" value="/patient/appointments?id=${appointment.key.appointmentId}"/>
                  <form id="logoutForm" action="${deleteAppointment}" method="POST">
                    <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
                    <button type="submit" class="btn btn-primary" href="#"> Confirm</button>
                    <button type="button" class="btn btn-outline-primary me-2" data-dismiss="modal">Go back</button>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </c:if>

      </div>
    </div>
  </c:forEach>

</div>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />