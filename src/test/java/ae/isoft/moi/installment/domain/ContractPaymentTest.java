package ae.isoft.moi.installment.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ae.isoft.moi.installment.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ContractPaymentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContractPayment.class);
        ContractPayment contractPayment1 = new ContractPayment();
        contractPayment1.setId(1L);
        ContractPayment contractPayment2 = new ContractPayment();
        contractPayment2.setId(contractPayment1.getId());
        assertThat(contractPayment1).isEqualTo(contractPayment2);
        contractPayment2.setId(2L);
        assertThat(contractPayment1).isNotEqualTo(contractPayment2);
        contractPayment1.setId(null);
        assertThat(contractPayment1).isNotEqualTo(contractPayment2);
    }
}
