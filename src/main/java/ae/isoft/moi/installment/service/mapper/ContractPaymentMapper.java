package ae.isoft.moi.installment.service.mapper;

import ae.isoft.moi.installment.domain.Contract;
import ae.isoft.moi.installment.domain.ContractPayment;
import ae.isoft.moi.installment.service.dto.ContractDTO;
import ae.isoft.moi.installment.service.dto.ContractPaymentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ContractPayment} and its DTO {@link ContractPaymentDTO}.
 */
@Mapper(componentModel = "spring")
public interface ContractPaymentMapper extends EntityMapper<ContractPaymentDTO, ContractPayment> {
    @Mapping(target = "contract", source = "contract", qualifiedByName = "contractId")
    ContractPaymentDTO toDto(ContractPayment s);

    @Named("contractId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractDTO toDtoContractId(Contract contract);
}
