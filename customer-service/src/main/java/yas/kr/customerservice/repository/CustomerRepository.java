package yas.kr.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;
import yas.kr.customerservice.entities.Customer;
@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long> {


}
