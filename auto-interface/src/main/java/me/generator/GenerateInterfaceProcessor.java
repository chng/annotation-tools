package me.generator;

import com.google.common.collect.Maps;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.Map;
import java.util.Set;

import static javax.tools.Diagnostic.Kind;

/**
 * Created by OurEDA on 2016/1/1.
 */
@SupportedAnnotationTypes({"me.generator.GenerateInterface"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class GenerateInterfaceProcessor extends AbstractProcessor {

    Types typeUtils;
    Elements elementUtils;
    Filer filer;
    Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment env){
        System.err.println(this.getClass().getName()+" Initializing");
        super.init(env);
        elementUtils = env.getElementUtils();
        filer = env.getFiler();
        typeUtils = env.getTypeUtils();
        messager = env.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        String msg = this.getClass().getName()+" Processing";
        messager.printMessage(Kind.NOTE, msg);
        Map<String, AnnotatedClass> classMap = Maps.newHashMap();

        // ��ÿ��ɨ�赽�ı�SupportedAnnotationTypeע�⵽��Ԫ�أ���������
        final Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(GenerateInterface.class);
        for(Element e: elements) {
            // ��ȡ��ע��ķ���Ԫ����
            ExecutableElement element = (ExecutableElement)e;
            // ����������﷨Ԫ�ط������Զ�������ݽṹ��
            final AnnotatedMethod method = new AnnotatedMethod(element);
            String className = method.getClassName();
            // ��ȡ��·��
            String pkgName = elementUtils.getPackageOf(element).getQualifiedName().toString(); // Qualified��ȫ·����XX.XXX.YY SimpleNameֻ��YY

            messager.printMessage(Kind.NOTE, pkgName+" "+className+" "+method.getMethodName());

            // ���ɨ�赽��className�ĵ�һ����ע�ⷽ������ӵ�map��
            final AnnotatedClass clazz = classMap.get(className);
            if( null == clazz) {
                final AnnotatedClass newClazz = new AnnotatedClass(pkgName, className);
                newClazz.addMethod(method);
                classMap.put(className, newClazz);
            } else {
                clazz.addMethod(method);
            }
        }

        //�������ɡ�Ϊÿ����ע��������ɽӿڡ�
        for (AnnotatedClass clazz : classMap.values()) {
            clazz.generateCode(filer);
        }

        return true;
    }
}
