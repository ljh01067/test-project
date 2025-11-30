package org.example.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestVo {
    private Long id;
    private String name;
    private String contact;
    private String email;
    private String workScope;
    private LocalDate deadline;
    private String genre;
    private String referenceLink;
    private String note;

    private byte[] guideFile;
    private byte[] midiFile;
    private byte[] referenceFile;

    // 파일명 필드 추가
    private String guideFilename;
    private String midiFilename;
    private String referenceFilename;

    private LocalDateTime createdAt;
}
