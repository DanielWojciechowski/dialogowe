<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><?xml version= "1.0" encoding="UTF-8" ?>
<grammar xml:lang="en-us" root="SERVICETYPE_GRAM">
    <rule id="SERVICETYPE_GRAM" scope="public">
        <one-of><c:forEach items="${services}" var="service"><item>${fn:toLowerCase(service.name)}</item></c:forEach></one-of>
    </rule>
</grammar>