package ae.isoft.moi.installment.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ae.isoft.moi.installment.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ContractSystemFeeDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContractSystemFeeDetails.class);
        ContractSystemFeeDetails contractSystemFeeDetails1 = new ContractSystemFeeDetails();
        contractSystemFeeDetails1.setId(1L);
        ContractSystemFeeDetails contractSystemFeeDetails2 = new ContractSystemFeeDetails();
        contractSystemFeeDetails2.setId(contractSystemFeeDetails1.getId());
        assertThat(contractSystemFeeDetails1).isEqualTo(contractSystemFeeDetails2);
        contractSystemFeeDetails2.setId(2L);
        assertThat(contractSystemFeeDetails1).isNotEqualTo(contractSystemFeeDetails2);
        contractSystemFeeDetails1.setId(null);
        assertThat(contractSystemFeeDetails1).isNotEqualTo(contractSystemFeeDetails2);
    }
}
