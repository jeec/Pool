package com.jerry.moudule_3;

import com.google.auto.service.AutoService;
import com.jerry.annation_defination.MyAnnotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;


@AutoService(Processor.class)
//需要处理的注解 = getSupportedAnnotationTypes()方法
@SupportedAnnotationTypes({"com.jerry.annation_defination.MyAnnotation"})
//编译版本 = getSupportedSourceVersion()
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MyProcessor extends AbstractProcessor {
    private Messager mMessager;

    //存储Activity列表
    private List<String> activityList;

    //输出
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        mMessager = processingEnv.getMessager();
        activityList = new ArrayList<>();
        filer = processingEnv.getFiler();
        mMessager.printMessage(Diagnostic.Kind.NOTE,"====================");

        String content = processingEnv.getOptions().get("content1");
        mMessager.printMessage(Diagnostic.Kind.NOTE, content);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        mMessager.printMessage(Diagnostic.Kind.NOTE,">>> start process...");
        if (annotations.isEmpty()){
            return false;
        }
        activityList.clear();
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(MyAnnotation.class);
        for (Element element : elements) {
            TypeElement typeElement = (TypeElement) element;
            String fullName = typeElement.getQualifiedName().toString();
            mMessager.printMessage(Diagnostic.Kind.NOTE,fullName);
            activityList.add(fullName);
        }
        mMessager.printMessage(Diagnostic.Kind.NOTE,">>> end process...");
        return true;
    }

//    @Override
//    public Set<String> getSupportedAnnotationTypes() {
//        String name = TestAnnotation.class.getCanonicalName();
//        Set set = new HashSet();
//        set.add(name);
//        return set;
//    }
//
//    @Override
//    public SourceVersion getSupportedSourceVersion() {
//        return SourceVersion.latestSupported();
//    }
}