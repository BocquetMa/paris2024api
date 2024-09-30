package bts.sio.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "epreuve")
public class Epreuve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="libelle")
    private String libelle;

    @Column(name = "date_debut")
    private String dateDebut;

    @Column(name = "date_fin")
    private String dateFin;

}
