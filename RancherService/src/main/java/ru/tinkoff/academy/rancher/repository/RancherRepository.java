package ru.tinkoff.academy.rancher.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.tinkoff.academy.rancher.model.Rancher;

public interface RancherRepository extends MongoRepository<Rancher, String> {
}
