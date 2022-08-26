<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<h2 class="text-center title-doctor-list">Personal Profile</h2>

<div class="table-profile">
    <ul class="list-group">
        <li class="list-group-item"><strong>First Name:  </strong>  ${patient.firstName}</li>
        <li class="list-group-item"><strong>Last Name:  </strong>  ${patient.lastName}</li>
        <li class="list-group-item"><strong>Email:  </strong>  ${patient.email}</li>
        <li class="list-group-item"><strong>Age:  </strong>  ${patient.age}</li>
        <li class="list-group-item"><strong>Gender:  </strong>  ${patient.gender}</li>
    </ul>
</div>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />