package com.example.prog4.repository.cnaps.cnapsEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"phone\"")
@EqualsAndHashCode
@ToString
public class Phone {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;
    private String value;
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @JsonIgnore
    private Employee employee;
}
