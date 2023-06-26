package ru.tinkoff.academy.rancher.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.academy.rancher.model.Field;

@Repository
public interface FieldRepository extends MongoRepository<Field, String> {
}
