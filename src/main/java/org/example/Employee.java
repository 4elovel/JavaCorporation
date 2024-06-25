package org.example;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String position;
}
