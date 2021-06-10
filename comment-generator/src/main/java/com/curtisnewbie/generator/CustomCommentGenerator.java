package com.curtisnewbie.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.util.Optional;

/**
 * @author yongjie.zhuang
 */
public class CustomCommentGenerator extends DefaultCommentGenerator {

    public CustomCommentGenerator() {

    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        // do nothing
    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        // do nothing
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        Optional<String> opt = buildJavaDoc(introspectedTable.getRemarks());
        if (opt.isPresent())
            topLevelClass.addJavaDocLine(opt.get());
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        Optional<String> opt = buildJavaDoc(introspectedTable.getRemarks());
        if (opt.isPresent())
            innerClass.addJavaDocLine(opt.get());
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        Optional<String> opt = buildJavaDoc(introspectedColumn.getRemarks());
        if (opt.isPresent())
            field.addJavaDocLine(opt.get());
    }

    private static Optional<String> buildJavaDoc(String remarks) {
        if (remarks == null || remarks.trim().isEmpty()) {
            return Optional.empty();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("/** ");
        sb.append(remarks);
        sb.append(" */");
        return Optional.of(sb.toString());
    }
}
