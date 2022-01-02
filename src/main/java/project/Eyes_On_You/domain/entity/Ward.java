package project.Eyes_On_You.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class) /* JPA에게 해당 Entity는 Auditiong 기능을 사용함을 알립니다. */
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String phoneNum;
    @Column
    private LocalDateTime lastSeen;
    @Column
    private String description;
    @Column(nullable = false)
    private String fileLoc;
    @Column(nullable = false)
    private String fileName;
    @Column(nullable = false, columnDefinition = "tinyint default 1")
    private boolean detected=true;

    @Builder
    public Ward(Long id, String name, String phoneNum, String description, LocalDateTime lastSeen,String fileLoc, String fileName, boolean detected) {
        this.id = id;
        this.name = name;
        this.phoneNum = phoneNum;
        this.lastSeen=lastSeen;
        this.description = description;
        this.fileLoc = fileLoc;
        this.fileName = fileName;
        this.detected = detected;
    }
}
