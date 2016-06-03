package com.mediamonks.domain;

/**
 * Created by zhangchen on 16/6/1.
 */
public enum AccountType {
    SERVICEACCOUNT("Service account"),SUBSCRIPTIONACCOUNT("Subscription account"), DEVELOPERACCOUNT("Developer account");

    private String accountName;

    AccountType(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountName() {
        return accountName;
    }
}
