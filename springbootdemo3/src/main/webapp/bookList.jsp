<%--
  Created by IntelliJ IDEA.
  User: liliting
  Date: 2019/3/22
  Time: 下午3:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <table>
        <tr>
            <td>书名</td>
            <td>作者</td>
            <td>价格</td>
            <td>出版时间</td>
        </tr>
        <c:forEach items="${books}" var="b">
            <tr>
                <td>${b.id}</td>
                <td>${b.title}</td>
                <td>${b.author}</td>
                <td>${b.publishdate}</td>
                <td>${b.typeId}</td>
            </tr>
        </c:forEach>

    </table>

</body>
</html>
