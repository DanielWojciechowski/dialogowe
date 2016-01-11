<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><?xml version= "1.0" encoding="UTF-8" ?>
<grammar xml:lang="en-us" root="LASTNAME">

	<rule id="LASTNAME" scope="public">
		<one-of>
			<item>
				<item repeat="0-1">
					<ruleref uri="#FILL_LASTNAME"/>
				</item>
				<one-of><c:forEach items="${clients}" var="client"><item> ${fn:toLowerCase(client.lastName)} <tag> out.lastName = "${fn:toLowerCase(client.lastName)}"; </tag></item></c:forEach>
				</one-of>
			</item>
		</one-of>
	</rule>

	<rule id="FILL_LASTNAME" scope="public">
		<one-of>
			<item> last name is </item>
			<item> my last name is </item>
		</one-of>
	</rule>
</grammar>