package ru.tinkoff.academy.handyman.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.tinkoff.academy.handyman.model.Handyman;

import java.util.UUID;

public interface HandymanRepository extends MongoRepository<Handyman, String> {
}
