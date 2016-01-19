<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><?xml version= "1.0" encoding="UTF-8" ?>
<grammar xml:lang="en-us" root="LENGTH">

	<rule id="LENGTH" scope="public">
		<one-of>
			<item>

				<item repeat="0-1">
					<ruleref uri="#FILL_LEN"/>
				</item>
				<one-of>
					<item> 1	<tag> out.stay_length = "1";	</tag></item>
					<item> 2	<tag> out.stay_length = "2";	</tag></item>
					<item> 3	<tag> out.stay_length = "3";	</tag></item>
					<item> 4	<tag> out.stay_length = "4";	</tag></item>
					<item> 5	<tag> out.stay_length = "5";	</tag></item>
					<item> 6	<tag> out.stay_length = "6";	</tag></item>
					<item> 1 week <tag> out.stay_length = "7";	</tag></item>
					<item> 2 weeks <tag> out.stay_length = "14";	</tag></item>
					<item> 3 weeks <tag> out.stay_length = "21";	</tag></item>
					<item> 4 weeks <tag> out.stay_length = "28";	</tag></item>
					<item> 1 month <tag> out.stay_length = "28";	</tag></item>
				</one-of>
				<item repeat="0-1">
					<ruleref uri="#FILL_LEN_END"/>
				</item>
			</item>
		</one-of>
	</rule>

	<rule id="FILL_LEN" scope="public">
		<one-of>
			<item> the length of the stay is </item>
		</one-of>
	</rule>

	<rule id="FILL_LEN_END" scope="public">
		<one-of>
			<item> day </item>
			<item> days </item>
		</one-of>
	</rule>
</grammar>