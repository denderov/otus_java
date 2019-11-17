package ru.otus.hw04;

import org.objectweb.asm.*;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.objectweb.asm.Opcodes.H_INVOKESTATIC;

public class TestLoggingMethodVisitor extends MethodVisitor {

    private boolean isAnnotationPresent = false;
    private String methodName;
    private String descriptor;

    TestLoggingMethodVisitor(int api, MethodVisitor mv, String methodName, String descriptor) {

        super(api,mv);

        this.methodName = methodName;
        this.descriptor = descriptor;

    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {

        if (descriptor.equals("Lru/otus/hw04/Log;")) {
            isAnnotationPresent = true;
        }

        return super.visitAnnotation(descriptor, visible);

    }

    public void visitCode() {
        super.visitCode();

        if (isAnnotationPresent) {
            Handle handle = new Handle(
                    H_INVOKESTATIC,
                    Type.getInternalName(java.lang.invoke.StringConcatFactory.class),
                    "makeConcatWithConstants",
                    MethodType.methodType(CallSite.class, MethodHandles.Lookup.class, String.class, MethodType.class, String.class, Object[].class).toMethodDescriptorString(),
                    false);

            Type[] types = Type.getArgumentTypes(descriptor);

            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");

            for (int i = 0; i < types.length; i++) {
                mv.visitVarInsn(types[i].getOpcode(Opcodes.ILOAD), i + 1);
            }
            String listOfArguments = "("+Arrays.stream(types).map(Type::getDescriptor).collect(Collectors.joining(""))+")"+"Ljava/lang/String;";
            String logText = "executed method: "+methodName+", param: "+Arrays.stream(types).map(type -> "\u0001").collect(Collectors.joining(", "));

            mv.visitInvokeDynamicInsn("makeConcatWithConstants", listOfArguments, handle, logText);

            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

        }
    }

}
