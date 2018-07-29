<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/include/header.jsp"%>
<h1>Customer Info</h1>
<c:if test="${alertStatus eq true }" >
<div class="alert alert-success alert-dismissable" " id="alertInfo">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
        <strong>Success!</strong> <span id="alertInfoDetail">${alertInfo}</span>
 </div>
 </c:if>
 
 
 <input type="hidden" id="customerId" value="${cust.id}" ></input>
<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Customer Info</h3>
	</div>
	<div class="panel-body">
		<div class="form-group">
			<label for="SSN">SSN:</label>
			<label for="SSN">${cust.SSN}</label>
<!-- 			 <input type="number" class="form-control" -->
<%-- 				id="ssn" name="ssn" placeholder="Enter ssn:" value="${cust.SSN}" /> --%>
		</div>

		<div class="form-group">
			<label for="firstName">firstName:</label>
			<label for="firstName">${cust.firstName }</label>
			
<!-- 			<textarea class="form-control" id="firstName" name="firstName" -->
<%-- 				rows="3" placeholder="Please Input firstName">${cust.firstName }</textarea> --%>
		</div>
		<div class="form-group">
			<label for="lastName">lastName:</label>
			<label for="lastName">${cust.lastName }</label>
<!-- 			<textarea class="form-control" id="lastName" name="lastName" rows="3" -->
<%-- 				placeholder="Please Input lastName">${cust.lastName }</textarea> --%>
		</div>
		<%-- <div class="form-group">
			<label for="lastName">Status:</label>
			<label for="lastName">${cust.status }</label>
<!-- 			<textarea class="form-control" id="lastName" name="lastName" rows="3" -->
				placeholder="Please Input lastName">${cust.status }</textarea>
		</div> --%>
	</div>

</div>

<h1>Account List</h1>




<div class="row">
                        <div class="col-md-12">
                            <!-- BEGIN EXAMPLE TABLE PORTLET-->
                            <div class="portlet light ">
                                <div class="portlet-title">
                                    <div class="caption font-dark">
                                        <i class="icon-settings font-dark"></i>
                                        <span class="caption-subject bold uppercase"> Managed Table</span>
                                    </div>
                                    <div class="actions">
                                        <div class="btn-group btn-group-devided" data-toggle="buttons">
                                            <label class="btn btn-transparent dark btn-outline btn-circle btn-sm active">
                                                <input type="radio" name="options" class="toggle" id="option1">Actions</label>
                                            <label class="btn btn-transparent dark btn-outline btn-circle btn-sm">
                                                <input type="radio" name="options" class="toggle" id="option2">Settings</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <div class="table-toolbar">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="btn-group">
                                                    <!-- <button id="sample_editable_1_new" class="btn sbold green" onclick="add()"> Add New
                                                        <i class="fa fa-plus"></i>
                                                    </button> -->
                                                </div>
                                            </div> 
                                            <div class="col-md-6">
                                                <div class="btn-group pull-right">
                                                    <button class="btn green  btn-outline dropdown-toggle" data-toggle="dropdown">Tools
                                                        <i class="fa fa-angle-down"></i>
                                                    </button>
                                                    <ul class="dropdown-menu pull-right">
                                                        <li>
                                                            <a href="javascript:;">
                                                                <i class="fa fa-print"></i> Print </a>
                                                        </li>
                                                        <li>
                                                            <a href="javascript:;">
                                                                <i class="fa fa-file-pdf-o"></i> Save as PDF </a>
                                                        </li>
                                                        <li>
                                                            <a href="javascript:;">
                                                                <i class="fa fa-file-excel-o"></i> Export to Excel </a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_1">
                                        <thead>
                                            <tr>
                                              
                                                <th> NickName </th>
                                                <th> AccountNumber </th>
                                                  <th> Balance($)</th>
                                              
                                               <!--  <th> Account Type </th> -->
                                                <th> Actions </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${cust.accounts}" var="list">
                                            <tr class="odd gradeX">
                                              
                                                <td> ${list.nickName} </td>
                                                  <td> ${list.accountNumber} </td>
                                                  <td> ${list.balance} </td>
                                                  
                                              <%--   <td>
                                                    <span class="label label-sm label-success">${list.getClass().getSimpleName()} </span>
                                                </td>
                                              --%>
                                                <td>
                                                    <div class="btn-group">
                                                        <button class="btn btn-xs green dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false"> Actions
                                                            <i class="fa fa-angle-down"></i>
                                                        </button>
                                                        <ul class="dropdown-menu" role="menu">
                                                            <li>
                                                                <a href="<%=path%>/account/deposit?accountId=${list.id}">
                                                                    <i class="icon-docs"></i> Deposit </a>
                                                            </li>
                                                            
                                                            <li>
                                                                <a href="<%=path%>/account/withdraw?accountId=${list.id}">
                                                                    <i class="icon-docs"></i> Withdraw </a>
                                                            </li>
                                                            
                                                           <%--   <li>
                                                                <a href="<%=path%>/account/transfer?accountId=${list.id}">
                                                                    <i class="icon-docs"></i> Transfer </a>
                                                            </li>
                                                             --%>
                                                            <li><a href="<%=path%>/bill/pay?accountId=${list.id}">
																<i class="icon-flag"></i> Pay Bill
														</a></li>
                                                            
                                                           
                                                           <%--  <li>
                                                                <a href="<%=path%>/">
                                                                    <i class="icon-flag"></i> List Accounts
                                                                     <span class="badge badge-success">Deposit</span> 
                                                                </a>
                                                            </li> --%>
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
    
    <script>
function add(){
	var id=$("#customerId").val();
	window.location.href="<%=path%>/customer/addAccount?customerId="+id;
}
   

</script>  

<%@ include file="/include/footer.jsp"%>
