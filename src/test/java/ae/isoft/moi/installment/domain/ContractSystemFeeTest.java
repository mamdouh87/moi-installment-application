package ae.isoft.moi.installment.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ae.isoft.moi.installment.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ContractSystemFeeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContractSystemFee.class);
        ContractSystemFee contractSystemFee1 = new ContractSystemFee();
        contractSystemFee1.setId(1L);
        ContractSystemFee contractSystemFee2 = new ContractSystemFee();
        contractSystemFee2.setId(contractSystemFee1.getId());
        assertThat(contractSystemFee1).isEqualTo(contractSystemFee2);
        contractSystemFee2.setId(2L);
        assertThat(contractSystemFee1).isNotEqualTo(contractSystemFee2);
        contractSystemFee1.setId(null);
        assertThat(contractSystemFee1).isNotEqualTo(contractSystemFee2);
    }
}
