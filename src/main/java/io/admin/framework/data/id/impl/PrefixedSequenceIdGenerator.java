// File: com.example.generator.PrefixedSequenceIdGenerator.java
package io.admin.framework.data.id.impl;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

/**
 * 基于 SequenceStyleGenerator (TableGenerator的Hibernate增强版) 的自定义生成器。
 * 格式为: [自定义前缀] + [序列值]
 */
public class PrefixedSequenceIdGenerator extends SequenceStyleGenerator {

    // 默认的表名，用于存储所有序列值
    public static final String TABLE_NAME = "sys_sequence_ids";


    public static final String VALUE_PREFIX_PARAMETER = "valuePrefix";
    private String prefix; // 用于存储从 @GenericGenerator 传入的前缀


    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry)
            throws HibernateException {

        // 1. 读取自定义的前缀参数
        prefix = ConfigurationHelper.getString(
                VALUE_PREFIX_PARAMETER,
                params,
                "ENT" // 默认前缀
        );
        String entityName = params.getProperty(JPA_ENTITY_NAME);

        params.put("optimizer", "none");
        params.put("increment_size", "1");



        params.put("sequence_table", TABLE_NAME); // 表名
        params.put("segment_column_name", "seq_name"); // 序列的列名
        params.put("segment_value", entityName); // 序列的值
        params.put("value_column_name", "next_val");

        // 调用父类的 configure 方法完成底层配置
        super.configure(type, params, serviceRegistry);
    }

    /**
     * 覆盖 generate 方法，对父类生成的序列值进行包装。
     */
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object)
            throws HibernateException {

        // 1. 调用父类方法，获取纯净的序列号 (Long 类型)
        Long sequenceValue = (Long) super.generate(session, object);

        // 2. 自定义 ID 格式：前缀 + 序列号 (左侧补零)
        return prefix + String.format("%08d", sequenceValue);
    }

}
