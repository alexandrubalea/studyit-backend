package com.ubbdevs.studyit.dto;

import com.ubbdevs.studyit.model.Group;
import org.springframework.stereotype.Component;

@Component
public class GroupEncoder {

    public int getDepartmentCode(final String group) {
        return Character.getNumericValue(group.charAt(0));
    }

    public int getYear(final String group) {
        return Character.getNumericValue(group.charAt(1));
    }

    public int getGroup(final String group) {
        return Character.getNumericValue(group.charAt(2));
    }

    public int getSemiGroup(final String group) {
        return Character.getNumericValue(group.charAt(4));
    }

    public String fromGroupWithoutSemigroup(final Group group) {
        return String.valueOf(group.getDepartmentCode()) +
                group.getYear() +
                group.getGroupNumber();
    }

    public String fromGroup(final Group group) {
        return String.valueOf(group.getDepartmentCode()) +
                group.getYear() +
                group.getGroupNumber() +
                "/" +
                group.getSemiGroup();
    }
}
