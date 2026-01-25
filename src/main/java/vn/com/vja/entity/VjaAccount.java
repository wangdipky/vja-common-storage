package vn.com.vja.entity;

import com.vn.vja.entity.CoreAuditDelete;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
* VjaAccount
* QuangDK.
*/
@Entity
@Table(name = "vja_account", schema = "sch_vja_storage_api")
@Getter
@Setter
public class VjaAccount extends CoreAuditDelete {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vja_account")
    @SequenceGenerator(name = "seq_vja_account", sequenceName = "seq_vja_account", allocationSize = 1)
    @Column(name = "id", precision = 20, scale = 0)
    private Long id;

    @Column(name = "code", length = 150, nullable = false)
    private String code;

    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "mid_name", length = 100)
    private String midName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "username", length = 100, nullable = false)
    private String username;

    @Column(name = "email", length = 150, nullable = false)
    private String email;

    @Column(name = "role", length = 100)
    private String role;
}