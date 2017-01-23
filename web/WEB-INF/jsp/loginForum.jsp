<%-- 
    Document   : loginForum
    Created on : 08-may-2016, 19:23:50
    Author     : shahadbawi
--%>

<form name = "loginForum" action="check.htm" action = "POST">
    <label >
        <spring:message code="etiq.txtuser"/>
    </label>
   
    <input type="text" name="txtuser"/>
    <label >
        <spring:message code="etiq.txtpassword"/>
    </label>
    
    <input type="text" name="txtpassword"/>
    <input name="submit"  value='<spring:message code="etiq.txtlogin"/>' type="submit"> 
</form>


