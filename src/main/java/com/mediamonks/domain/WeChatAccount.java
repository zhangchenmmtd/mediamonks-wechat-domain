package com.mediamonks.domain;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.Minutes;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

/**
 * Created by zhangchen on 16/6/1.
 */
@Entity
@Table(name = "wechataccount")
public class WeChatAccount extends Domain{
    @Column(name = "appid")
    private String appId;
    @Column(name = "appsecret")
    private String appSecret;
    @Column(name = "ticket")
    private String ticket;
    @Column(name = "lastupdateat")
    private Date lastUpdatedAt;
    @JoinColumn(name = "clientid")
    @ManyToOne(targetEntity = Client.class)
    private Client client;
    @Column(name = "accounttype")
    private AccountType accountType;
    @Column(name = "enabled",columnDefinition = "tinyint(1) default 1")
    private boolean enabled;

    public WeChatAccount() {
        this.enabled = true;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public Client getClient() {
        return client;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getTicket() {
        return ticket;
    }

    public void changeTicket(String ticket) {
        this.ticket = ticket;
        this.lastUpdatedAt = new Date();
    }

    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public boolean needUpdate() {
        if(lastUpdatedAt == null){
            return true;
        }

        LocalDateTime dateTime = new LocalDateTime(lastUpdatedAt);
        return enabled && Minutes.minutesBetween(dateTime, new LocalDateTime()).getMinutes() > 60;
    }


    public void initial(String appId, String appSecret) {
        this.appId = appId;
        this.appSecret = appSecret;
    }

    public void changeClient(Client client) {
        this.client = client;
    }

    public void changeAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void updateStatus() {
        this.enabled = !enabled;
    }
}
