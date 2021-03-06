package ae.isoft.moi.installment.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ae.isoft.moi.installment.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RequestDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RequestDTO.class);
        RequestDTO requestDTO1 = new RequestDTO();
        requestDTO1.setId(1L);
        RequestDTO requestDTO2 = new RequestDTO();
        assertThat(requestDTO1).isNotEqualTo(requestDTO2);
        requestDTO2.setId(requestDTO1.getId());
        assertThat(requestDTO1).isEqualTo(requestDTO2);
        requestDTO2.setId(2L);
        assertThat(requestDTO1).isNotEqualTo(requestDTO2);
        requestDTO1.setId(null);
        assertThat(requestDTO1).isNotEqualTo(requestDTO2);
    }
}
