package ru.otus.hw04;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public class TestLoggingClassVisitor extends ClassVisitor {
    TestLoggingClassVisitor(int api, ClassWriter cw) {
        super(api,cw);
    }

    @Override
    public MethodVisitor visitMethod(
                                     int access,
                                     String name,
                                     String descriptor,
                                     String signature,
                                     String[] exceptions)
    {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        return new TestLoggingMethodVisitor(api,mv,name,descriptor);
    }
}
