package ae.isoft.moi.installment.repository;

import ae.isoft.moi.installment.domain.RequestSystemFeeDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RequestSystemFeeDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RequestSystemFeeDetailsRepository extends JpaRepository<RequestSystemFeeDetails, Long> {}
