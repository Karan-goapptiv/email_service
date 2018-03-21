package com.goapptiv.entities;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QMessageTemplate extends EntityPathBase<MessageTemplate> {

    private static final long serialVersionUID = 1162278550L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMessageTemplate messageTemplate = new QMessageTemplate("messageTemplates");

    public final StringPath name = createString("name");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath location = createString("location");

    public QMessageTemplate(String variable) {
        this(MessageTemplate.class, forVariable(variable), INITS);
    }

    public QMessageTemplate(Path<? extends MessageTemplate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMessageTemplate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMessageTemplate(PathMetadata metadata, PathInits inits) {
        this(MessageTemplate.class, metadata, inits);
    }

    public QMessageTemplate(Class<? extends MessageTemplate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
    }
}
