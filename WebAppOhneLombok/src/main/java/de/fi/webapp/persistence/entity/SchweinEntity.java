package de.fi.webapp.persistence.entity;

import java.util.Objects;
import java.util.UUID;

public class SchweinEntity {

    private UUID id;
    private String name;
    private int gewicht;

    public SchweinEntity() {
        this("nobody");
    }

    public SchweinEntity(final String name) {
        this.name = name;
        this.gewicht= 10;
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
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final SchweinEntity that = (SchweinEntity) o;
        return gewicht == that.gewicht && Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gewicht);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SchweinEntity{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", gewicht=").append(gewicht);
        sb.append('}');
        return sb.toString();
    }
}
