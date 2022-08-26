<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<h2 class="text-center">Prescriptions</h2>
<p class="text-center">Please, bring the digital prescription to your local pharmacy to pick up your medication</p>

<div class="prescription-list-centre">
    <div class="div-complete-prescription-list">
        <c:forEach var="prescription" items="${prescription}">

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
                    <p class="prescription-firm"> ${prescription.value.firstName} ${prescription.value.lastName}</p>
                </div>
            </div>

        </c:forEach>

    </div>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />