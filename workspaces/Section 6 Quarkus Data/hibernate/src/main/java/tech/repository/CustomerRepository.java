package tech.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import tech.donau.data.Book;
import tech.donau.data.Customer;

@ApplicationScoped
public class CustomerRepository implements PanacheRepositoryBase<Customer, String>{
    
    
}
