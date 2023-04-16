package com.cadastro.produtos.repositories;

import com.cadastro.produtos.models.BlockedTokenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockedTokenRepository extends JpaRepository<BlockedTokenModel, Long> {

    boolean existsByBlockedToken(String token);

}
