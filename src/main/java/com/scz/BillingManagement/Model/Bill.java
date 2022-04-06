package com.scz.BillingManagement.Model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "TB_BILLS")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @NotBlank(message = "Description is required")
    @Size(max=60, message = "Description cannot exceed 60 characters")
    private String description;

    @NotNull(message = "Due Date is required")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dueDate;

    @NotNull(message = "Value is required" )
    @DecimalMin(value = "0.01", message = "Value cannot be lower than £0.01")
    @DecimalMax(value = "999999999.99", message = "Value cannot be higher than £999999999.99")
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private BillStatus status;

}
