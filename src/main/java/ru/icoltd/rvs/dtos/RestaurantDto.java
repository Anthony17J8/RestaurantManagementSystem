package ru.icoltd.rvs.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RestaurantDto implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @Valid
    private RestaurantDetailDto restaurantDetail;
}
