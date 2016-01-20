<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><?xml version= "1.0" encoding="UTF-8" ?>
<grammar xml:lang="en-us" root="SERVICETYPE_GRAM">
    <rule id="SERVICETYPE_GRAM" scope="public">
        <item>
            <item repeat="0-1">
                <ruleref uri="#FILL_SERVICE_MENU"/>
            </item>
            <one-of><c:forEach items="${services}" var="service"><item>${fn:toLowerCase(service.name)}</item></c:forEach></one-of>
        </item>

    </rule>
    <rule id="FILL_SERVICE_MENU" scope="public">
        <one-of>
            <item> service </item>
        </one-of>
    </rule>
</grammar>