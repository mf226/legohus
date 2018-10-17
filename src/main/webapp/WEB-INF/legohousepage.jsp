<%-- 
    Document   : legohousepage
    Created on : Oct 16, 2018, 11:19:47 PM
    Author     : MadsF
--%>

<%@page import="Logic.Legohouse"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet"
              type = "text/css"
              href = "DefaultStyle.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Part list</h1>
        <%= request.getAttribute("pcelist")%>

        <table>
            <tr>
                <td>
                    <form name="createOrder" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="createOrder">
                        <input type="submit" value="Finalize order">
                    </form>
                </td>
                <td>
                    <form name="login" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="login">
                        <input type="submit" value="Make different house">
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>
