package com.br.voting.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PAUTA")
public class Pauta implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_pauta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPauta;

    @Column(name = "pauta")
    private String pauta;

    @JsonIgnore
    @Column(name = "created_date")
    private LocalDateTime createdDate;

}
