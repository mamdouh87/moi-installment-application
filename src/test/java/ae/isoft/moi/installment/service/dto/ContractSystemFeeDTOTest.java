package ae.isoft.moi.installment.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ae.isoft.moi.installment.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ContractSystemFeeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContractSystemFeeDTO.class);
        ContractSystemFeeDTO contractSystemFeeDTO1 = new ContractSystemFeeDTO();
        contractSystemFeeDTO1.setId(1L);
        ContractSystemFeeDTO contractSystemFeeDTO2 = new ContractSystemFeeDTO();
        assertThat(contractSystemFeeDTO1).isNotEqualTo(contractSystemFeeDTO2);
        contractSystemFeeDTO2.setId(contractSystemFeeDTO1.getId());
        assertThat(contractSystemFeeDTO1).isEqualTo(contractSystemFeeDTO2);
        contractSystemFeeDTO2.setId(2L);
        assertThat(contractSystemFeeDTO1).isNotEqualTo(contractSystemFeeDTO2);
        contractSystemFeeDTO1.setId(null);
        assertThat(contractSystemFeeDTO1).isNotEqualTo(contractSystemFeeDTO2);
    }
}
