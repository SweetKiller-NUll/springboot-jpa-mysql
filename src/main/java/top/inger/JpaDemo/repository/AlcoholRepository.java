package top.inger.JpaDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import top.inger.JpaDemo.domain.Alcohol;

@Repository
public interface AlcoholRepository extends JpaRepository<Alcohol, Integer>, JpaSpecificationExecutor<Alcohol> {
}
