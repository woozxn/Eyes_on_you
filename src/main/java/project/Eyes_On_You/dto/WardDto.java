package project.Eyes_On_You.dto;

import lombok.*;
import project.Eyes_On_You.domain.entity.Ward;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class WardDto {
    private Long id;
    private String name;
    private String phoneNum;
    private LocalDateTime lastSeen;
    private String description;
    private String fileLoc;
    private String fileName;
    private boolean detected;

    public Ward toEntity(){
        Ward build= Ward.builder()
                .id(id)
                .name(name)
                .phoneNum(phoneNum)
                .description(description)
                .lastSeen(lastSeen)
                .fileLoc(fileLoc)
                .fileName(fileName)
                .detected(detected)
                .build();
        return build;
    }

    @Builder
    public WardDto(Long id, String name, String phoneNum, LocalDateTime lastSeen, String description, String fileLoc, String fileName, boolean detected) {
        this.id = id;
        this.name = name;
        this.phoneNum = phoneNum;
        this.lastSeen = lastSeen;
        this.description = description;
        this.fileLoc = fileLoc;
        this.fileName = fileName;
        this.detected = detected;
    }
}
