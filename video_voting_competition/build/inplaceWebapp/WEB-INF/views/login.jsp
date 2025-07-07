<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet"/>
    <script>window.ctxPath='${pageContext.request.contextPath}';</script>
</head>
<body class="page-wrapper" data-context-path="${pageContext.request.contextPath}">
    <div class="main">
        <div class="login-container">
            <div class="login-card">
                <div class="login-card-header">
                    <h3>Login</h3>
                </div>
                <div class="login-card-body">
                    <c:if test="${not empty error}">
                        <div class="alert alert-error">${error}</div>
                    </c:if>

                    <form class="login-form"
                        action = "${pageContext.request.contextPath}/login" method="POST">
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" id="username" name="username" class="form-control" required />
                        </div>

                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" id="password" name="password" class="form-control" required />
                        </div>

                        <div class="form-group form-check">
                            <input type="checkbox" id="rememberMe" name="rememberMe" class="form-check-input" />
                            <label for="rememberMe" class="form-check-label">Remember me</label>
                        </div>

                        <div class="form-actions">
                            <button type="submit" class="btn btn-primary", id="btnSubmit">Login</button>
                        </div>
                    </form>
                </div>
                <div class="login-card-footer">
                    <a href="${pageContext.request.contextPath}/" class="link-secondary">Back to Home</a>
                </div>
            </div>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/js/LoginRequest.js"></script>
</body>
</html>
