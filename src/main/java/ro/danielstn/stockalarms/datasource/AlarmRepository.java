package ro.danielstn.stockalarms.datasource;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmRepository extends CrudRepository<Alarm, Long>{
    List<Alarm> findByActive(boolean active);

    List<Alarm> findByUser(User user);
}
