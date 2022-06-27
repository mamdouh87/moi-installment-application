package ae.isoft.moi.installment.repository;

import ae.isoft.moi.installment.domain.ContractSystemFeeDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ContractSystemFeeDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContractSystemFeeDetailsRepository extends JpaRepository<ContractSystemFeeDetails, Long> {}
