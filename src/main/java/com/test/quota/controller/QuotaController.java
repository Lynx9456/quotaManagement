package com.test.quota.controller;

import com.test.quota.bean.Account;
import com.test.quota.request.AccountRequest;
import com.test.quota.service.QuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController()
@RequestMapping("/api/quota")
public class QuotaController {

    @Autowired
    private QuotaService quotaService;

    @PostMapping("/init")
    public Account initializeAccount(@RequestBody AccountRequest request) {
        Account account = quotaService.initializeAccount(request.getAccountName(), request.getInitialQuota(), request.getQuotaType(), request.getDescription());
        return account;
    }

    @GetMapping("/increase")
    public Boolean increaseQuota(@RequestParam int accountId, @RequestParam double amount) {
        return quotaService.increaseQuota(accountId, amount);
    }

    @GetMapping("/decrease")
    public Boolean decreaseQuota(@RequestParam int accountId, @RequestParam double amount) {
        return quotaService.decreaseQuota(accountId, amount);
    }

    @GetMapping("/info/{accountId}")
    public Account getAccountInfo(@PathVariable int accountId) {
        return quotaService.getAccountInfo(accountId);
    }

    @GetMapping("/queryQuota")
    public Double queryQuota(@RequestParam int accountId,@RequestParam String quotaType) {
        return quotaService.queryQuota(accountId,quotaType);
    }
}
