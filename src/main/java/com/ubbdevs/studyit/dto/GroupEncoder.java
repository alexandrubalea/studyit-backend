package com.ubbdevs.studyit.dto;

import org.springframework.stereotype.Component;

@Component
public class GroupEncoder {

    public int getDepartment(final String group) {
        //return Character.getNumericValue(group.charAt(0));
        return 9;
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

    public String encodeGroup(final int department, final int year, final int group, final int semiGroup) {
        return String.valueOf(department) +
                year +
                group +
                "/" +
                semiGroup;
    }

}
