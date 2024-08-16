package com.rajat.springsecurity.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class Student {

    private Integer id;
    private String name;
    private Integer marks;
}
