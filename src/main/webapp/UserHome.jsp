<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success</title>
        <link rel="stylesheet" type="text/css" href="/ecommerce/styles/main.css">
    </head>
    <body>
        <h1>Success!</h1>
        <h2>Welcome : <bean:write name="LoginForm" property="userName"></bean:write></h2>
    </body>
</html>