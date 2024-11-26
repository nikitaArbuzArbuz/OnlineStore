package ru.moysklad.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.moysklad.onlinestore.entity.ProductSupply;

@Repository
public interface ProductSupplyRepository extends JpaRepository<ProductSupply, Long> {
}

