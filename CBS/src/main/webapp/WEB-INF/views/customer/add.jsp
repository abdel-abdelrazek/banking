<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ include file="/include/header.jsp"%>
<form:form method="post" modelAttribute="customer">
	<%-- <form  method="post" role="form"> --%>
	<form:errors path="*" cssClass="alert alert-danger" element="div" />

	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">Customer Info</h3>
		</div>
		<div class="panel-body">
			<div class="form-group">
				<label for="SSN">SSN:</label> <input class="form-control" id="SSN"
					name="SSN" placeholder="Please Enter ssn:" />

			</div>

			<div class="form-group">
				<label for="firstName">firstName:</label> <input type="text"
					class="form-control" id="firstName" name="firstName"
					placeholder="Please Enter firstName" />


			</div>
			<div class="form-group">
				<label for="lastName">lastName:</label> <input type="text"
					class="form-control" id="lastName" name="lastName"
					placeholder="Please Enter lastName" />


			</div>

			<div class="form-group">
				<label for="email">Email:</label> <input type="text"
					class="form-control" id="email" name="email"
					placeholder="Please Enter email" />
			</div>

		</div>

	</div>


	<div class="form-group">
		<button type="submit" class="btn btn-sm btn-success">Submit</button>
	</div>

</form:form>



<%@ include file="/include/footer.jsp"%>