package ae.isoft.moi.installment.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RequestSystemFeeDetailsMapperTest {

    private RequestSystemFeeDetailsMapper requestSystemFeeDetailsMapper;

    @BeforeEach
    public void setUp() {
        requestSystemFeeDetailsMapper = new RequestSystemFeeDetailsMapperImpl();
    }
}
