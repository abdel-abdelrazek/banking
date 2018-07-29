package edu.mum.cbs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.mum.cbs.domain.Account;
import edu.mum.cbs.domain.Customer;
import edu.mum.cbs.servie.IAccountService;
import edu.mum.cbs.servie.ICustomerService;


@Controller 
@RequestMapping ("/customer")
public class CustomerController {

	@Autowired
	ICustomerService customerService;

	@Autowired
	IAccountService accountService;

/*	@Autowireds
	BillService billService;*/

	// @Autowired
	// AccountRepository accountRep;

	//@Autowired
	//TransationRepository transationRepository;

	@RequestMapping(value = "/Exp", method = RequestMethod.GET)
	public String Exp(ModelMap modelMap) {
		
		int x=1/0;
		//throw new RuntimeException("");
		return "/customer/index";
		
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap modelMap, String status, String accStatus) {
		List<Customer> customerList = (List<Customer>) customerService.getAllCustomers();

		modelMap.addAttribute("customerList", customerList);
		modelMap.addAttribute("status", status);
		modelMap.addAttribute("accStatus", accStatus);

		return "/customer/index";

	}

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addCustomer(@ModelAttribute Customer customer) {
		return "/customer/add";
	}

	//
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addCustomerPost(@Valid @ModelAttribute Customer customer,  BindingResult result) {

		System.out.println("#######" +customer.getFirstName());
		if (result.hasErrors()) {
			System.out.println(result.toString());
			
			return "/customer/add";
		}
		customerService.addCustomer(customer);

		return "redirect:index?status=1";
	}

	
	

	

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/account/transfer", method = RequestMethod.GET)
	public String accountTransfer(ModelMap modelMap, int accountId) {

		Account account = accountService.findAccount(accountId);

		modelMap.addAttribute("account", account);

		return "/account/transfer";

	}

	



	/*@RequestMapping(value = "/account/transferP", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> accountTransferP(ModelMap modelMap, double amount, int accountId,
			int beneficiaryAccountNumber) {

		Map<String, Object> map = this.initMapStatus();

		try {

			accountService.transferMoney(amount, accountId, beneficiaryAccountNumber);

			map.put("status", Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", Boolean.FALSE);
		}

		return map;

	}*/
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/account/findAccountByNum", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> findAccountByNum(ModelMap modelMap, int accountNum) {

		Map<String, Object> map = this.initMapStatus();

		Account ac = accountService.findAccountByAccountNumber(accountNum);

		if (null == ac) {
			map.put("status", Boolean.FALSE);
		} else {
			map.put("status", Boolean.TRUE);
			map.put("nickName", ac.getNickName());
		}

		return map;

	}

	/*@RequestMapping(value = "/bill/pay", method = RequestMethod.GET)
	public String payBill(ModelMap modelMap, int accountId) {

		List<Biller> billers = billService.getAllBillers();
		Account account = accountService.findAccount(accountId);

		modelMap.addAttribute("account", account);
		modelMap.addAttribute("billers", billers);

		return "/bill/pay";

	}

	@RequestMapping(value = "/bill/payP", method = RequestMethod.POST)
	public String payBillP(ModelMap modelMap, int accountId, int billerId, String billNumber) {

		try {
			boolean isSucceeded = billService.PayBill(accountId, billerId, billNumber);

			if (isSucceeded)
				return "redirect:/customer/index?status=1";
			else
				return "redirect:/customer/index?status=0";
		} catch (Exception e) {
			return "redirect:/customer/index?status=0";
		}

	}*/

/*	@RequestMapping(value = "/customer/getBillInfo", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getBillInfo(int billerId, String billNumber) {

		double billAmount = billService.getBillInfo(billerId, billNumber);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", billAmount);

		return map;

	}*/

	public Map<String, Object> initMapStatus() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Boolean.TRUE);
		map.put("msg", "");
		return map;
	}

}
