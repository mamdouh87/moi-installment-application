package ae.isoft.moi.installment.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RequestSystemFeeMapperTest {

    private RequestSystemFeeMapper requestSystemFeeMapper;

    @BeforeEach
    public void setUp() {
        requestSystemFeeMapper = new RequestSystemFeeMapperImpl();
    }
}
