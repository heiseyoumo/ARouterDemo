package com.huifu.myapplication;


public interface CoreRouterQuery {
    String KEY_URL = "_url";
    String KEY_ROUTER_URL = "_router_url";
    String KEY_CLOSE_THIS = "closeThis"; //true or false
    String KEY_OPEN_WITH = "_openWith";
    String KEY_NEED_RESULT = "needResponse";  //true or false
    String KEY_HAS_TITLE = "hasTitle";  //true or false
    String KEY_TITLE_NAME = "titleName";
    String KEY_WEEX_JS_PATH = "js_path";
    String KEY_WEEX_PAGE_NAME = "page_name";

    String VALUE_OPEN_WITH_BLANK = "_blank";
    String VALUE_OPEN_WITH_SYS = "_sys";
    String VALUE_OPEN_WITH_KEEP = "_keep";
}
