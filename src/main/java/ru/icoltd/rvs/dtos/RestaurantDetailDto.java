package ru.icoltd.rvs.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RestaurantDetailDto implements Serializable {

    @NotNull
    private String country;

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    @Pattern(regexp = "^\\+*(?:[0-9] ?){6,14}[0-9]$")
    private String phoneNumber;

    @NotNull
    @Pattern(regexp = "^((https?|ftp|smtp):\\/\\/)?(www\\.)?[a-z0-9]+[-]?[a-z0-9]+\\.[a-z]+(\\/[a-zA-Z0-9#]+\\/?)*$")
    private String url;
}
