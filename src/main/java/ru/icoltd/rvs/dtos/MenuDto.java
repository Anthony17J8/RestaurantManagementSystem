package ru.icoltd.rvs.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MenuDto implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private LocalDateTime date;

    private RestaurantDto restaurant;

    private Long votesAmount;
}
