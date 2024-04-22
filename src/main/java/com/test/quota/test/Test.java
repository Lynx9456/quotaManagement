package com.test.quota.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.quota.bean.Account;
import com.test.quota.enums.QuotaTypeEnum;
import com.test.quota.request.AccountRequest;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(100, (new BasicThreadFactory.Builder()).namingPattern("cronTab-%d").daemon(true).build());
        ObjectMapper mapper = new ObjectMapper();

        int threadCount = 100;

        long[] userIds = new long[threadCount];

        for (int i = 0; i < threadCount; i++) {
            // 模拟100个用户定时操作额度
            int finalI = i;
            scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
                long userId = userIds[finalI];
                System.out.println(Thread.currentThread().getName() + " "+ userId);

                if (userId == 0) {
                    // 创建账号
                    AccountRequest accountRequest = new AccountRequest();
                    accountRequest.setAccountName("user-" + userId);
                    accountRequest.setInitialQuota(100.0);
                    accountRequest.setQuotaType(QuotaTypeEnum.CASH.getCode());
                    accountRequest.setDescription("测试账号");

                    try {
                        // 创建账号并初始化额度
                        String result = HttpUtil.post("http://localhost:8080/api/quota/init", mapper.writeValueAsString(accountRequest));
                        Account account = mapper.readValue(result, Account.class);
                        userId = account.getId();
                        userIds[finalI] = userId;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }

                // 1、额度查询
                String result;
                double quota1 = 0;
                userId = userIds[finalI];
                try {
                    // 根据用户名查询账号
                    result = HttpUtil.get("http://localhost:8080/api/quota/info/" + userId);
                    Account account = mapper.readValue(result, Account.class);
                    quota1 = account.getQuota();
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                System.out.println("初始额度：" + quota1);

                // 4、额度增加
                double addAmount = 200;
                try {
                    HttpUtil.get("http://localhost:8080/api/quota/increase?accountId=" + userId + "&amount=" + addAmount);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }

                // 5、额度扣减
                double subAmount = 100;
                try {
                    // 额度查询
                    HttpUtil.get("http://localhost:8080/api/quota/decrease?accountId=" + userId + "&amount=" + subAmount);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }

                // 6、额度查询
                double quota2 = 0;
                try {
                    // 根据用户名查询账号
                    result = HttpUtil.get("http://localhost:8080/api/quota/queryQuota?accountId=" + userId);
                    quota2 = Double.parseDouble(result);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                System.out.println("余额度：" + quota2);

                // 对比额度是否能对的上
                assert quota1 + addAmount - subAmount == quota2;

            }, 0, 1, TimeUnit.SECONDS); // 1秒调度一次
        }

        while (true) {}
    }
}
