package com.mediamonks.repository;

import com.mediamonks.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhangchen on 16/6/1.
 */
public interface ClientRepository extends JpaRepository<Client,Integer>{
    Client findByName(String name);

    Client findByGuid(String clientguid);
}
