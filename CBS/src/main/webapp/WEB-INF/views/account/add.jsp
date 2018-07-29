<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ include file="/include/header.jsp"%>

<form:form method="post" modelAttribute="account">
<form:errors path="*" cssClass="alert alert-danger" element="div" />
	<input type="hidden" name="customer.id" value="${customerId}"></input>

	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">Account Info</h3>
		</div>
		<div class="panel-body">

			<div class="form-group">
				<label for="nickName">Nick Name:</label>
				<input type="text"
					class="form-control" id="nickName" name="nickName"
					placeholder="Please Enter NickName" />
					
				
			</div>

			<div class="form-group">
				<label for="uaccountType">Account Type:</label> <select
					class="form-control" id="accountType" name="accountType">
					<option value="1">Saving Account</option>
					<option value="2">Checking Account</option>
				</select>
			</div>


			<div class="form-group">
				<label for="initalBalance">Initial Balance:</label>
				<input class="form-control" id="balance" type="number"
					name="balance" ></input>
			</div>
		</div>

</div>
		<div class="form-group">
			<button type="submit" class="btn btn-sm btn-success">Submit</button>
		</div>
</form:form>



<%@ include file="/include/footer.jsp"%>