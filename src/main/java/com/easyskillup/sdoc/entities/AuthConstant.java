package com.easyskillup.sdoc.entities;

public class AuthConstant {
    public final static String ROLE_PREFIX = "ROLE_";
    public final static String AUTH_DELIMITER = ",";
    public final static String USER_AUTHORITIES = "document:create,document:read,document:update,document:delete";
    public final static String ADMIN_AUTHORITIES =
            "document:create,document:read,document:update,document:delete,user:create,user:read,user:update";
    public final static String SUPER_ADMIN_AUTHORITIES =
            "user:delete,user:create,user:read,user:update,document:create,document:read,document:update,document:delete";
    public final static String MANAGER_AUTHORITIES = "document:create,document:read,document:update,document:delete";
}
