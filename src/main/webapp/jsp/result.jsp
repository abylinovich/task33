<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--Based on pagination example:
http://www.javaknowledge.info/jstl-1-1-paginate-table-example-with-next-previous-features/--%>


<c:set var="catalog" value="${requestScope.catalog}"/>
<c:set var="command" value="${param.command}"/>
<c:set var="parserName" value="${param.parserName}"/>
<c:set var="elementsPerPage" value="3" />
<c:set var="pageNumber" value="${param.pageNumber}"/>
<c:set var="a">
    <fmt:formatNumber value="${catalog.size()/elementsPerPage}" maxFractionDigits="0"/>
</c:set>
<c:set var="b" value="${catalog.size()/elementsPerPage}" />

<c:choose>
    <c:when test="${a==0}">
        <c:set var="numberOfPages" value="1" scope="session"/>
    </c:when>

    <c:when test="${b>a}">
        <c:set var="xxx" value="${b%a}"/>
        <c:if test="${xxx>0}">
            <c:set var="numberOfPages" value="${b-xxx+1}" scope="session"/>
        </c:if>
    </c:when>

    <c:when test="${a>=b}">
        <c:set var="numberOfPages" value="${a}" scope="session"/>
    </c:when>
</c:choose>

<c:set var="start" value="${pageNumber*elementsPerPage-elementsPerPage}"/>
<c:set var="stop" value="${pageNumber*elementsPerPage-1}"/>
    <table class="table table-hover" border="1" width="100%" align="center">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Author</th>
                    <th>Title</th>
                    <th>Genre</th>
                    <th>Price</th>
                    <th>Publish date</th>
                    <th>Description</th>
                </tr>
                </thead>
        <c:forEach items="${requestScope.catalog}" var="book" begin="${start}" end="${stop}">
            <tbody>
            <tr>
                <th>${book.id}</th>
                <td>${book.author}</td>
                <td>${book.title}</td>
                <td>${book.genre}</td>
                <td>$${book.price}</td>
                <td>${book.publishDate}</td>
                <td>${book.description}</td>
            </tr>
            </tbody>
        </c:forEach>
    </table>

    <p>Pages:</p>
    <%--For displaying Previous link --%>
    <c:if test="${pageNumber gt 1}">
        <a href="action?command=${command}&parserName=${parserName}&pageNumber=${pageNumber - 1}">Previous</a>
    </c:if>
    <c:forEach begin="1" end="${numberOfPages}" var="i">
        <c:choose>
            <c:when test="${i!=pageNumber}">
                <a href="action?command=${command}&parserName=${parserName}&pageNumber=<c:out value="${i}"/>"><c:out value="${i}"/></a>
            </c:when>
            <c:otherwise>
                <c:out value="${i}"/>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <%--For displaying Next link --%>
    <c:if test="${pageNumber lt numberOfPages}">
        <a href="action?command=${command}&parserName=${parserName}&pageNumber=${pageNumber + 1}">Next</a>
    </c:if>

    <br/>
    <a href="/">Go back</a>