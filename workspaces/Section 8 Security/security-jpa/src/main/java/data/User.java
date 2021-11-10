package data;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

@Entity
@Table(name = "users")
@UserDefinition
public class User extends PanacheEntity {

    @Username
    public String username;
    @Password
    public String password;
    @Roles
    public String roles;

    public User() {}

    public User(String username, String password, String roles) {
        this.username = username;
        this.password = BcryptUtil.bcryptHash(password);
        this.roles = roles;
    }
   
}
