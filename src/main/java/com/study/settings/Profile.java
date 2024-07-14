package com.study.settings;

import com.study.domain.Account;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Lob;

@Data
@NoArgsConstructor
public class Profile {

    @Length(min = 1, max = 35)
    private String bio;
    @Length(min = 1, max = 50)
    private String url;
    @Length(min = 1, max = 50)
    private String occupation;
    @Length(min = 1, max = 50)
    private String location;

    public Profile(Account account) {
        this.bio = account.getBio();
        this.url = account.getUrl();
        this.occupation = account.getOccupation();
        this.location = account.getLocation();
    }
}
