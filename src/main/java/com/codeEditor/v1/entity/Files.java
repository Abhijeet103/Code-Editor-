package com.codeEditor.v1.entity;

import com.codeEditor.v1.utils.BoilerPlate;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Files {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private Languages language  ;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    public void initializeBoilerplate() {
        if (this.content == null || this.content.isBlank()) {
            this.content = BoilerPlate.getBoilerplate(this.language);
        }
    }
}
