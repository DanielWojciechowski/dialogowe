<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><?xml version= "1.0" encoding="UTF-8" ?>
<grammar xml:lang="en-us" root="TYPE">
	<rule id="TYPE" scope="public">
		<one-of>
			<item>
				<item repeat="0-1">
					<ruleref uri="#FILL_TYPE"/>
				</item>
				<one-of><c:forEach items="${roomTypes}" var="roomType"><item>${roomType}</item></c:forEach></one-of>
			</item>
		</one-of>
	</rule>
	<rule id="FILL_TYPE" scope="public">
		<one-of>
			<item> room type is </item>
		</one-of>
	</rule>
</grammar>