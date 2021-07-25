package com.wfh.sp21.lms.model.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "wfh_file")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileModule implements Serializable {
    @Id
    private Long fileId;
    private String fileName;
    @Lob
    private String fileData;
}
