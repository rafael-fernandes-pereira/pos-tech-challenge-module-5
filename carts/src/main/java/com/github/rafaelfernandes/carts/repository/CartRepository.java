package com.github.rafaelfernandes.carts.repository;

import com.github.rafaelfernandes.carts.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CartRepository extends JpaRepository<CartEntity, UUID> {

    CartEntity findByUserIdAndProductId(UUID userId, UUID productId);

    List<CartEntity> findAllByUserId(UUID userId);

}
