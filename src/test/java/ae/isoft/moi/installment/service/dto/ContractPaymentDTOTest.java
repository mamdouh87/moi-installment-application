package ae.isoft.moi.installment.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ae.isoft.moi.installment.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ContractPaymentDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContractPaymentDTO.class);
        ContractPaymentDTO contractPaymentDTO1 = new ContractPaymentDTO();
        contractPaymentDTO1.setId(1L);
        ContractPaymentDTO contractPaymentDTO2 = new ContractPaymentDTO();
        assertThat(contractPaymentDTO1).isNotEqualTo(contractPaymentDTO2);
        contractPaymentDTO2.setId(contractPaymentDTO1.getId());
        assertThat(contractPaymentDTO1).isEqualTo(contractPaymentDTO2);
        contractPaymentDTO2.setId(2L);
        assertThat(contractPaymentDTO1).isNotEqualTo(contractPaymentDTO2);
        contractPaymentDTO1.setId(null);
        assertThat(contractPaymentDTO1).isNotEqualTo(contractPaymentDTO2);
    }
}
