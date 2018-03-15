package com.goapptiv.entities.enums;

public enum Status {

    PENDING("pending"),
    RUNNING("running"),
    SENT("sent"),
    FAILED("failed");

    private String status;

    /**
     * Status Constructor
     *
     * @param status (required) string status
     */
    Status(String status) {
        this.status = status;
    }

    /**
     * Get string status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Converting string to Enum Status
     *
     * @param status (required) string to be converted
     * @return Status enum
     */
    public static Status fromStatus(String status) {
        for (Status s : Status.values()) {
            if (s.getStatus().equals(status)) {
                return s;
            }
        }
        throw new UnsupportedOperationException(
                "The code " + status + " is not supported!");
    }
}
