package yas.kr.orderservice.repository;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import yas.kr.orderservice.entities.Order;
@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order,Long> {
}
