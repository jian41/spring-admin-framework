package io.admin.framework.data.id;

import io.admin.framework.data.id.impl.PrefixedSequenceIdGenerator;
import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@IdGeneratorType(value = PrefixedSequenceIdGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface GeneratePrefixedSequenceId {

    String prefix();

}
