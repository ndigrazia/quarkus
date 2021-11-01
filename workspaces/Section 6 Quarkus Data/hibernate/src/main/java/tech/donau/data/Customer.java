package tech.donau.data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

public class Customer extends PanacheEntityBase {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id; //custom id
    public String name;
    
}
