package com.goapptiv.entities;

import com.goapptiv.entities.enums.Status;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmailValue extends EntityPathBase<EmailValue> {

    private static final long serialVersionUID = 1162278550L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmailValue emailValue = new QEmailValue("emailValue");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> email_id = createNumber("email_id", Long.class);

    public final StringPath value = createString("value");

    public QEmailValue(String variable) {
        this(EmailValue.class, forVariable(variable), INITS);
    }

    public final EnumPath<com.goapptiv.entities.enums.Type> type = createEnum("type",
            com.goapptiv.entities.enums.Type.class);

    public QEmailValue(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmailValue(PathMetadata metadata, PathInits inits) {
        this(EmailValue.class, metadata, inits);
    }

    public QEmailValue(Class<? extends EmailValue> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
    }
}
