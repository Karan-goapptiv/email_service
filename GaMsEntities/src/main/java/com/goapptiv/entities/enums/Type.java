package com.goapptiv.entities.enums;

public enum Type {

    TO("to"),
    CC("cc"),
    BCC("bcc");

    private String type;

    /**
     * Type Constructor
     *
     * @param type (required) string type
     */
    Type(String type) {
        this.type = type;
    }

    /**
     * Get string type
     */
    public String getType() {
        return type;
    }

    /**
     * Converting string to Enum Type
     *
     * @param type (required) string to be converted
     * @return Type enum
     */
    public static Type fromType(String type) {
        for (Type s : Type.values()) {
            if (s.getType().equals(type)) {
                return s;
            }
        }
        throw new UnsupportedOperationException(
                "The code " + type + " is not supported!");
    }
}
