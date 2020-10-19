<%-- 
    Document   : result
    Created on : Oct 17, 2020, 3:35:03 PM
    Author     : catal
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Result</title>
    </head>
    <body>
        <h1>Your dictionary</h1>

        <c:import var = "resource" url = "http://localhost:8080/Lab2/languages.xml"/>
        <x:parse xml = "${resource}" var = "output"/>
        <ul>
            <x:forEach select="$output/root/dictionary/entry" var="item">
                <li>
                    <x:out select="$item/word" /> : <x:out select="$item/description" /> <b><x:out select="$item/language" /></b>
                </li>
            </x:forEach>
        </ul>
</body>
</html>
