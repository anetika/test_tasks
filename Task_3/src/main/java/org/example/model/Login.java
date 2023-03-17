package org.example.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "logins")
@Entity
public class Login {

    @Column(name = "application")
    private String application;
    @Id
    @Column(name = "app_account_name")
    private String appAccountName;
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "job_title")
    private String jobTitle;
    @Column(name = "department")
    private String department;
}
