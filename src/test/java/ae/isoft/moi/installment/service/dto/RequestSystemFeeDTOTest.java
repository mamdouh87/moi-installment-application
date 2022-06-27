package ae.isoft.moi.installment.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ae.isoft.moi.installment.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RequestSystemFeeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RequestSystemFeeDTO.class);
        RequestSystemFeeDTO requestSystemFeeDTO1 = new RequestSystemFeeDTO();
        requestSystemFeeDTO1.setId(1L);
        RequestSystemFeeDTO requestSystemFeeDTO2 = new RequestSystemFeeDTO();
        assertThat(requestSystemFeeDTO1).isNotEqualTo(requestSystemFeeDTO2);
        requestSystemFeeDTO2.setId(requestSystemFeeDTO1.getId());
        assertThat(requestSystemFeeDTO1).isEqualTo(requestSystemFeeDTO2);
        requestSystemFeeDTO2.setId(2L);
        assertThat(requestSystemFeeDTO1).isNotEqualTo(requestSystemFeeDTO2);
        requestSystemFeeDTO1.setId(null);
        assertThat(requestSystemFeeDTO1).isNotEqualTo(requestSystemFeeDTO2);
    }
}
