package com.example.vaccnow.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.example.vaccnow.util.PaymentMethodConverter;
import com.example.vaccnow.util.PaymentMethodEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_schedule_vaccination", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "schd_vac_request_email" }) })

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id" }, callSuper = false)
public class ScheduleVaccination extends BaseEntity<Integer> {

    @Id
    @Column(name = "schedule_vac_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "schedule_vac_payment_method")
    @Convert(converter = PaymentMethodConverter.class)
    private PaymentMethodEnum paymentMethod;

    @Column(name = "schedule_vac_request")
    private String request;

    @Column(name = "schedule_vac_desc")
    private String vacDesc;

    @Column(name = "schd_vac_request_email")
    private String email;

    @Column(name = "schedule_vac_Time")
    private Date vacTime;

    @Column(name = "schedule_vac_confirm")
    private boolean confirmed;

    @Override
    public Integer getPK() {
        return getId();
    }

}
