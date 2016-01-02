package me.generator;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

/**
 * Created by OurEDA on 2016/1/1.
 */
public class AnnotatedClass {

    private String className;
    private String packageName;
    private List<AnnotatedMethod> methods = Lists.newLinkedList();

    public AnnotatedClass(String packageName, String className) {
        this.className = className;
        this.packageName = packageName;
    }

    /**
     * 生成本类的接口的java文件
     * @param filer
     */
    public void generateCode(Filer filer) {
        // 接口名：I+ClassName
        // 使用Javapoet生成代码。先定义构造器
        TypeSpec.Builder interfaceBuilder = TypeSpec.interfaceBuilder("I" + className)
                .addModifiers(Modifier.PUBLIC);

        // 对这个被注解的类的每个被注解的方法，将方法的名字、返回值、参数列表，都加到构造器中
        for (AnnotatedMethod method : methods) {
            //方法名，public，返回值类型
            final String methodName = method.getMethodName();
            final TypeMirror returnType = method.getReturnType();
            final List<? extends VariableElement> parameters = method.getParameters();

            MethodSpec.Builder methodBuilder =
                    MethodSpec.methodBuilder(methodName)
                            .addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC)
                            .returns(TypeName.get(returnType));

            //参数列表
            for (VariableElement e : parameters) {
                methodBuilder.addParameter(TypeName.get(e.asType()), "");
            }
            interfaceBuilder.addMethod(methodBuilder.build());
        }
        // 写入文件
        JavaFile javaFile = JavaFile.builder(packageName, interfaceBuilder.build()).build();
        try {
            javaFile.writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 向类中添加方法
    public void addMethod(AnnotatedMethod method) {
        methods.add(method);
    }
}