package ae.isoft.moi.installment.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ae.isoft.moi.installment.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InstallmentsPlanDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InstallmentsPlanDTO.class);
        InstallmentsPlanDTO installmentsPlanDTO1 = new InstallmentsPlanDTO();
        installmentsPlanDTO1.setId(1L);
        InstallmentsPlanDTO installmentsPlanDTO2 = new InstallmentsPlanDTO();
        assertThat(installmentsPlanDTO1).isNotEqualTo(installmentsPlanDTO2);
        installmentsPlanDTO2.setId(installmentsPlanDTO1.getId());
        assertThat(installmentsPlanDTO1).isEqualTo(installmentsPlanDTO2);
        installmentsPlanDTO2.setId(2L);
        assertThat(installmentsPlanDTO1).isNotEqualTo(installmentsPlanDTO2);
        installmentsPlanDTO1.setId(null);
        assertThat(installmentsPlanDTO1).isNotEqualTo(installmentsPlanDTO2);
    }
}
