package ae.isoft.moi.installment.service.mapper;

import ae.isoft.moi.installment.domain.Request;
import ae.isoft.moi.installment.domain.RequestSystemFee;
import ae.isoft.moi.installment.service.dto.RequestDTO;
import ae.isoft.moi.installment.service.dto.RequestSystemFeeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RequestSystemFee} and its DTO {@link RequestSystemFeeDTO}.
 */
@Mapper(componentModel = "spring")
public interface RequestSystemFeeMapper extends EntityMapper<RequestSystemFeeDTO, RequestSystemFee> {
    @Mapping(target = "request", source = "request", qualifiedByName = "requestId")
    RequestSystemFeeDTO toDto(RequestSystemFee s);

    @Named("requestId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    RequestDTO toDtoRequestId(Request request);
}
