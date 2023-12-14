package com.k22.student.model;

import jakarta.servlet.http.PushBuilder;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    private int sid;
    private String sname;
    private int cid;

}
