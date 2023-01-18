package io.github.optijava.opt_carpet_addition.utils.config_bean;

import java.util.ArrayList;
import java.util.List;

public class CommandLoggerConfigBean {

    public boolean logAllCommand = false;

    public final List<String> LogCommandWhitelist = new ArrayList<>();

    public final List<String> LogCommandBlacklist = new ArrayList<>();

    public final List<String> LogCommandPrefixWhitelist = new ArrayList<>();

    public final List<String> LogCommandPrefixBlacklist = new ArrayList<>();
}
