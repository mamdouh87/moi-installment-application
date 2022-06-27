package ae.isoft.moi.installment.service.mapper;

import ae.isoft.moi.installment.domain.Contract;
import ae.isoft.moi.installment.domain.ContractSystemFee;
import ae.isoft.moi.installment.service.dto.ContractDTO;
import ae.isoft.moi.installment.service.dto.ContractSystemFeeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ContractSystemFee} and its DTO {@link ContractSystemFeeDTO}.
 */
@Mapper(componentModel = "spring")
public interface ContractSystemFeeMapper extends EntityMapper<ContractSystemFeeDTO, ContractSystemFee> {
    @Mapping(target = "contract", source = "contract", qualifiedByName = "contractId")
    ContractSystemFeeDTO toDto(ContractSystemFee s);

    @Named("contractId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractDTO toDtoContractId(Contract contract);
}
