package edu.mum.cbs.controller;

import java.util.HashMap;
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
import edu.mum.cbs.domain.Transaction;
import edu.mum.cbs.servie.IAccountService;
import edu.mum.cbs.servie.ICustomerService;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	ICustomerService customerService;

	@Autowired
	IAccountService accountService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String accountIndex(ModelMap modelMap, int customerId, String alertStatus, String alertInfo) {

		Customer cust = customerService.findCustomer(customerId);

		modelMap.addAttribute("cust", cust);
		modelMap.addAttribute("alertStatus", alertStatus);
		modelMap.addAttribute("alertInfo", alertInfo);

		return "/account/index";

	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addAccount(ModelMap modelMap, String customerId) {
		modelMap.addAttribute("customerId", customerId);
		return "account/add";
	}

	//
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addAccountPost(ModelMap modelMap, @Valid @ModelAttribute Account account, BindingResult result) {

		if (result.hasErrors()) {

			modelMap.addAttribute("customerId", account.getCustomer().getId());
			return "/account/add";
		}

		accountService.saveAccount(account);
		return "redirect:/customer/index?accStatus=1";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/deposit", method = RequestMethod.GET)
	public String accountDeposit(ModelMap modelMap, int accountId) {

		Account account = accountService.findAccount(accountId);

		modelMap.addAttribute("account", account);

		return "/account/deposit";

	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> accountDepositP(ModelMap modelMap, double amount, int accountId) {

		Map<String, Object> map = this.initMapStatus();

		try {
			accountService.depositMoney(amount, accountId);

			map.put("status", Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", Boolean.FALSE);
		}

		return map;

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/withdraw", method = RequestMethod.GET)
	public String accountWithdraw(ModelMap modelMap, int accountId) {

		Account account = accountService.findAccount(accountId);

		modelMap.addAttribute("account", account);

		return "/account/withdraw";

	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> accountWithdrawP(ModelMap modelMap, double amount, int accountId) {

		Map<String, Object> map = this.initMapStatus();

		try {
			Transaction result = accountService.withdrawMoney(amount, accountId);

			map.put("status", result != null);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", Boolean.FALSE);
		}

		return map;

	}

	public Map<String, Object> initMapStatus() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Boolean.TRUE);
		map.put("msg", "");
		return map;
	}
}
