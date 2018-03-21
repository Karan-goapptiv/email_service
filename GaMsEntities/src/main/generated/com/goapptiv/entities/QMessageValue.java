package com.goapptiv.entities;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QMessageValue extends EntityPathBase<MessageValue> {

    private static final long serialVersionUID = 1162278550L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMessageValue messageValue = new QMessageValue("messageValue");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> message_id = createNumber("message_id", Long.class);

    public final StringPath value = createString("value");

    public QMessageValue(String variable) {
        this(MessageValue.class, forVariable(variable), INITS);
    }

    public QMessageValue(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMessageValue(PathMetadata metadata, PathInits inits) {
        this(MessageValue.class, metadata, inits);
    }

    public QMessageValue(Class<? extends MessageValue> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
    }
}
