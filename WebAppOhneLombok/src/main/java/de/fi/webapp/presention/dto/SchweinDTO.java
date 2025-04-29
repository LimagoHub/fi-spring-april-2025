package de.fi.webapp.presention.dto;

import java.util.Objects;
import java.util.UUID;

public class SchweinDTO {
    private UUID id;
    private String name;
    private int gewicht;

    public SchweinDTO() {
    }

    public SchweinDTO(final UUID id, final String name, final int gewicht) {
        this.id = id;
        this.name = name;
        this.gewicht = gewicht;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getGewicht() {
        return gewicht;
    }

    public void setGewicht(final int gewicht) {
        this.gewicht = gewicht;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SchweinDTO{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", gewicht=").append(gewicht);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final SchweinDTO that = (SchweinDTO) o;
        return gewicht == that.gewicht && Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gewicht);
    }
}
