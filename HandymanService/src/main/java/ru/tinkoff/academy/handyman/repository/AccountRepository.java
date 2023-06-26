package ru.tinkoff.academy.handyman.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tinkoff.academy.handyman.data.PaymentSystemStatistic;
import ru.tinkoff.academy.handyman.model.Account;

import java.util.List;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    @Query("{ $group: { _id: '$paymentSystem', minCreated: { $min: '$created' }, maxCreated: { $max: '$created' } } }")
    List<PaymentSystemStatistic> getPaymentSystemStatistics();
}
