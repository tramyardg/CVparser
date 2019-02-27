package com.cv.parser.applicant;

import com.cv.parser.RegEx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserHelper {

    public int getIndexOfThisSection(RegEx regEx, String fileContent) {
        RegEx[] sectionRegex = {
                RegEx.OBJECTIVE,
                RegEx.EDUCATION,
                RegEx.EXPERIENCE,
                RegEx.SKILLS,
                RegEx.LANGUAGE,
                RegEx.INTEREST,
                RegEx.MEMBERSHIP,
                RegEx.ADDITIONAL
        };
        List<Integer> indexOfThisSection = new ArrayList<>();
        for (RegEx r : sectionRegex) {
            if (r.equals(regEx)) {
                storeSectionIndexes(fileContent, indexOfThisSection, r);
            }
        }
        if (!indexOfThisSection.isEmpty()) {
            return indexOfThisSection.get(0);
        }
        return -1;
    }

    private void storeSectionIndexes(String line, List<Integer> indexOfThisSection, RegEx r) {
        Pattern pattern = Pattern.compile(r.toString(), Pattern.MULTILINE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            indexOfThisSection.add(matcher.start());
        }
    }

    List<Integer> getAllSectionIndexes(String content) {
        RegEx[] sectionRegex = {
                RegEx.OBJECTIVE,
                RegEx.EDUCATION,
                RegEx.EXPERIENCE,
                RegEx.SKILLS,
                RegEx.LANGUAGE,
                RegEx.INTEREST,
                RegEx.MEMBERSHIP,
                RegEx.ADDITIONAL
        };
        List<Integer> indexesOfSection = new ArrayList<>();
        for (RegEx r : sectionRegex) {
            storeSectionIndexes(content, indexesOfSection, r);
        }
        Collections.sort(indexesOfSection);
        return indexesOfSection;
    }

    public List<Integer> getSectionIndexesExcludeOne(RegEx regEx, String line) {
        RegEx[] sectionRegex = {
                RegEx.OBJECTIVE,
                RegEx.EDUCATION,
                RegEx.EXPERIENCE,
                RegEx.SKILLS,
                RegEx.LANGUAGE,
                RegEx.INTEREST,
                RegEx.MEMBERSHIP,
                RegEx.ADDITIONAL
        };
        List<Integer> indexesOfSection = new ArrayList<>();
        for (RegEx r : sectionRegex) {
            if (!r.equals(regEx)) {
                storeSectionIndexes(line, indexesOfSection, r);
            }
        }
        Collections.sort(indexesOfSection);
        return indexesOfSection;
    }

    String getSectionContent(int sectionIndex, List<Integer> sectionIndexes, String content, int nextSectionIndex) {
        for (int index = 0; index < sectionIndexes.size(); index++) {
            if (sectionIndexes.get(index) == sectionIndex) {
                if (index == sectionIndexes.size() - 1) {
                    return content.substring(sectionIndex);
                } else {
                    nextSectionIndex = sectionIndexes.get(index + 1);
                    break;
                }
            }
        }
        return content.substring(sectionIndex, nextSectionIndex);
    }

}
