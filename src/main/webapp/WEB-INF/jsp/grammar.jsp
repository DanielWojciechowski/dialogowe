<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
           
           
<grammar xml:lang="en-us" root="meh">
	<rule id="meh" scope="public">
    	<one-of>
    		<c:forEach items="${names}" var="name">
    			<item>${name}</item>
    		</c:forEach>
        </one-of>
    </rule>
</grammar>