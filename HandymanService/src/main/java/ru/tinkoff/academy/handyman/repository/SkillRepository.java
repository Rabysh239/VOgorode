package ru.tinkoff.academy.handyman.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.academy.handyman.model.Skill;

@Repository
public interface SkillRepository extends MongoRepository<Skill, String> {
}
