<%-- Created by IntelliJ IDEA. --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <html>
    <head>
        <title>Index</title>
        <style type="text/css">
            body{
                background: url("/resources/img/background.jpg");
                background-size: cover;
            }


            .login{
                border: 3px solid #f4421a;
                color: black;
                padding: 15px 32px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
            }

        </style>
    </head>
    <body>

    <br/>
    <form>

        <input class="login" type="button" value="Войти" onClick='location.href="/jsp/login.jsp"'>
    </form>
    <form>
        <input class="login" type="button" value="Нужна регистрация?" onClick='location.href="/jsp/register.jsp"'>
    </form>
    </body>
    </html>
