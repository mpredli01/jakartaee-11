package org.redlich.nosql;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

@Entity
public class Brewer {
    @Id
    private int id;

    @Column
    private String name;

    @Column
    private String city;

    @Column
    private String state;

    public Brewer() {
        this.id = 0;
        this.name = "{ brewer name }";
        this.city = "{ brewer city }";
        this.state = "{ brewer state }";
    }

    private Brewer(int id, String name, String city, String state) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.state = state;
        }

    public int getId() {
        return id;
        }

    public String getName() {
        return name;
        }

    public String getCity() {
        return city;
        }

    public String getState() {
        return state;
        }

    @Override
    public String toString() {
        return "Brewer { " +
                "id = '" + getId() + '\'' +
                ", name = '" + getName() + '\'' +
                ", city = '" + getCity() + '\'' +
                ", state = '" + getState() + '\'' +
                " }\n";
        }

    public static BrewerBuilder builder() {
        return new BrewerBuilder();
        }

    public static class BrewerBuilder {
        private int id;
        private String name;
        private String city;
        private String state;

        private BrewerBuilder() {
            }

        public BrewerBuilder id(int id) {
            this.id = id;
            return this;
            }

        public BrewerBuilder name(String name) {
            this.name = name;
            return this;
            }

        public BrewerBuilder city(String city) {
            this.city = city;
            return this;
            }

        public BrewerBuilder state(String state) {
            this.state = state;
            return this;
            }

        public Brewer build() {
            return new Brewer(id, name, city, state);
            }
        }
    }
