
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>${param.title} Video Voting Competition</title>
    <link href="https://fonts.googleapis.com/css?family=Inter" rel="stylesheet"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script>
        window.ctxPath = "${pageContext.request.contextPath}";
    </script>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet"/>
    </head>

    <body data-context-path="${pageContext.request.contextPath}">

      <%
      String info = (String) session.getAttribute("info");
      if (info != null) {
        session.removeAttribute("info");
      %>
      <script>alert("<%= info %>");</script>
      <%
      }
      %>

      <div class="page-wrapper">
        <jsp:include page="/WEB-INF/views/header.jsp"/>

        <div class="main">

          
          <div class="iframe-container" id="rand-videos-container">
            

          </div>

          <div class="rank-container" id="top-five-container">
            
          </div>
        </div>

        <jsp:include page="/WEB-INF/views/footer.jsp"/>
      </div>


      <script src="${pageContext.request.contextPath}/js/AuthStatus.js"></script>
      <script src="${pageContext.request.contextPath}/js/GlobalUtils.js"></script>
      <script src="${pageContext.request.contextPath}/js/TopFiveVideosLoader.js"></script>
      <script src="${pageContext.request.contextPath}/js/RandomVideosLoader.js"></script>
      <script src="${pageContext.request.contextPath}/js/LinksManager.js"></script>
      <script src="${pageContext.request.contextPath}/js/RankingTableLoader.js"></script>


    </body>
</html>
