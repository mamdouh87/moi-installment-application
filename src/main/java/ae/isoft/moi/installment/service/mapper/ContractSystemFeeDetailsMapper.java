package ae.isoft.moi.installment.service.mapper;

import ae.isoft.moi.installment.domain.ContractSystemFee;
import ae.isoft.moi.installment.domain.ContractSystemFeeDetails;
import ae.isoft.moi.installment.service.dto.ContractSystemFeeDTO;
import ae.isoft.moi.installment.service.dto.ContractSystemFeeDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ContractSystemFeeDetails} and its DTO {@link ContractSystemFeeDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface ContractSystemFeeDetailsMapper extends EntityMapper<ContractSystemFeeDetailsDTO, ContractSystemFeeDetails> {
    @Mapping(target = "contractSystemFee", source = "contractSystemFee", qualifiedByName = "contractSystemFeeId")
    ContractSystemFeeDetailsDTO toDto(ContractSystemFeeDetails s);

    @Named("contractSystemFeeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractSystemFeeDTO toDtoContractSystemFeeId(ContractSystemFee contractSystemFee);
}
