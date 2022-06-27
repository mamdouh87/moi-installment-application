package ae.isoft.moi.installment.service.mapper;

import ae.isoft.moi.installment.domain.RequestSystemFee;
import ae.isoft.moi.installment.domain.RequestSystemFeeDetails;
import ae.isoft.moi.installment.service.dto.RequestSystemFeeDTO;
import ae.isoft.moi.installment.service.dto.RequestSystemFeeDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RequestSystemFeeDetails} and its DTO {@link RequestSystemFeeDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface RequestSystemFeeDetailsMapper extends EntityMapper<RequestSystemFeeDetailsDTO, RequestSystemFeeDetails> {
    @Mapping(target = "requestSystemFee", source = "requestSystemFee", qualifiedByName = "requestSystemFeeId")
    RequestSystemFeeDetailsDTO toDto(RequestSystemFeeDetails s);

    @Named("requestSystemFeeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    RequestSystemFeeDTO toDtoRequestSystemFeeId(RequestSystemFee requestSystemFee);
}
