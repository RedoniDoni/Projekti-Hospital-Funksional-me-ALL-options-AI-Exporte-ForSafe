package com.example.HospitalManagment.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageObject<T> {
    private Integer page;

    private Integer size;

    private Integer totalPages;

    private Long totalSize;

    private Long date;

    private List<T> data;

    private T details;

//    private List<FileData> files;
}
