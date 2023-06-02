package ru.tinkoff.academy.handyman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.academy.handyman.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
}
