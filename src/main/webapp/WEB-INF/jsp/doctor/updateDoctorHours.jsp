<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp" />

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<h2 class="text-center">Update Available Hours</h2>

<p class="text-center"><strong>Available Hours: </strong> Monday to Friday, from ${startTime}:00 to ${endTime}:00</p>

<p class="text-center">Appointments can only be scheduled on the hour (e.g. 10am) between 9:00 to 18:00</p>

<div class="div-doctor-hours">
    <c:url var="formAction" value="/users/profile/update"/>
    <form method="POST" action="${formAction}" modelAttribute="availability" class="form-doctor-hour">
        <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
        <div class="row form-doctor-hour">
            <div class="col-xs-4  form-doctor-hour">
                <div class="form-group">
                    <label for="startingTime">From: </label>
                    <input type="time" id="startingTime" name="startingTime"
                           min="09:00" max="18:00" step-minute="900" required>
                </div>

                <div class="form-group">
                    <label for="endingTime">To: </label>
                    <input type="time" id="endingTime" name="endingTime"
                           min="09:00" max="18:00" required>
                </div>
                <button type="submit" class="btn btn-primary button-doctor-hour">Update Hours</button>
            </div>
        </div>
    </form>
</div>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />

