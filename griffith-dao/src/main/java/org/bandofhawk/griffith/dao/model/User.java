package org.bandofhawk.griffith.dao.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Griffith
 * Created by Bluegear on 5/31/14.
 */
@Entity(name = "USER")
public class User implements Serializable {

    @SequenceGenerator(name="USER_GEN", sequenceName="USER_SEQ")
    @Id @GeneratedValue(generator = "USER_GEN")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;
    @Column
    private String credential;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

}
