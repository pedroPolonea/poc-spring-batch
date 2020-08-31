package com.sb.php.domain;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private String name;
    private String lastName;
    private String age;
    private String email;
}
