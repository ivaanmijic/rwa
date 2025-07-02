<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1"/>
    <title>Rankings</title>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css?family=Inter" rel="stylesheet"/>
    <script>window.ctxPath='${pageContext.request.contextPath}';</script>
  </head>
  <body>
    <div class="main">
      <header>
        <h1>Rankings</h1>
        <button onclick="location='${pageContext.request.contextPath}/index.jsp'">Back</button>
      </header>

      <div id="ranking-container">
        <!-- JS will inject table here -->
      </div>

      <footer>
        <div class="pagination">
          <c:if test="${hasPrevBlock}">
            <button onclick="goToPage(${startPage-1})">&lt;&lt;</button>
          </c:if>

          <c:forEach begin="${startPage}" end="${endPage}" var="i">
            <button class="${i == currentPage ? 'active' : ''}" onclick="goToPage(${i})">${i}</button>
          </c:forEach>

          <c:if test="${hasNextBlock}">
            <button onclick="goToPage(${endPage+1})">&gt;&gt;</button>
          </c:if>
        </div>
      </footer>
    </div>

    <script src="${pageContext.request.contextPath}/js/ranking-page.js"></script>
  </body>
</html>
