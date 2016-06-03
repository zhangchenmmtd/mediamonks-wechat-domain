package com.mediamonks.domain;

import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangchen on 16/6/1.
 */
@Entity
@Table(name = "client")
public class Client extends Domain{
    @Column(name = "name")
    private String name;
    @Column(name = "enabled")
    private boolean enabled;
    @OneToMany(targetEntity = WeChatAccount.class,mappedBy = "client",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<WeChatAccount> weChatAccounts;

    public Client() {
        this.enabled = true;
    }

    public String getName() {
        return name;
    }

    public List<WeChatAccount> getWeChatAccounts() {
        return weChatAccounts;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void addWeChatAccount(WeChatAccount weChatAccount) {
        if(this.weChatAccounts == null){
            this.weChatAccounts = new ArrayList<WeChatAccount>();
        }
        weChatAccount.changeClient(this);
        this.weChatAccounts.add(weChatAccount);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean hasWeChatAccounts() {
        return this.weChatAccounts != null;
    }

    public WeChatAccount getWeChatAccount(String wechatguid) {
        if(this.weChatAccounts == null || !StringUtils.hasText(wechatguid)){
            return null;
        }
        for (WeChatAccount weChatAccount : weChatAccounts) {
            if(weChatAccount.getGuid().endsWith(wechatguid)){
                return weChatAccount;
            }
        }
        return null;
    }
}
