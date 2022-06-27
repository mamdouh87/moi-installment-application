package ae.isoft.moi.installment.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ae.isoft.moi.installment.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TrafficContractDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrafficContractDTO.class);
        TrafficContractDTO trafficContractDTO1 = new TrafficContractDTO();
        trafficContractDTO1.setId(1L);
        TrafficContractDTO trafficContractDTO2 = new TrafficContractDTO();
        assertThat(trafficContractDTO1).isNotEqualTo(trafficContractDTO2);
        trafficContractDTO2.setId(trafficContractDTO1.getId());
        assertThat(trafficContractDTO1).isEqualTo(trafficContractDTO2);
        trafficContractDTO2.setId(2L);
        assertThat(trafficContractDTO1).isNotEqualTo(trafficContractDTO2);
        trafficContractDTO1.setId(null);
        assertThat(trafficContractDTO1).isNotEqualTo(trafficContractDTO2);
    }
}
