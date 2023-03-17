package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Builder
@Getter
@Entity
@Table(name = "postings")
@NoArgsConstructor
@AllArgsConstructor
public class Posting {

    @Id
    @Column(name = "posting_number")
    private String postingNumber;
    @Column(name = "item")
    private int item;
    @Column(name = "doc_date")
    private LocalDate docDate;
    @Column(name = "posting_date")
    private LocalDate postingDate;
    @Column(name = "material_description")
    private String materialDescription;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "bun")
    private String bun;
    @Column(name = "price")
    private double price;
    @Column(name = "currency")
    private String currency;
    @Column(name = "username")
    private String username;
    @Column(name = "is_authorized")
    private boolean isAuthorized;

    public void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }
}
