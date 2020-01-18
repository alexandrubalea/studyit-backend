package com.ubbdevs.studyit.model;

import lombok.*;

import javax.persistence.Embeddable;


@Getter
@Setter
@Builder
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    private int departmentCode;
    private int year;
    private int groupNumber;
    private int semiGroup;

    public boolean isOnlyGroup() {
        return semiGroup == 0;
    }

    public boolean withSemigroupBelongsToGroup(final Group other) {
        return this.departmentCode == other.getDepartmentCode() && this.year == other.getYear() &&
                this.groupNumber == other.getGroupNumber();
    }

}
