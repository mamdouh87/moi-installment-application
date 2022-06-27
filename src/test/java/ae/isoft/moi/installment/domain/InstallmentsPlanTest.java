package ae.isoft.moi.installment.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ae.isoft.moi.installment.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InstallmentsPlanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InstallmentsPlan.class);
        InstallmentsPlan installmentsPlan1 = new InstallmentsPlan();
        installmentsPlan1.setId(1L);
        InstallmentsPlan installmentsPlan2 = new InstallmentsPlan();
        installmentsPlan2.setId(installmentsPlan1.getId());
        assertThat(installmentsPlan1).isEqualTo(installmentsPlan2);
        installmentsPlan2.setId(2L);
        assertThat(installmentsPlan1).isNotEqualTo(installmentsPlan2);
        installmentsPlan1.setId(null);
        assertThat(installmentsPlan1).isNotEqualTo(installmentsPlan2);
    }
}
