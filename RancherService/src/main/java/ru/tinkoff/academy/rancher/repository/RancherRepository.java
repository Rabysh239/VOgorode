package ru.tinkoff.academy.rancher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.academy.rancher.model.Rancher;

@Repository
public interface RancherRepository extends JpaRepository<Rancher, Long> {
}
