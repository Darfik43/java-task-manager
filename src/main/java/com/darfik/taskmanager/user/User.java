package com.darfik.taskmanager.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class User {

    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

}
