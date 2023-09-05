package springboot.football_transfers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.football_transfers.persistance.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
