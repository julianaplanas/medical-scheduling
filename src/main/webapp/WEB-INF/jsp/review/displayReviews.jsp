<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<h2 class="text-center title-doctor-list">Your Reviews</h2>

<div class="doctor-list-centre">
    <div class="container-reviews">

        <c:forEach var="review" items="${reviews}">

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

        </c:forEach>

    </div>
</div>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />