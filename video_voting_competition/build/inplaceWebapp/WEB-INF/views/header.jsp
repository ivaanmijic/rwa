<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<header>

  <c:set var="isAdmin" value="false" />
  <c:forEach var="r" items="${sessionScope.role}">
    <c:if test="${r == 'ADMIN'}">
       <c:set var="isAdmin" value="true" />
    </c:if>
  </c:forEach>

  <div class="left-container">
    <h1>Video Voting Competition</h1>
    <c:if test="${isAdmin}">
    <button type="submit" onclick="window.location.href='${pageContext.request.contextPath}/upload'" class="btn btn-primary">Upload</button>
    </c:if>
  </div>



<div class="right-controls">
  
  <div class="auth-controls">
  <c:choose>
    <c:when test="${not empty sessionScope.user}">
      <div class="user-info">
        <span class="greeting">Hi, ${sessionScope.username}</span>


<c:set var="isAdmin" value="false" />
<c:forEach var="r" items="${sessionScope.role}">
  <c:if test="${r == 'ADMIN'}">
    <c:set var="isAdmin" value="true" />
  </c:if>
</c:forEach>

<c:if test="${isAdmin}">
  <a href="${pageContext.request.contextPath}/admin/dashboard" class="ranking-btn">Admin Panel</a>
</c:if>

        <a href="${pageContext.request.contextPath}/logout" class="ranking-btn">Logout</a>
      </div>
    </c:when>
    <c:otherwise>
      <a href="${pageContext.request.contextPath}/login" class="ranking-btn">Login</a>
    </c:otherwise>
  </c:choose>
</div>

  <a href="${pageContext.request.contextPath}/ranking?page=1" class="ranking-btn">Rankings</a>

  <span class="header-icon">
    <button id="refresh-btn" class="icon-button" type="button">
      <img src="${pageContext.request.contextPath}/assets/images/refresh.svg" alt="Refresh"/>
    </button>
  </span>

  <span >
   <div class="share-wrapper">
     <span class="header-icon">
  <img id="share-btn"
       src="${pageContext.request.contextPath}/assets/images/share.svg"
       alt="Share"
       style="cursor: pointer;" />
     </span>

  <ul id="share-menu" class="popup-menu">
    <li onclick="navigator.clipboard.writeText(window.location.href)
                  .then(()=>alert('Link copied!'))
                  .catch(()=>alert('Copy failed'))">
      Copy link
    </li>
    <li onclick="window.open('https://twitter.com/intent/tweet?url='+encodeURIComponent(window.location.href))">
      Share on Twitter
    </li>
    <li onclick="window.open('https://www.facebook.com/sharer/sharer.php?u='+encodeURIComponent(window.location.href))">
      Share on Facebook
    </li>
  </ul>
</div>
  </span>

</header> 

