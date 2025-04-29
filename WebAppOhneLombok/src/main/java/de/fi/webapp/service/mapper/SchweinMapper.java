package de.fi.webapp.service.mapper;


import de.fi.webapp.persistence.entity.SchweinEntity;
import de.fi.webapp.service.model.Schwein;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;

@Mapper(componentModel = "spring")
public interface SchweinMapper {

    Schwein convert(SchweinEntity entity);
    SchweinEntity convert(Schwein schwein);
    Iterable<Schwein> convert(Iterable<SchweinEntity> entities);
}
