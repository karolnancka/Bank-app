package bank.repository;

import bank.model.OperationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationHistoryRepository extends JpaRepository<OperationHistory, Long> {
}
