package de.fi.webapp.presention.mapper;

import de.fi.webapp.presention.dto.SchweinDTO;
import de.fi.webapp.service.model.Schwein;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchweinDTOMapper {

    SchweinDTO convert(Schwein schwein);
    Schwein convert(SchweinDTO schweinDTO);
    Iterable<SchweinDTO> convert(Iterable<Schwein> schweine);
}
