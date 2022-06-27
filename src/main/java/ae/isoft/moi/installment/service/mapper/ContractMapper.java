package ae.isoft.moi.installment.service.mapper;

import ae.isoft.moi.installment.domain.Contract;
import ae.isoft.moi.installment.domain.InstallmentsPlan;
import ae.isoft.moi.installment.domain.TrafficContract;
import ae.isoft.moi.installment.service.dto.ContractDTO;
import ae.isoft.moi.installment.service.dto.InstallmentsPlanDTO;
import ae.isoft.moi.installment.service.dto.TrafficContractDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Contract} and its DTO {@link ContractDTO}.
 */
@Mapper(componentModel = "spring")
public interface ContractMapper extends EntityMapper<ContractDTO, Contract> {
    @Mapping(target = "trafficContract", source = "trafficContract", qualifiedByName = "trafficContractId")
    @Mapping(target = "installmentPlan", source = "installmentPlan", qualifiedByName = "installmentsPlanId")
    ContractDTO toDto(Contract s);

    @Named("trafficContractId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TrafficContractDTO toDtoTrafficContractId(TrafficContract trafficContract);

    @Named("installmentsPlanId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    InstallmentsPlanDTO toDtoInstallmentsPlanId(InstallmentsPlan installmentsPlan);
}
