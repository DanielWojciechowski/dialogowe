<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
           
           
<grammar xml:lang="en-us" root="meh">
	<rule id="meh" scope="public">
    	<one-of>
    		<c:forEach items="${roomTypes}" var="roomType">
    			<item>${roomType}</item>
    		</c:forEach>
        </one-of>
    </rule>
</grammar>