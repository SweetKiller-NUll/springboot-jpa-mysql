package top.inger.JpaDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import top.inger.JpaDemo.domain.FeedBack;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBack, Integer>, JpaSpecificationExecutor<FeedBack> {
}
