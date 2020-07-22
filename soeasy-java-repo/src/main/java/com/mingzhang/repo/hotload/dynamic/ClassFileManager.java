package com.mingzhang.repo.hotload.dynamic;

import java.io.IOException;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
 
/**
 * 
 * @author d
 *
 */
public class ClassFileManager extends
        ForwardingJavaFileManager<StandardJavaFileManager> {
    public JavaClassObject getJavaClassObject() {
        return jclassObject;
    }
 
    private JavaClassObject jclassObject;
 
 
    public ClassFileManager(StandardJavaFileManager standardManager) {
        super(standardManager);
    }
 
 
    @Override
    public JavaFileObject getJavaFileForOutput(Location location,
        String className, JavaFileObject.Kind kind, FileObject sibling)
            throws IOException {
            jclassObject = new JavaClassObject(className, kind);
        return jclassObject;
    }
}