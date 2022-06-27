package ae.isoft.moi.installment.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ae.isoft.moi.installment.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ContractSystemFeeDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContractSystemFeeDetailsDTO.class);
        ContractSystemFeeDetailsDTO contractSystemFeeDetailsDTO1 = new ContractSystemFeeDetailsDTO();
        contractSystemFeeDetailsDTO1.setId(1L);
        ContractSystemFeeDetailsDTO contractSystemFeeDetailsDTO2 = new ContractSystemFeeDetailsDTO();
        assertThat(contractSystemFeeDetailsDTO1).isNotEqualTo(contractSystemFeeDetailsDTO2);
        contractSystemFeeDetailsDTO2.setId(contractSystemFeeDetailsDTO1.getId());
        assertThat(contractSystemFeeDetailsDTO1).isEqualTo(contractSystemFeeDetailsDTO2);
        contractSystemFeeDetailsDTO2.setId(2L);
        assertThat(contractSystemFeeDetailsDTO1).isNotEqualTo(contractSystemFeeDetailsDTO2);
        contractSystemFeeDetailsDTO1.setId(null);
        assertThat(contractSystemFeeDetailsDTO1).isNotEqualTo(contractSystemFeeDetailsDTO2);
    }
}
