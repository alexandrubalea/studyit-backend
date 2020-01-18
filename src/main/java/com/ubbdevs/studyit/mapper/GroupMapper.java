package com.ubbdevs.studyit.mapper;

import com.ubbdevs.studyit.dto.GroupEncoder;
import com.ubbdevs.studyit.model.Group;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GroupMapper {

    private final GroupEncoder groupEncoder;

    public Group dtoToModel(final String group) {
        final Group mappedGroup = Group.builder()
                .departmentCode(groupEncoder.getDepartmentCode(group))
                .year(groupEncoder.getYear(group))
                .groupNumber(groupEncoder.getGroup(group))
                .build();
        if (group.length() == 5)
            mappedGroup.setSemiGroup(groupEncoder.getSemiGroup(group));
        return mappedGroup;
    }

    public String modelToDto(final Group group) {
        return groupEncoder.fromGroup(group);
    }
}
