package io.github.optijava.opt_carpet_addition;

import com.google.gson.Gson;
import io.github.optijava.opt_carpet_addition.utils.config_bean.TpLimitConfigBean;

public class Main {

    public static void main(String[] args) {
        TpLimitConfigBean bean = new TpLimitConfigBean();
        bean.TpBlacklist.add("bot");
        System.out.println(new Gson().toJson(bean));
    }
}
