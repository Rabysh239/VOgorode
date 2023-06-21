package ru.tinkoff.academy.handyman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tinkoff.academy.handyman.data.PaymentSystemStatistic;
import ru.tinkoff.academy.handyman.model.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("""
            select new ru.tinkoff.academy.handyman.data.PaymentSystemStatistic(a.paymentSystem, min(a.created), max(a.created))
            from Account a
            group by a.paymentSystem""")
    List<PaymentSystemStatistic> getPaymentSystemStatistics();
}
