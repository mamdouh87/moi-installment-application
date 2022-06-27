package ae.isoft.moi.installment.service.mapper;

import ae.isoft.moi.installment.domain.Country;
import ae.isoft.moi.installment.domain.Customer;
import ae.isoft.moi.installment.service.dto.CountryDTO;
import ae.isoft.moi.installment.service.dto.CustomerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Customer} and its DTO {@link CustomerDTO}.
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {
    @Mapping(target = "country", source = "country", qualifiedByName = "countryId")
    CustomerDTO toDto(Customer s);

    @Named("countryId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CountryDTO toDtoCountryId(Country country);
}
