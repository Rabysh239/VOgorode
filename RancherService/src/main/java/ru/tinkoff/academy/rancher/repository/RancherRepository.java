package ru.tinkoff.academy.rancher.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.academy.rancher.data.Statistic;
import ru.tinkoff.academy.rancher.model.Rancher;

@Repository
public interface RancherRepository extends MongoRepository<Rancher, String> {
    default Page<Statistic> getStatistics(Pageable pageable) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
