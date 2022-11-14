package io.github.optijava.opt_carpet_addition.utils.config_bean;

import java.util.ArrayList;
import java.util.List;

public class TpLimitConfigBean {
    public final List<String> tpWhitelist = new ArrayList<>();
    public final List<String> tpBlacklist = new ArrayList<>();
    public final List<String> tphereWhitelist = new ArrayList<>();
    public final List<String> tphereBlacklist = new ArrayList<>();
}
