<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ include file="/include/header.jsp"%>
 <div class="note note-danger" id="alertInfo" style="display:none">
     <h4 class="block" id="alertMsg"></h4>
 </div>
<form:form method="post" modelAttribute="account">
	<input type="hidden" value="${account.id}" id="accountId"></input>
	<input type="hidden" value="${account.customer.id}" id="accountCustomerId"></input>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">Customer Info</h3>
		</div>
		<div class="panel-body">

			<div class="form-group">
				<label for="nickName">Customer Name:</label>
				<label for="nickName">${account.customer.firstName}  ${account.customer.lastName}</label>
				
<%-- 				<input class="form-control" disable="true" value="${account.customer.firstName}  ${account.customer.lastName}"></input> --%>
			</div>

			<div class="form-group">
				<label for="initalBalance">Account Number:</label>
				<label for="initalBalance">${account.accountNumber}</label>
<%-- 				<input class="form-control" disable="true" value="${account.accountNumber}"></input> --%>
			</div>
			

			<div class="form-group">
				<label for="initalBalance">Current Balance:</label>
				<label for="initalBalance">${account.balance}</label>
<%-- 				<input class="form-control" disable="true" id="balance" value="${account.balance}"></input> --%>
			</div>
			
			<div class="form-group">
				<label for="initalBalance">Amount To Deposit:</label>
				<input id="amount"  name="amount" class="form-control" type="number" value=""></input>
			</div>
			
		</div>

		<div class="form-group">
			<button type="button" class="btn btn-sm btn-success" onclick="deposit()" >Deposit</button>
		</div>
</form:form>

<script>
function deposit(){
	var amount = Number($("#amount").val());
	var accountCustomerId = $('#accountCustomerId').val();
	if(null==amount||""==amount){
		$("#alertMsg").html("Please enter the amount!");
		$("#alertInfo").show();
		return ;
	}
	var balance = Number($("#balance").val());
	$.ajax({
		url : "<%=path%>/account/deposit",
		data : {"amount":amount,"accountId":$("#accountId").val()},
		type : "POST",
		dataType : "JSON",
		success : function(result) {
			if (result.status) {
				$("#balance").val(balance+amount);
				 window.location.href="<%=path%>/account/index?customerId="+accountCustomerId+"&alertStatus=true&alertInfo=Customer deposits Money Successfully"; 
			} else {
				alert("Deposit fail");
			/* 	$("#msg").html("<font color='red' >"+"login fail"+"</font>"); */
			}
		},
		error : function(e){
			alert(e.message);	
		}
	}); 
}

</script>

<%@ include file="/include/footer.jsp"%>