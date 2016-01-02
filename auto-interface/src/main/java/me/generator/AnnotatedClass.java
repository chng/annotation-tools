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
     * ���ɱ���Ľӿڵ�java�ļ�
     * @param filer
     */
    public void generateCode(Filer filer) {
        // �ӿ�����I+ClassName
        // ʹ��Javapoet���ɴ��롣�ȶ��幹����
        TypeSpec.Builder interfaceBuilder = TypeSpec.interfaceBuilder("I" + className)
                .addModifiers(Modifier.PUBLIC);

        // �������ע������ÿ����ע��ķ����������������֡�����ֵ�������б����ӵ���������
        for (AnnotatedMethod method : methods) {
            //��������public������ֵ����
            final String methodName = method.getMethodName();
            final TypeMirror returnType = method.getReturnType();
            final List<? extends VariableElement> parameters = method.getParameters();

            MethodSpec.Builder methodBuilder =
                    MethodSpec.methodBuilder(methodName)
                            .addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC)
                            .returns(TypeName.get(returnType));

            //�����б�
            for (VariableElement e : parameters) {
                methodBuilder.addParameter(TypeName.get(e.asType()), "");
            }
            interfaceBuilder.addMethod(methodBuilder.build());
        }
        // д���ļ�
        JavaFile javaFile = JavaFile.builder(packageName, interfaceBuilder.build()).build();
        try {
            javaFile.writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ��������ӷ���
    public void addMethod(AnnotatedMethod method) {
        methods.add(method);
    }
}