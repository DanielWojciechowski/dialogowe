<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><?xml version="1.0" encoding="UTF-8"?>
<vxml version="2.1">

	<form id="changeReservationDate">
		<block>
			<var name="dictData" expr="'<c:forEach items="${dictData}" var="dictElem">${dictElem}, </c:forEach>'"/>
			<return namelist="dictData" />
		</block>
	</form>

</vxml>
