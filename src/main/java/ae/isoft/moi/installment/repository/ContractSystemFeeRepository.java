package ae.isoft.moi.installment.repository;

import ae.isoft.moi.installment.domain.ContractSystemFee;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ContractSystemFee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContractSystemFeeRepository extends JpaRepository<ContractSystemFee, Long> {}
