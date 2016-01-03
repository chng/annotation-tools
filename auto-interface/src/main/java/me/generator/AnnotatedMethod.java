package me.generator;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import java.util.List;

/**
 * Created by OurEDA on 2016/1/1.
 */
public class AnnotatedMethod {

    private String methodName;
    private String className;


    private TypeMirror returnType;
    private final List<? extends VariableElement> parameters;


    public AnnotatedMethod(ExecutableElement annotatedMethodElement) {
        //��ȡ������
        methodName = annotatedMethodElement.getSimpleName().toString();
        //��ȡ����
        TypeElement parent = (TypeElement) annotatedMethodElement.getEnclosingElement();
        className = parent.getSimpleName().toString();
        //��ȡ����ֵ����
        returnType = annotatedMethodElement.getReturnType();
        parameters = annotatedMethodElement.getParameters();
    }

    public String getMethodName() {
        return methodName;
    }

    public String getClassName() {
        return className;
    }

    public TypeMirror getReturnType() {
        return returnType;
    }

    public List<? extends VariableElement> getParameters() {
        return parameters;
    }
}
