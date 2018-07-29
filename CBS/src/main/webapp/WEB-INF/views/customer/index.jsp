<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

 <%@ include file="/include/header.jsp"%>


<h1>Customer List</h1>
<input type="hidden" id="status" value="${status}"></input>
<input type="hidden" id="accStatus" value="${accStatus}"></input>

<div class="alert alert-success alert-dismissable" style="display: none"
	id="alertInfo">
	<button type="button" class="close" data-dismiss="alert"
		aria-hidden="true"></button>
	<strong>Success!</strong> Operation Completed Successfully

</div>

<div class="alert alert-danger alert-dismissable" style="display: none"
	id="alertInfo2">
	<button type="button" class="close" data-dismiss="alert"
		aria-hidden="true"></button>
	<strong>Failure!</strong> Operation Failed!

</div>

<div class="alert alert-success alert-dismissable" style="display: none"
	id="alertInfoAccount">
	<button type="button" class="close" data-dismiss="alert"
		aria-hidden="true"></button>
	<strong>Success!</strong> Account Added Successfully

</div>

<security:authorize access="isAuthenticated()">
Welcome <div style="color:red"> <security:authentication property="principal.username" /></div>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet light ">
			<div class="portlet-title">
				<div class="caption font-dark">
					<i class="icon-settings font-dark"></i> <span
						class="caption-subject bold uppercase"> Managed Table</span>
				</div>
				<div class="actions">
					<div class="btn-group btn-group-devided" data-toggle="buttons">
						<label
							class="btn btn-transparent dark btn-outline btn-circle btn-sm active">
							<input type="radio" name="options" class="toggle" id="option1">Actions
						</label> <label
							class="btn btn-transparent dark btn-outline btn-circle btn-sm">
							<input type="radio" name="options" class="toggle" id="option2">Settings
						</label>
					</div>
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<div class="row">
						<div class="col-md-6">
							<div class="btn-group">
								<button id="sample_editable_1_new" class="btn sbold green"
									onclick="add()">
									Add New <i class="fa fa-plus"></i>
								</button>
							</div>
						</div>
						<div class="col-md-6">
							<div class="btn-group pull-right">
								<button class="btn green  btn-outline dropdown-toggle"
									data-toggle="dropdown">
									Tools <i class="fa fa-angle-down"></i>
								</button>
								<ul class="dropdown-menu pull-right">
									<li><a href="javascript:;"> <i class="fa fa-print"></i>
											Print
									</a></li>
									<li><a href="javascript:;"> <i
											class="fa fa-file-pdf-o"></i> Save as PDF
									</a></li>
									<li><a href="javascript:;"> <i
											class="fa fa-file-excel-o"></i> Export to Excel
									</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<table
					class="table table-striped table-bordered table-hover table-checkable order-column"
					id="sample_1">
					<thead>
						<tr>

							<th>SSN</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Email</th>
							<!-- <th>Status</th> -->
							<th>Actions</th>
						</tr>
					</thead>
					
					
					
					<tbody>
						<c:forEach items="${customerList}" var="list">
							<tr class="odd gradeX">

								<td>${list.SSN}</td>
								<td>${list.firstName}</td>
								<td>${list.lastName}</td>
								<td>${list.email}</td>
								<%-- <td><span class="label label-sm label-success">
										${list.status} </span></td> --%>

								<td>
									<div class="btn-group">
										<button class="btn btn-xs green dropdown-toggle" type="button"
											data-toggle="dropdown" aria-expanded="false">
											Actions <i class="fa fa-angle-down"></i>
										</button>
										<ul class="dropdown-menu" role="menu">
											<li><a
												href="<%=path%>/account/add?customerId=${list.id}">
													<i class="icon-docs"></i> Add Account
											</a></li>



											<li><a
												href="<%=path%>/account/index?customerId=${list.id}"> <i
													class="icon-flag"></i> List Accounts <span
													class="badge badge-success">${list.accounts.size()}</span>
											</a></li>
											
										</ul>
									</div>
								</td>
							</tr>
						</c:forEach>

					</tbody>
					
				</table>
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>
</security:authorize>
<script>

	function add(){
		
		window.location.href="<%=path%>/customer/add";

	}

	$(document).ready(function() {
		var status = $("#status").val();
		if (status == '1') {
			$("#alertInfo").show();
			$("#alertInfo2").hide();
		} else if (status=='0'){
			$("#alertInfo2").show();
			$("#alertInfo").hide();
		
		} else {
			$("#alertInfo").hide();
			$("#alertInfo2").hide();
			
		}

		var status2 = $("#accStatus").val();
		if (status2 == '1') {
			$("#alertInfoAccount").show();
		} else {
			$("#alertInfoAccount").hide();
		}
	})
</script>

<%@ include file="/include/footer.jsp"%> 
