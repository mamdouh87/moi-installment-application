package ae.isoft.moi.installment.repository;

import ae.isoft.moi.installment.domain.RequestSystemFee;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RequestSystemFee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RequestSystemFeeRepository extends JpaRepository<RequestSystemFee, Long> {}
