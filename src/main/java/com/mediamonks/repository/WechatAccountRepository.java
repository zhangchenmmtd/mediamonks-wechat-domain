package com.mediamonks.repository;

import com.mediamonks.domain.WeChatAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhangchen on 16/6/1.
 */
public interface WechatAccountRepository extends JpaRepository<WeChatAccount,Integer>{
    WeChatAccount findByGuid(String wechatId);
}
