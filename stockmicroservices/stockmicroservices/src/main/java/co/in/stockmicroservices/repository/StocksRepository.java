package co.in.stockmicroservices.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.in.stockmicroservices.entity.Stocks;

@Repository
public interface StocksRepository extends JpaRepository<Stocks, Integer> {

	Optional<Stocks> findById(Integer id);

}
