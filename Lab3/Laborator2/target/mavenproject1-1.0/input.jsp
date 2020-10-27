<%-- 
    Document   : input
    Created on : Oct 17, 2020, 1:09:13 PM
    Author     : catal
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>

<html>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Input</title>
    </head>
    <body>
        <h1>Insert word into dictionary</h1>
        <h3>Please select the language, the word and its definition</h3>
        <c:import var = "resource" url = "http://localhost:8080/Lab2/languages.xml"/>
        <x:parse xml = "${resource}" var = "output"/>

        <form action="controller" method="POST">
            Language:

            <select name="languages" >
                <x:forEach select="$output/root/languages/language" var="item">
                <option value="<x:out select="$item" />"/>
                    <x:out select="$item" />
                </option>
                </x:forEach>
            </select>
            Word: <input type = "text" name = "word">
            Definition: <input type = "text" name = "definition">
            <input type = "submit" value = "Add" />
        </form>
    </body>
</html>
