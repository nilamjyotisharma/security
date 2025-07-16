package com.security.security.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long classId;
    private String className;
    private Duration classDuration;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @OneToOne
    @JoinColumn(name = "teacherId", referencedColumnName = "userId")
    private AppUser teacher;
    private Boolean isActive;
}
