<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><?xml version= "1.0" encoding="UTF-8" ?>
<grammar xml:lang="en-us" root="FIRSTNAME_GRAM">
    <rule id="FIRSTNAME_GRAM" scope="public">
        <one-of><c:forEach items="${clients}" var="client"><item>${fn:toLowerCase(client.firstName)}</item></c:forEach></one-of>
    </rule>
</grammar>