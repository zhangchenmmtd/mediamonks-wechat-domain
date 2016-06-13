package com.mediamonks.domain;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.Minutes;
import org.springframework.util.StringUtils;

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

    /**
     * For some client, they do not want MM to have direct integration with WeChat API since wechat
     * has some limitation on ticket or accessToken request frequency, so client may build a system as a hub for MM to
     * obtain the ticket and token, in that case, We need to store both accessToken and ticket from the client side
     */
    @Column(name = "accessToken")
    private String accessToken;
    @Column(name = "clientserverid")
    private String clientServerID;
    @Column(name = "clientserverscecret")
    private String clientServerSecrect;
    @Column(name = "accesstokenurl")
    private String accessTokenUrl;
    @Column(name = "jssdkticketurl")
    private String jsSDKTicketUrl;
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
        if(!enabled){
            return false;
        }
        if(lastUpdatedAt == null || ticket == null){
            return true;
        }
        if(usingClientServer()){
            return true;
        }

        LocalDateTime dateTime = new LocalDateTime(lastUpdatedAt);
        return Minutes.minutesBetween(dateTime, new LocalDateTime()).getMinutes() > 60;
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

    public String getAccessTokenUrl() {
        return accessTokenUrl;
    }

    public String getJsSDKTicketUrl() {
        return jsSDKTicketUrl;
    }

    public void changeUrls(String accessTokenUrl, String jsSDKTicketUrl) {
        this.accessTokenUrl = accessTokenUrl;
        this.jsSDKTicketUrl = jsSDKTicketUrl;
    }

    public void changeAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getClientServerID() {
        return clientServerID;
    }

    public String getClientServerSecrect() {
        return clientServerSecrect;
    }

    public void changeClientServerInfo(String clientServerID, String clientServerSecrect) {
        this.clientServerID = clientServerID;
        this.clientServerSecrect = clientServerSecrect;
    }

    public boolean usingClientServer() {
        return StringUtils.hasText(clientServerID);
    }
}
