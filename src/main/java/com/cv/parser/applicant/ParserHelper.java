package com.cv.parser.applicant;

import com.cv.parser.RegEx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserHelper {
    private RegEx[] sectionRegex;

    public ParserHelper() {
        this.sectionRegex = new RegEx[]{
                RegEx.OBJECTIVE,
                RegEx.EDUCATION,
                RegEx.EXPERIENCE,
                RegEx.SKILLS,
                RegEx.LANGUAGE,
                RegEx.INTEREST,
                RegEx.MEMBERSHIP,
                RegEx.ADDITIONAL
        };
    }

    /**
     * Returns a single index given a regular expression for section heading.
     * @param regEx one of the regex in sectionRegex
     * @param texts the texts to match the regex
     * @return the index of the first occurrence of the matched regex (see matcher.group)
     */
    public int getIndexOfThisSection(RegEx regEx, String texts) {
        List<Integer> indexOfThisSection = new ArrayList<>();
        storeSectionIndexes(texts, indexOfThisSection, regEx);
        if (!indexOfThisSection.isEmpty()) {
            return indexOfThisSection.get(0);
        }
        return -1;
    }

    private void storeSectionIndexes(String line, List<Integer> indexOfThisSection, RegEx r) {
        Pattern pattern = Pattern.compile(r.toString(), Pattern.MULTILINE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            // run through each section regex and store its index
            indexOfThisSection.add(matcher.start());
        }
    }

    List<Integer> getAllSectionIndexes(String content) {
        List<Integer> indexesOfSection = new ArrayList<>();
        for (RegEx r : sectionRegex) {
            storeSectionIndexes(content, indexesOfSection, r);
        }
        Collections.sort(indexesOfSection);
        return indexesOfSection;
    }

    public List<Integer> getSectionIndexesExcludeOne(RegEx regEx, String line) {
        List<Integer> indexesOfSection = new ArrayList<>();
        for (RegEx r : sectionRegex) {
            if (!r.equals(regEx)) {
                storeSectionIndexes(line, indexesOfSection, r);
            }
        }
        Collections.sort(indexesOfSection);
        return indexesOfSection;
    }

    String getSectionContent(int sectionIndex, List<Integer> sectionIndexes, String content) {
        for (int i = 0; i < sectionIndexes.size(); i++) {
            // if sectionIndex is the last one, then no need for end index in substring
            if (sectionIndexes.get(i) == sectionIndex) {
                if (i == sectionIndexes.size() - 1) {
                    return content.substring(sectionIndex);
                } else {
                    // otherwise, the next index is the index of the following section
                    // i.e. education starts at index 60
                    // work experience starts at index 200
                    // for education content := content.substring(60, 200)
                    // for work experience content := content.substring(200, {startIndexOfOtherSection})
                    return content.substring(sectionIndex, sectionIndexes.get(i + 1));
                }
            }
        }
        return null;
    }

}
