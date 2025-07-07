
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,initial-scale=1.0">
  <title>Upload New Video</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
   </head>
<body class="page-wrapper" data-context-path="${pageContext.request.contextPath}">
  <header>
    <h1>Upload new Video</h1>
  </header>

  
<div class="main">
  <div class="login-container">

      <c:if test="${not empty error}">
        <div id="error-message" class="alert-error">${error}</div>
      </c:if>

      <div class="login-card">
        <div class="login-card-header">
          <h3>Upload New Video</h3>
        </div>
        <div class="login-card-body">
          <form id="upload-video-form"
          action = "${pageContext.request.contextPath}/upload" method="POST"
          >

            <div class="form-group">
              <label for="url">Emmbed URL</label>
              <input type="text"
                     id="url"
                     name="url"
                     class="form-control"
                     value="${url}"
                     required>
            </div>

            <div class="form-group">
              <label for="title">Title</label>
              <input type="text"
                     id="title"
                     name="title"
                     class="form-control"
                     value="${title}"
                     required>
            </div>

            <div class="form-group">
              <label for="customImage">Custom Image</label>
              <input type="text"
                     id="customImage"
                     name="customImage"
                     class="form-control"
                     value="${customImage}">
            </div>
            <div class="form-actions">
                <button type="submit" class="btn btn-primary", id="uploadBtn">Upload</button>
            </div>
          </form>
        </div>
        <div class="login-card-footer">
          <a href="${pageContext.request.contextPath}/" class="link-secondary">Back to Home</a>
        </div>

      </div>
      <!-- /login-card -->

    </div>
</div>

<footer>
  <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>

