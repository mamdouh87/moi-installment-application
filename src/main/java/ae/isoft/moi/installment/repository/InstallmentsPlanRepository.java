package ae.isoft.moi.installment.repository;

import ae.isoft.moi.installment.domain.InstallmentsPlan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the InstallmentsPlan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InstallmentsPlanRepository extends JpaRepository<InstallmentsPlan, Long> {}
