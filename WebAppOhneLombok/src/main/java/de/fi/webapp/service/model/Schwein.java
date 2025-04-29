package de.fi.webapp.service.model;



import java.util.Objects;
import java.util.UUID;

public class Schwein {
    private UUID id;
    private String name;
    private int gewicht;

    public Schwein() {
        this("nobody");
    }

    public Schwein(final String name) {
        this.name = name;
        this.gewicht= 10;
    }

    protected Schwein(final String name, int gewicht) {
        this.name = name;
        this.gewicht= gewicht;
    }

    public UUID getId() {
        return id;
    }

    protected void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    protected void setName(final String name) {
        this.name = name;
    }

    public int getGewicht() {
        return gewicht;
    }

    protected void setGewicht(final int gewicht) {
        this.gewicht = gewicht;
    }

    public void fuettern() {
        setGewicht(getGewicht()+1);
    }


    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final Schwein schwein = (Schwein) o;
        return gewicht == schwein.gewicht && Objects.equals(id, schwein.id) && Objects.equals(name, schwein.name);
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

    public static SchweinBuilder builder() {
        return new SchweinBuilder();
    }

    public static class SchweinBuilder {
        private Schwein schwein = new Schwein();


        public SchweinBuilder name(final String name) {
            schwein.setName(name);
            return this;
        }

        public SchweinBuilder gewicht(final int gewicht) {
            schwein.setGewicht(gewicht);
            return this;
        }

        public SchweinBuilder id(final UUID id) {
            schwein.setId(id);
            return this;
        }

        public Schwein build() {
            return schwein;
        }
    }
}
