package com.sb.php.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CovidDTO {

    private LocalDate dateReported;

    private String countryCode;

    private String country;

    private String whoRegion;

    private int newCases;

    private int cumulativeCases;

    private int newDeaths;

    private int cumulativeDeaths;
}
