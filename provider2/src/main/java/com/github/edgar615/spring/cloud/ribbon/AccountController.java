package com.github.edgar615.spring.cloud.ribbon;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 张雨舟
 * @Date 2020/8/6
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @GetMapping("/{id}")
    public Account get(@PathVariable("id") String id) {
        Account account = new Account();
        account.setId(id);
        account.setName("provider2");
        return account;
    }

    @PostMapping
    public Account add(@RequestBody Account account) {
        System.out.println(account);
        return account;
    }

    @GetMapping
    public List<Account> list() {
        Account account = new Account();
        account.setId(UUID.randomUUID().toString());
        account.setName(UUID.randomUUID().toString());
        return Collections.singletonList(account);
    }

    @GetMapping("/error")
    public List<Account> error() {
        System.out.println("error");
       throw new RuntimeException("occur err");
    }

    @GetMapping("/sleep")
    public List<Account> sleep(@RequestParam(value = "second", defaultValue = "3") Integer second) {
        System.out.println("sleep start");
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("sleep end");
        throw new RuntimeException("occur err");
    }

}
