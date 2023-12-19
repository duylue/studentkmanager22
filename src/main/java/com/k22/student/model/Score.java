package com.k22.student.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Score {
    private int scid;
    private float score;
    private int typeId;
    private int sjId;
    private String typeName;
    private String sjName;

}
