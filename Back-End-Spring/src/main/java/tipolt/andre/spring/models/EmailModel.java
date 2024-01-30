package tipolt.andre.spring.models;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import tipolt.andre.spring.models.enums.StatusEmailEnum;

@Entity
@Table(name = "tb_email")
@Data
public class EmailModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String ownerRef;

    private String emailFrom;

    private String emailTo;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String text;

    private LocalDateTime sendDateEmail;

    private StatusEmailEnum statusEmail;
}