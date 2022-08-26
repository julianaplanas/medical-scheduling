<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp" />

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<h2 class="text-center">Prescriptions</h2>

<div class="prescription-list-centre">
    <div class="div-complete-prescription-list">
        <c:forEach var="prescription" items="${prescription}">

            <div class="div-prescription">
                <div class="div-prescription-patient">
                    <p class="prescription-name"> <strong>Patient:</strong> ${prescription.value.firstName} ${prescription.value.lastName}</p>
                </div>

                <div class="prescription">
                    <div class="div-prescription-logo">
                        <c:url var="imgSrc" value="/img/logo.png" />
                        <img src="${imgSrc}" class="img-fluid prescription-logo" alt="iSchedule" />
                    </div>
                    <div class="div-prescription-name">
                        <strong>Prescription name:</strong>
                        <p class="prescription-name"> ${prescription.key.prescriptionName}</p>
                    </div>
                    <div class="div-prescription-cost">
                        <strong>Cost:</strong>
                        <p class="prescription-cost"> $${prescription.key.cost}</p>
                    </div>
                    <div class="div-prescription-firm">
                        <strong>Doctor firm:</strong>
                        <p class="prescription-firm"> ${doctor.firstName} ${doctor.lastName}</p>
                    </div>
                </div>
            </div>


        </c:forEach>

    </div>
</div>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />