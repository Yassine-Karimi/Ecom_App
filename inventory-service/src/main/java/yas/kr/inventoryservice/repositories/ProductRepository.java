package yas.kr.inventoryservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import yas.kr.inventoryservice.entities.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{

}
