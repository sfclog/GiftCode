package me.sfclog.giftcode.util;

import java.util.List;

public class BuildInfo {


    public static String build(List<String> s) {
        StringBuilder build = new StringBuilder();
        for(String list : s) {
            build.append(list).append("\n");
        }
        return build.substring(0, build.length() - 1);
    }

}
