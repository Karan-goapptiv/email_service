package com.goapptiv.entities.enums;

public enum Permission {

    CAN_CREATE_HEADQUARTER("CAN_CREATE_HEADQUARTER"),
    CAN_UPDATE_HEADQUARTER("CAN_UPDATE_HEADQUARTER"),
    CAN_DISABLE_HEADQUARTER("CAN_DISABLE_HEADQUARTER"),
    CAN_DELETE_HEADQUARTER("CAN_DELETE_HEADQUARTER"),
    CAN_VIEW_CHILDREN("CAN_VIEW_CHILDREN"),
    CAN_VIEW_PARENTS("CAN_VIEW_PARENTS"),
    CAN_VIEW_ALL("CAN_VIEW_ALL");

    private String permission;

    /**
     * Enum Permission Constructor
     *
     * @param permission (required) permission string
     */
    Permission(String permission) {
        this.permission = permission;
    }


    public String getPermission() {
        return permission;
    }

    /**
     * Converting string permission into enum value
     *
     * @param permission (required) string permission
     * @return permission Enum
     */
    public static Permission fromPermission(String permission) {
        for (Permission s : Permission.values()) {
            if (s.getPermission().equals(permission)) {
                return s;
            }
        }
        throw new UnsupportedOperationException(
                "The code " + permission + " is not supported!");
    }
}
