package com.wfh.sp21.lms.model.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "wfh_file")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;
    private String fileName;
    private byte[] fileData;
}
