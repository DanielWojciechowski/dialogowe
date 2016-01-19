<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><?xml version= "1.0" encoding="UTF-8" ?>
<grammar xml:lang="en-us" root="SEASON">

	<rule id="SEASON" scope="public">
		<one-of>
			<item>
				<item repeat="0-1">
					<ruleref uri="#FILL_SEASON"/>
				</item>
				<one-of>
					<item> yes					<tag> out.season = "Yes";	</tag></item>
					<item> in the season		<tag> out.season = "Yes";	</tag></item>
					<item> no					<tag> out.season = "No";	</tag></item>
					<item> not in the season	<tag> out.season = "No";	</tag></item>
				</one-of>
			</item>
		</one-of>
	</rule>

	<rule id="FILL_SEASON" scope="public">
		<one-of>
			<item> and it will be </item>
		</one-of>
	</rule>
</grammar>