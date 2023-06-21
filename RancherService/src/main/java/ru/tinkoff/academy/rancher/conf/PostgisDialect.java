package ru.tinkoff.academy.rancher.conf;

import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.spatial.dialect.postgis.PostgisPG82Dialect;
import org.hibernate.type.StandardBasicTypes;

public class PostgisDialect extends PostgisPG82Dialect {
    public PostgisDialect() {
        super();
        registerFunction("ST_Area", new StandardSQLFunction("ST_Area", StandardBasicTypes.DOUBLE));
    }
}