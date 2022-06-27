package ae.isoft.moi.installment.service.mapper;

import ae.isoft.moi.installment.domain.InstallmentsPlan;
import ae.isoft.moi.installment.service.dto.InstallmentsPlanDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InstallmentsPlan} and its DTO {@link InstallmentsPlanDTO}.
 */
@Mapper(componentModel = "spring")
public interface InstallmentsPlanMapper extends EntityMapper<InstallmentsPlanDTO, InstallmentsPlan> {}
