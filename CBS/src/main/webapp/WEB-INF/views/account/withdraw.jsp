<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ include file="/include/header.jsp"%>

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
				<label for="initalBalance">balance:</label>
				<label for="initalBalance">${account.balance}</label>
<%-- 				<input class="form-control" disable="true" id="balance" value="${account.balance}"></input> --%>
			</div>
			
			<div class="form-group">
				<label for="initalBalance">Withdraw Amount:</label>
				<input id="amount" type="number" name="amount" class="form-control" value=""></input>
			</div>
		</div>

		<div class="form-group">
			<button type="button" class="btn btn-sm btn-success" onclick="withdraw()" >Withdraw</button>
		</div>
</form:form>

<script>
function withdraw(){
	var amount = Number($("#amount").val());
	var balance = Number($("#balance").val());
	var accountCustomerId = $('#accountCustomerId').val();
	if(null==amount||""==amount){
		alert("Please enter the amount!");
		return ;
	}else if(balance<amount){
		alert("You don't have enough money!");
		return ;
	}
	
	$.ajax({
		url : "<%=path%>/account/withdraw",
		data : {"amount":amount,"accountId":$("#accountId").val()},
		type : "POST",
		dataType : "JSON",
		success : function(result) {
			if (result.status) {
				$("#balance").val(balance-amount);
				/* alert("Withdraw Successful"); */
			    window.location.href="<%=path%>/account/index?customerId="+accountCustomerId+"&alertStatus=true&alertInfo=Customer withdraws Money Successfully"; 
				
			} else {
				alert("Insufficient Fund");
			/* 	$("#msg").html("<font color='red' >"+"login fail"+"</font>"); */
			}
		},
		error : function(){
			alert("error");	
		}
	}); 
}

</script>

<%@ include file="/include/footer.jsp"%>