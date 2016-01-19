<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><?xml version= "1.0" encoding="UTF-8" ?>
<grammar xmlns="http://www.w3.org/2001/06/grammar" xml:lang="en-US" root="CHECKER">
  <rule id="CHECKER" scope="public">
    <one-of>
      <item>
        <item><ruleref uri="#TYPE"/><tag>out.room_type=rules.TYPE.room_type;</tag></item>
        <item><ruleref uri="#LENGTH"/><tag>out.stay_length=rules.LENGTH.stay_length</tag></item>
        <item><ruleref uri="#SEASON"/><tag>out.season=rules.SEASON.season</tag></item>
      </item>
      <item>
        <item><ruleref uri="#TYPE"/><tag>out.room_type=rules.TYPE.room_type;</tag></item>
        <item><ruleref uri="#SEASON"/><tag>out.season=rules.SEASON.season</tag></item>
        <item><ruleref uri="#LENGTH"/><tag>out.stay_length=rules.LENGTH.stay_length</tag></item>
      </item>
      <item>
        <item><ruleref uri="#LENGTH"/><tag>out.stay_length=rules.LENGTH.stay_length</tag></item>
        <item><ruleref uri="#TYPE"/><tag>out.room_type=rules.TYPE.room_type;</tag></item>
        <item><ruleref uri="#SEASON"/><tag>out.season=rules.SEASON.season</tag></item>
      </item>
      <item>
        <item><ruleref uri="#LENGTH"/><tag>out.stay_length=rules.LENGTH.stay_length</tag></item>
        <item><ruleref uri="#SEASON"/><tag>out.season=rules.SEASON.season</tag></item>
        <item><ruleref uri="#TYPE"/><tag>out.room_type=rules.TYPE.room_type;</tag></item>
      </item>
      <item>
        <item><ruleref uri="#SEASON"/><tag>out.season=rules.SEASON.season</tag></item>
        <item><ruleref uri="#TYPE"/><tag>out.room_type=rules.TYPE.room_type;</tag></item>
        <item><ruleref uri="#LENGTH"/><tag>out.stay_length=rules.LENGTH.stay_length</tag></item>
      </item>
      <item>
        <item><ruleref uri="#SEASON"/><tag>out.season=rules.SEASON.season</tag></item>
        <item><ruleref uri="#LENGTH"/><tag>out.stay_length=rules.LENGTH.stay_length</tag></item>
        <item><ruleref uri="#TYPE"/><tag>out.room_type=rules.TYPE.room_type;</tag></item>
      </item>
      <item>
        <item><ruleref uri="#SEASON"/><tag>out.season=rules.SEASON.season</tag></item>
        <item><ruleref uri="#LENGTH"/><tag>out.stay_length=rules.LENGTH.stay_length</tag></item>
      </item>
      <item>
        <item><ruleref uri="#LENGTH"/><tag>out.stay_length=rules.LENGTH.stay_length</tag></item>
        <item><ruleref uri="#SEASON"/><tag>out.season=rules.SEASON.season</tag></item>
      </item>
      <item>
        <item><ruleref uri="#SEASON"/><tag>out.season=rules.SEASON.season</tag></item>
        <item><ruleref uri="#TYPE"/><tag>out.room_type=rules.TYPE.room_type;</tag></item>
      </item>
      <item>
        <item><ruleref uri="#TYPE"/><tag>out.room_type=rules.TYPE.room_type;</tag></item>
        <item><ruleref uri="#SEASON"/><tag>out.season=rules.SEASON.season</tag></item>
      </item>
      <item>
        <item><ruleref uri="#LENGTH"/><tag>out.stay_length=rules.LENGTH.stay_length</tag></item>
        <item><ruleref uri="#TYPE"/><tag>out.room_type=rules.TYPE.room_type;</tag></item>
      </item>
      <item>
        <item><ruleref uri="#TYPE"/><tag>out.room_type=rules.TYPE.room_type;</tag></item>
        <item><ruleref uri="#LENGTH"/><tag>out.stay_length=rules.LENGTH.stay_length</tag></item>
      </item>
      <item>
        <item><ruleref uri="#TYPE"/><tag>out.room_type=rules.TYPE.room_type;</tag></item>
      </item>
      <item>
        <item><ruleref uri="#SEASON"/><tag>out.season=rules.SEASON.season</tag></item>
      </item>
      <item>
        <item><ruleref uri="#LENGTH"/><tag>out.stay_length=rules.LENGTH.stay_length</tag></item>
      </item>
    </one-of>
  </rule>

  <rule id="TYPE" scope="public">
    <one-of>
      <item>
        <item repeat="0-1">
          <ruleref uri="#FILL_TYPE"/>
        </item>
        <one-of><c:forEach items="${roomTypes}" var="roomType"><item>${roomType}<tag> out.room_type = "${roomType}";	</tag></item></c:forEach></one-of>
      </item>
    </one-of>
  </rule>

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

  <rule id="FILL_TYPE" scope="public">
    <one-of>
      <item> room type is </item>
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

  <rule id="FILL_SEASON" scope="public">
    <one-of>
      <item> and it will be </item>
    </one-of>
  </rule>

</grammar>