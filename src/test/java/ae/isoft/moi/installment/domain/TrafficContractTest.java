package ae.isoft.moi.installment.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ae.isoft.moi.installment.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TrafficContractTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrafficContract.class);
        TrafficContract trafficContract1 = new TrafficContract();
        trafficContract1.setId(1L);
        TrafficContract trafficContract2 = new TrafficContract();
        trafficContract2.setId(trafficContract1.getId());
        assertThat(trafficContract1).isEqualTo(trafficContract2);
        trafficContract2.setId(2L);
        assertThat(trafficContract1).isNotEqualTo(trafficContract2);
        trafficContract1.setId(null);
        assertThat(trafficContract1).isNotEqualTo(trafficContract2);
    }
}
