package ae.isoft.moi.installment.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ae.isoft.moi.installment.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RequestSystemFeeDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RequestSystemFeeDetailsDTO.class);
        RequestSystemFeeDetailsDTO requestSystemFeeDetailsDTO1 = new RequestSystemFeeDetailsDTO();
        requestSystemFeeDetailsDTO1.setId(1L);
        RequestSystemFeeDetailsDTO requestSystemFeeDetailsDTO2 = new RequestSystemFeeDetailsDTO();
        assertThat(requestSystemFeeDetailsDTO1).isNotEqualTo(requestSystemFeeDetailsDTO2);
        requestSystemFeeDetailsDTO2.setId(requestSystemFeeDetailsDTO1.getId());
        assertThat(requestSystemFeeDetailsDTO1).isEqualTo(requestSystemFeeDetailsDTO2);
        requestSystemFeeDetailsDTO2.setId(2L);
        assertThat(requestSystemFeeDetailsDTO1).isNotEqualTo(requestSystemFeeDetailsDTO2);
        requestSystemFeeDetailsDTO1.setId(null);
        assertThat(requestSystemFeeDetailsDTO1).isNotEqualTo(requestSystemFeeDetailsDTO2);
    }
}
