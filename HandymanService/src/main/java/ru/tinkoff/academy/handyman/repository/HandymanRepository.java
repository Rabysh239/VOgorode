package ru.tinkoff.academy.handyman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.academy.handyman.model.Handyman;

@Repository
public interface HandymanRepository extends JpaRepository<Handyman, Long> {
}
