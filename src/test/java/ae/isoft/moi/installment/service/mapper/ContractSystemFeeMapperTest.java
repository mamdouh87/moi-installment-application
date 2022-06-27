package ae.isoft.moi.installment.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContractSystemFeeMapperTest {

    private ContractSystemFeeMapper contractSystemFeeMapper;

    @BeforeEach
    public void setUp() {
        contractSystemFeeMapper = new ContractSystemFeeMapperImpl();
    }
}
