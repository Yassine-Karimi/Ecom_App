package yas.kr.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import yas.kr.orderservice.entities.ProductItem;
@RepositoryRestResource
public interface ProductItemRepository extends JpaRepository<ProductItem,Long> {

}
