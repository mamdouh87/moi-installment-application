package ae.isoft.moi.installment.repository;

import ae.isoft.moi.installment.domain.TrafficContract;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TrafficContract entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrafficContractRepository extends JpaRepository<TrafficContract, Long> {}
