package ru.icoltd.rvs.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DishDto implements Serializable {

    private Long id;

    @NotBlank
    private String description;

    @PositiveOrZero
    private double price;

    private MenuDto menu;
}
