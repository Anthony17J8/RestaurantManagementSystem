package ru.icoltd.rvs.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Builder
    public Role(Integer id, String name, Set<User> users) {
        super(id);
        this.name = name;
        this.users = users;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(name).build();
    }
}
