package com.urena.calculator.operation;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class OperatingController {

    public static List<Operating> getOperatings(String text) {
        return Pattern.compile(OperatorController.SINGLEOPERATING).matcher(text).results()
                .map(matchResult -> checkSign(matchResult,text))
                .map(OperatingController::getOperating)
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public static Operating getOperating(String operatings) {
        Operating operating = parseOperating(operatings);
        if(operating!=null)
            return new Operating(SingleOperatorController.parseOperators(operating.getNumber(),operatings));
        return null;
    }

    private static String checkSign(MatchResult matchResult, String text) {
        if(text.charAt(matchResult.start())=='-' && matchResult.start()!= 0){
            if(text.charAt(matchResult.start()-1)=='*'|| text.charAt(matchResult.start()-1)=='/')
                return matchResult.group();
            else return text.substring(matchResult.start()+1,matchResult.end());
        }
        return matchResult.group();
    }

    private static Operating parseOperating(String text){
        Matcher matcher = Pattern.compile(OperatorController.DOUBLE).matcher(text);
        if(matcher.find()){
            return new Operating(Double.parseDouble(matcher.group()));
        }
        return null;
    }
}
