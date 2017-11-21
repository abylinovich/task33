<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<div class="container" align="center">
        <form name="loginForm" action="/action" method="get">
        <input type="hidden" name="command" value="SAX" />
        <div>
            <input type="submit" value="SAX" />
        </div>
        </form>
        <form name="loginForm" action="/action" method="get">
            <input type="hidden" name="command" value="StAX" />
            <div>
                <input type="submit" value="StAX" />
            </div>
        </form>
        <form name="loginForm" action="/action" method="get">
            <input type="hidden" name="command" value="DOM" />
            <div>
                <input type="submit" value="DOM" />
            </div>
        </form>
</div>
</body>
</html>
