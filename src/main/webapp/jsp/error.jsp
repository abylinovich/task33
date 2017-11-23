<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<div class="container" align="center">
    <h2>Internal error</h2>
    <h5>Something going wrong. Please, call your system administrator.</h5>
    <p>${requestScope.errorMessage}</p>
    <a href="/">Go back</a>
</div>
</body>
</html>
