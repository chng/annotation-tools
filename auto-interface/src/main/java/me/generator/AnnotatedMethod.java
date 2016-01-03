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
        //获取方法名
        methodName = annotatedMethodElement.getSimpleName().toString();
        //获取类名
        TypeElement parent = (TypeElement) annotatedMethodElement.getEnclosingElement();
        className = parent.getSimpleName().toString();
        //获取返回值类型
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
