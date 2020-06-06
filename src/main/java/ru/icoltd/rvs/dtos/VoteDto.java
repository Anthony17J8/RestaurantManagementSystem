package ru.icoltd.rvs.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.icoltd.rvs.entity.User;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoteDto {

    private Long id;

    private MenuDto menu;

    private User user;

    @NotNull
    private LocalDateTime dateTime;
}
