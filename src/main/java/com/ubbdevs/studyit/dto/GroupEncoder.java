package com.ubbdevs.studyit.dto;

import org.springframework.stereotype.Component;

@Component
public class GroupEncoder {

    public int getDepartmentCode(final int group) {
        return getDepartmentCode(Integer.toString(group));
    }

    public int getDepartmentCode(final String group) {
        return Character.getNumericValue(group.charAt(0));
    }

    public int getYear(final int group) {
        return getYear(Integer.toString(group));
    }

    public int getYear(final String group) {
        return Character.getNumericValue(group.charAt(1));
    }

    public int getGroup(final int group) {
        return getGroup(Integer.toString(group));
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
