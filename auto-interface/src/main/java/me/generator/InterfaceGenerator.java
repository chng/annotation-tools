package me.generator;

import com.google.common.collect.Maps;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static javax.tools.Diagnostic.Kind;

/**
 * Created by OurEDA on 2016/1/1.
 */
@SupportedAnnotationTypes({"me.generator.GenerateInterface"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class InterfaceGenerator extends AbstractProcessor {

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
        System.err.println(msg);

        Map<String, AnnotatedClass> classMap = Maps.newHashMap();

        // 对每个扫描到的被SupportedAnnotationType注解到的元素，都做处理
        final Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(GenerateInterface.class);
        for(Element e: elements) {

            if(e.getKind() != ElementKind.METHOD) {
                messager.printMessage(Kind.ERROR, "错啦");
                return false;
            }
            // 获取被注解的方法元数据
            ExecutableElement element = (ExecutableElement)e;
            // 将解析后的语法元素放置在自定义的数据结构中
            AnnotatedMethod method = new AnnotatedMethod(element);
            String className = method.getClassName();
            // 获取包信息
            PackageElement pkg = elementUtils.getPackageOf(element);

            // 如果扫描到类className的第一个被注解方法，则加到map中
            final AnnotatedClass clazz = classMap.get(className);
            if( null == clazz) {
                final AnnotatedClass newClazz = new AnnotatedClass(pkg.getSimpleName().toString(), className);
                newClazz.addMethod(method);
                classMap.put(className, newClazz);
            } else {
                clazz.addMethod(method);
            }
        }

        //代码生成。为每个被注解的类生成接口。
        for (AnnotatedClass myclass : classMap.values()) {
            myclass.generateCode(filer);
        }

        return false;
    }
}
