<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="/WEB-INF/jsp/common/header.jsp" />

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2 class="text-center">Create new prescription</h2>

<c:url var="formAction" value="/doctor/appointments/prescription/${param.id}" />
<form method="POST" action="${formAction}" model="prescription" />
  <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
  <div class="row">
    <div class="col-xs-4"></div>
    <div class="col-xs-4">
      <div class="form-group">
        <label for="prescriptionName">Prescription name: </label>
        <input type="text" id="prescriptionName" name="prescriptionName" placeHolder="Name" class="form-control" value="${prescription.prescriptionName}" />
      </div>
      <div class="form-group">
        <label for="cost">Cost: </label>
        <input type="number" id="cost" name="cost" placeHolder="0" class="form-control" value="${prescription.cost}"/>
      </div>
      <button type="submit" class="btn btn-primary">Send prescription</button>
      <button type="button" class="btn btn-outline-primary me-2 go-back-button" onclick="history.back()">Go Back</button>
    </div>
    <div class="col-xs-4"></div>
  </div>
</form>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />