<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ include file="/include/header.jsp"%>

 <div class="note note-danger" id="alertInfo" style="display:none">
     <h4 class="block" id="alertMsg"></h4>
 </div>
 
<form action="<%=path%>/account/depositP" method="post" role="form" id="moneyfrom" >
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
				<label for="initalBalance">Balance:</label>
<%-- 				<label for="initalBalance">${account.balance}</label> --%>
 				<input class="form-control" disabled id="balance" value="${account.balance}"></input> 
			</div>
			
			<div class="form-group">
				<label for="initalBalance">Beneficiary Account Number:</label>
				<input id="beneficiaryAccountNumber"  type="number" name="beneficiaryAccountNumber" autocomplete="off" class="form-control" onkeyup="findUser()" ></input>
			</div>
			
			<div class="form-group" id="beneficiaryAccountInfo" style="display:none" >
				<label for="initalBalance">Beneficiary Account Name:</label>
<!-- 				<label   id="beneficiaryAccountName"></label> -->
				
	<input id="beneficiaryAccountName"  type="text" autocomplete="off" name="beneficiaryAccountName" class="form-control" ></input> 
			</div>
			
			<div class="form-group">
				<label for="initalBalance">Transfer Amount:</label>
				<input id="amount"  name="amount" type="number" class="form-control" value=""></input>
			</div>
		</div>

		<div class="form-group">
			<button type="button" class="btn btn-sm btn-success" onclick="transfer()" >Transfer</button>
		</div>
</form>

<script>

function findUser(){
	var beneficiaryAccountNumber  = $("#beneficiaryAccountNumber").val();
	if(null==beneficiaryAccountNumber||""==beneficiaryAccountNumber){
		return ;
	}
	$.ajax({
		url : "<%=path%>/account/findAccountByNum",
		data : {"accountNum":beneficiaryAccountNumber},
		type : "POST",
		dataType : "JSON",
		success : function(result) {
			if (result.status) {
				$("#beneficiaryAccountName").val(result.nickName);
				$("#beneficiaryAccountInfo").show();
			} else {
				$("#beneficiaryAccountName").val("");
				$("#beneficiaryAccountInfo").hide();
			/* 	$("#msg").html("<font color='red' >"+"login fail"+"</font>"); */
			}
		},
		error : function(){
			alert("error");	
		}
	}); 
}
function transfer(){
	var balance = Number($("#balance").val());
	var amount = Number($("#amount").val());
	var accountCustomerId = $('#accountCustomerId').val();
	var beneficiaryAccountNumber = $("#beneficiaryAccountNumber").val();
	var beneficiaryAccountName = $("#beneficiaryAccountName").val();
	if(null==beneficiaryAccountNumber||""==beneficiaryAccountNumber){
		$("#alertMsg").html("Please enter the Account Number!");
		$("#alertInfo").show();
		return ;
	}else if(null==beneficiaryAccountName||""==beneficiaryAccountName){
		$("#alertMsg").html("Account doesn't exists!");
		$("#alertInfo").show();
		return ;
	}else if(null==amount||""==amount){
	
		$("#alertMsg").html("Please enter the transfer money!");
		$("#alertInfo").show();
		return ;
	}else if(balance<amount){
		$("#alertMsg").html("Account doesn't have enough money!");
		$("#alertInfo").show();
		return ;
	}
	
	$.ajax({
		url : "<%=path%>/account/transferP",
		data : {"amount":amount,"accountId":$("#accountId").val(),"beneficiaryAccountNumber":$("#beneficiaryAccountNumber").val()},
		type : "POST",
		dataType : "JSON",
		success : function(result) {
			if (result.status) {
				$("#balance").val(balance-amount);
				/* alert("Transfer Successful"); */
			    window.location.href="<%=path%>/account/index?customerId="+accountCustomerId+"&alertStatus=true&alertInfo=Customer transfers Money Successfully"; 
			} else {
				alert("Transfer fail");
			}
		},
		error : function(){
			alert("error");	
		}
	}); 
}

</script>

<%@ include file="/include/footer.jsp"%>