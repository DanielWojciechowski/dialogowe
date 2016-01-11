<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><?xml version= "1.0" encoding="UTF-8" ?>

<grammar xml:lang="en-US" root="MIXEDNAMES">

  <rule id="MIXEDNAMES" scope="public">

    <one-of>
      <item>
        <item><ruleref uri="#FIRSTNAME"/><tag>out.firstName=rules.FIRSTNAME.firstName;</tag></item>
        <item><ruleref uri="#LASTNAME"/><tag>out.lastName=rules.LASTNAME.lastName</tag></item>
      </item>
      <item>
        <item><ruleref uri="#LASTNAME"/><tag>out.lastName=rules.LASTNAME.lastName</tag></item>
        <item><ruleref uri="#FIRSTNAME"/><tag>out.firstName=rules.FIRSTNAME.firstName;</tag></item>
      </item>


      <item><ruleref uri="#FIRSTNAME"/><tag>out.firstName=rules.FIRSTNAME.firstName;</tag></item>

      <item><ruleref uri="#LASTNAME"/><tag>out.lastName=rules.LASTNAME.lastName</tag></item>


    </one-of>
  </rule>

  <rule id="FIRSTNAME" scope="public">
    <one-of>
      <item>
        <item repeat="0-1">
          <ruleref uri="#FILL_FIRSTNAME"/>
        </item>
        <one-of><c:forEach items="${clients}" var="client"><item>${fn:toLowerCase(client.firstName)}<tag>out.firstName="${fn:toLowerCase(client.firstName)}";</tag></item></c:forEach></one-of>
      </item>
    </one-of>
  </rule>

  <rule id="LASTNAME" scope="public">
    <one-of>
      <item>
        <item repeat="0-1">
          <ruleref uri="#FILL_LASTNAME"/>
        </item>
        <one-of><c:forEach items="${clients}" var="client"><item>${fn:toLowerCase(client.lastName)}<tag>out.lastName="${fn:toLowerCase(client.lastName)}";</tag></item></c:forEach></one-of>
      </item>
    </one-of>
  </rule>

  <rule id="FILL_FIRSTNAME" scope="public">
    <one-of>
      <item> my name is </item>
      <item> my first name is </item>
      <item> i am </item>
    </one-of>
  </rule>

  <rule id="FILL_LASTNAME" scope="public">
    <one-of>
      <item> last name is </item>
      <item> my last name is </item>
    </one-of>
  </rule>

</grammar>