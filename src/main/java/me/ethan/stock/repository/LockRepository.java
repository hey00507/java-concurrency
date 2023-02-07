package me.ethan.stock.repository;

import me.ethan.stock.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LockRepository extends JpaRepository<Stock,Long> {
//    @Query(value = "select get_lock(:key, 3000)", nativeQuery = true)

}
