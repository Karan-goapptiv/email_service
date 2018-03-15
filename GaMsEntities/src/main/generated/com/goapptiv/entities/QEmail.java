package com.goapptiv.entities;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QHeadquarter is a Querydsl query type for Headquarter
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmail extends EntityPathBase<Email> {

    private static final long serialVersionUID = 1162278550L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmail email = new QEmail("email");

    public final StringPath subject = createString("subject");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath data = createString("data");

    public final QTemplate templateId;

    public final EnumPath<com.goapptiv.entities.enums.Status> status = createEnum("status",
            com.goapptiv.entities.enums.Status.class);

    public QEmail(String variable) {
        this(Email.class, forVariable(variable), INITS);
    }

    public QEmail(Path<? extends Email> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmail(PathMetadata metadata, PathInits inits) {
        this(Email.class, metadata, inits);
    }

    public QEmail(Class<? extends Email> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.templateId = inits.isInitialized("templateId") ? new QTemplate(forProperty("templateId")) : null;

    }

}

