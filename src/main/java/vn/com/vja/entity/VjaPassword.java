package vn.com.vja.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * VjaPassword
 * QuangDK.
 */
@Entity
@Table(name = "vja_password", schema = "sch_vja_storage_api")
@Getter
@Setter
public class VjaPassword {

    @Id
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "password")
    private String password;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "updated_by")
    private Long updatedBy;
}
