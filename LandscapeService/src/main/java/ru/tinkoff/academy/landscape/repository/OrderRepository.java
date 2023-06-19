package ru.tinkoff.academy.landscape.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.academy.landscape.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByOrderByUser_LastNameAsc(Pageable pageable);

    Page<Order> findAllByOrderByUser_LastNameDesc(Pageable pageable);

    Page<Order> findAllByOrderById(Pageable pageable);
}
