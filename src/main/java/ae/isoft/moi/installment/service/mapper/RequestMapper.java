package ae.isoft.moi.installment.service.mapper;

import ae.isoft.moi.installment.domain.Customer;
import ae.isoft.moi.installment.domain.Request;
import ae.isoft.moi.installment.service.dto.CustomerDTO;
import ae.isoft.moi.installment.service.dto.RequestDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Request} and its DTO {@link RequestDTO}.
 */
@Mapper(componentModel = "spring")
public interface RequestMapper extends EntityMapper<RequestDTO, Request> {
    @Mapping(target = "customer", source = "customer", qualifiedByName = "customerId")
    RequestDTO toDto(Request s);

    @Named("customerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CustomerDTO toDtoCustomerId(Customer customer);
}
