package ae.isoft.moi.installment.repository;

import ae.isoft.moi.installment.domain.ContractPayment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ContractPayment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContractPaymentRepository extends JpaRepository<ContractPayment, Long> {}
