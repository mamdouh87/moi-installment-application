package ae.isoft.moi.installment.service.mapper;

import ae.isoft.moi.installment.domain.Country;
import ae.isoft.moi.installment.service.dto.CountryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Country} and its DTO {@link CountryDTO}.
 */
@Mapper(componentModel = "spring")
public interface CountryMapper extends EntityMapper<CountryDTO, Country> {}
