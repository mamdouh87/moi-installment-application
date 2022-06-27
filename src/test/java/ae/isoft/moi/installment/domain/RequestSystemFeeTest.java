package ae.isoft.moi.installment.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ae.isoft.moi.installment.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RequestSystemFeeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RequestSystemFee.class);
        RequestSystemFee requestSystemFee1 = new RequestSystemFee();
        requestSystemFee1.setId(1L);
        RequestSystemFee requestSystemFee2 = new RequestSystemFee();
        requestSystemFee2.setId(requestSystemFee1.getId());
        assertThat(requestSystemFee1).isEqualTo(requestSystemFee2);
        requestSystemFee2.setId(2L);
        assertThat(requestSystemFee1).isNotEqualTo(requestSystemFee2);
        requestSystemFee1.setId(null);
        assertThat(requestSystemFee1).isNotEqualTo(requestSystemFee2);
    }
}
