package ae.isoft.moi.installment.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InstallmentsPlanMapperTest {

    private InstallmentsPlanMapper installmentsPlanMapper;

    @BeforeEach
    public void setUp() {
        installmentsPlanMapper = new InstallmentsPlanMapperImpl();
    }
}
