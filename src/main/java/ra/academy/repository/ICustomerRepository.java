package ra.academy.repository;

import ra.academy.model.Customer;

import java.util.List;

public interface ICustomerRepository{
    List<Customer> findAll();

    Customer findById(Long id);

    void save(Customer customer);

    void remove(Long id);

}
