package ru.tinkoff.academy.handyman.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.academy.handyman.model.Handyman;

@Repository
public interface HandymanRepository extends MongoRepository<Handyman, String> {
}
