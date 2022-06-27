package ae.isoft.moi.installment.service.mapper;

import ae.isoft.moi.installment.domain.TrafficContract;
import ae.isoft.moi.installment.service.dto.TrafficContractDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TrafficContract} and its DTO {@link TrafficContractDTO}.
 */
@Mapper(componentModel = "spring")
public interface TrafficContractMapper extends EntityMapper<TrafficContractDTO, TrafficContract> {}
