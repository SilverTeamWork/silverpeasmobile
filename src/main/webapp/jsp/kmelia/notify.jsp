<%@ page isELIgnored="false"%>

<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sm"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="../parameters.jsp"%>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="com.oosphere.silverpeasmobile.multilang.silverpeasmobile"/>
<c:set var="zeBackLabel" value="${(empty backLabel) ? 'Notify' : backLabel}"/>

<html>
<head>
	<title><fmt:message key="pageTitle"/></title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.mobile-1.0b1.min.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquerymobileoverride.css">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name = "format-detection" content = "telephone=no">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mobile-1.0b1.min.js"></script>
	<script type='text/javascript'>
        $(document).ready(function() {
            $('#message').keyup(function() {
                var len = this.value.length;
                if (len >= 2000) {
                    this.value = this.value.substring(0, 2000);
                }
                $('#charLeft').text(2000 - len);
            });
        });
    </script>
</head>

<body>
<div  data-role="page" >

	<form name="formHome" action="${pageContext.request.contextPath}/index.html" method="post">
		<input type="hidden" name="action" value="login"/>
		<input type="hidden" name="subAction" value="home"/>
		<input type="hidden" name="userId" value="${userId}"/>
	</form>
	<div  data-role="header" data-position="fixed">
		<a href="#" data-icon="back" data-rel="back">Back</a>
		<h1>${publication.name}</h1>
		<a href="javascript:document.forms['formHome'].submit()" data-icon="home" >Home</a>
	</div>
	
	<div  data-role="content">
	
		<script type="text/javascript">
			function goTo(subAction){
				var form = document.forms['form'];
				form.subAction.value = subAction;
				form.submit();
			}
		</script>
		<form name="form" action="${pageContext.request.contextPath}/index.html" method="post">
			<input type="hidden" name="action" value="kmelia"/>
			<input type="hidden" name="subAction" value="doNotify"/>
			<input type="hidden" name="userId" value="${userId}"/>
			<input type="hidden" name="spaceId" value="${spaceId}"/>
			<input type="hidden" name="componentId" value="${componentId}"/>
			<input type="hidden" name="attachmentId" value="${attachmentId}"/>
			<input type="hidden" name="publicationId" value="${publicationId}"/>
			
			<div data-role="fieldcontain">
				<label for="message">Message</label>
				<textarea cols="40" rows="8" name="message" id="message" style="width: 98%"></textarea>
				<span style="font-size:smaller;">&nbsp;<span id="charLeft">2000</span>  Characters left</span>
			</div>
			
			
			<div data-role="fieldcontain">
				<fieldset data-role="controlgroup">
				<legend>Choose recipients to notify</legend>
				<c:forEach items="${listComponentUsers}" var="user">
							<input id="user-${user.id}" name="recipient-${user.id}" type="checkbox" value="${user.id}" class="custom">
							<label for="user-${user.id}">${user.lastName} ${user.firstName}</label>
				</c:forEach>
				</fieldset>
			</div>
			
			<input type="submit" value="Notify Recipients" data-theme="a">  
			
		</form>
	</div>
	
	<div data-role="footer" data-position="fixed">
		Copyright Silverpeas 1999-2011
	</div>

</body>


</html>