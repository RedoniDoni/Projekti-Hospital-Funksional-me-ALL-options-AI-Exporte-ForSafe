package com.example.HospitalManagment.exception;

public enum ErrorCode {
    /* DB ERRORS */
    UNIQUE_FIELD_CONSTRAINT(1000, "unique.field.constraint"),
    REQUESTED_DATA_NOT_FOUND(1001, "requested.data.not.found"),
    EXISTED_EMAIL(1002, "existed.email"),
    EXISTED_NAME(1003, "existed.name"),

    /* REQUESTS ERRORS */
    BAD_REQUEST(2000, "bad.request"),
    DOMAIN_NAME_NOT_EXIST(2001, "domain.name.not.exist"),
    USER_IS_NOT_ACTIVATED(2002, "user.is.not.activated"),
    DOMAIN_EXIST(2003, "domain.exist"),
    INVALID_TOKEN(2004, "invalid.token"),
    WRONG_BASE64_FORMAT(2005,"wrong.base64.format"),
    CATEGORY_NOT_FOUND(2006,"category_not_found"),
    SUB_CATEGORY_NOT_FOUND(2007,"sub_category_not_found"),
    TICKET_NOT_FOUND(2008,"ticket_not_found"),
    COMMENT_NOT_FOUND(2009,"comment_not_found"),
    USER_NOT_FOUND(2010,"user_not_found"),

    /* AUTH ERRORS */
    UNAUTHORIZED(3000, "unauthorized"),
    ROLE_NOT_ALLOWED(3001, "role.not.allowed"),

    /* CUSTOM ERRORS */
    MISSING_TOKEN_APIKEY_TENANTID(4000, "missing.token.apikey.tenantid"); // Këtu është kodi i ri

    private final int code;
    private final String reasonPhrase;
    private final String errorPrefix = "errorCode";
    private final String titlePostfix = "title";

    ErrorCode(int code, String reasonPhrase) {
        this.code = code;
        this.reasonPhrase = reasonPhrase;
    }

    public int getCode() {
        return this.code;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    public String getMessageTitleKey() {
        return this.errorPrefix + "." + this.name() + "." + this.titlePostfix;
    }

    @Override
    public String toString() {
        return "Code: " + this.code + ". Title: " + this.getMessageTitleKey() + ".";
    }
}