package ru.tinkoff.academy.rancher.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tinkoff.academy.rancher.data.Statistic;
import ru.tinkoff.academy.rancher.model.Rancher;

@Repository
public interface RancherRepository extends JpaRepository<Rancher, Long> {
    @Query("""
            select new ru.tinkoff.academy.rancher.data.Statistic(r.userId, min(ST_Area(f.area)), max(ST_Area(f.area)), avg(ST_Area(f.area)))
            from Rancher r join Field f on f.rancher.id = r.id
            group by r.id""")
    Page<Statistic> getStatistics(Pageable pageable);
}
