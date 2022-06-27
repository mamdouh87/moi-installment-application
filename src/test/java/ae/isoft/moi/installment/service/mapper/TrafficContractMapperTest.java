package ae.isoft.moi.installment.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrafficContractMapperTest {

    private TrafficContractMapper trafficContractMapper;

    @BeforeEach
    public void setUp() {
        trafficContractMapper = new TrafficContractMapperImpl();
    }
}
