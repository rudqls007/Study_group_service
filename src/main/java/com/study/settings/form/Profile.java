package com.study.settings.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class Profile {

    @Length(min = 1, max = 35)
    private String bio;
    @Length(min = 1, max = 50)
    private String url;
    @Length(min = 1, max = 50)
    private String occupation;
    @Length(min = 1, max = 50)
    private String location;

    private String profileImage;

}
