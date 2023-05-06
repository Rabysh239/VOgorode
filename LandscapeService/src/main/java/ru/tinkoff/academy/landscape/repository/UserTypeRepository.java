package ru.tinkoff.academy.landscape.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.academy.landscape.model.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Integer> {
    UserType getReferenceByName(String type);
}
