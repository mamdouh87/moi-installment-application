package ae.isoft.moi.installment.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ae.isoft.moi.installment.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RequestSystemFeeDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RequestSystemFeeDetails.class);
        RequestSystemFeeDetails requestSystemFeeDetails1 = new RequestSystemFeeDetails();
        requestSystemFeeDetails1.setId(1L);
        RequestSystemFeeDetails requestSystemFeeDetails2 = new RequestSystemFeeDetails();
        requestSystemFeeDetails2.setId(requestSystemFeeDetails1.getId());
        assertThat(requestSystemFeeDetails1).isEqualTo(requestSystemFeeDetails2);
        requestSystemFeeDetails2.setId(2L);
        assertThat(requestSystemFeeDetails1).isNotEqualTo(requestSystemFeeDetails2);
        requestSystemFeeDetails1.setId(null);
        assertThat(requestSystemFeeDetails1).isNotEqualTo(requestSystemFeeDetails2);
    }
}
