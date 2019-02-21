<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="/ecommerce/styles/main.css">
        </head>
   <body>
       <html:form action="/login" >
           <label for="userName">User Name : </label>
           <html:text name="LoginForm" property="userName" /> <br>
           <label for="password">Password  :</label>
           <html:password name="LoginForm" property="password" /> <br>
           <html:submit value="Login" />
       </html:form>
   </body>
</html>