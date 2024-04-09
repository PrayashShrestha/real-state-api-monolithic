package miu.ea.realestateapimonolithic.common;

public class Constant {
    public static final String API_URL_v1_PREFIX = "/api/v1";

    public static final String SIGNUP_URL = API_URL_v1_PREFIX + "/signup";
    public static final String LOGIN_URL = API_URL_v1_PREFIX + "/login";

    public static final String USER_URL_PREFIX = API_URL_v1_PREFIX + "/users";
    public static final String PROPERTY_URL_PREFIX = API_URL_v1_PREFIX + "/properties";
    public static final String ADMIN_URL_PREFIX = API_URL_v1_PREFIX + "/admin";
    public static final String BUYER_URL_PREFIX = API_URL_v1_PREFIX + "/buyers";

    public static final String AGENT_URL_PREFIX = API_URL_v1_PREFIX + "/agents";
    public static final String SEARCH_URL_PREFIX = API_URL_v1_PREFIX + "/search";
    public static final String MESSAGE_URL_PREFIX = API_URL_v1_PREFIX + "/messages";
    public static final String AGENT_REPORT_URL_PREFIX = API_URL_v1_PREFIX + "/agentreports";

    public static final int MAX_LOGIN_FAILED_ATTEMPTS = 5;
    public static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000; // 24 hours
    public static final long TOKEN_EXPIRATION_DURATION = 3600000; // 1 hour
}
