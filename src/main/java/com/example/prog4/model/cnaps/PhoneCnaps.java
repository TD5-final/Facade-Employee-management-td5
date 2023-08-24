package com.example.prog4.model.cnaps;

import java.io.Serializable;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PhoneCnaps implements Serializable {
    private String id;
    private String value;
    private String countryCode;
}
