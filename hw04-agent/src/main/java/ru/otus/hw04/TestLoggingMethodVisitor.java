package ru.otus.hw04;

import org.objectweb.asm.*;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import static org.objectweb.asm.Opcodes.H_INVOKESTATIC;

public class TestLoggingMethodVisitor extends MethodVisitor {

    private boolean isAnnotationPresent = false;
    private String methodName;
    private String descriptor;

    TestLoggingMethodVisitor(int api, MethodVisitor mv, String methodName, String descriptor) {

        super(api, mv);

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

            StringBuilder listOfArguments = new StringBuilder("(");

            StringBuilder logText = new StringBuilder("executed method: ");

            int types_count = types.length;

            if (types_count == 0) {
                logText.append(methodName).append(", no params");
            } else if (types_count == 1) {
                logText.append(methodName).append(", param: ");
            } else {
                logText.append(methodName).append(", params: ");
            }

            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");

            for (int i = 0; i < types_count; i++) {

                mv.visitVarInsn(types[i].getOpcode(Opcodes.ILOAD), i + 1);

                listOfArguments.append(types[i].getDescriptor());

                if (i > 0) {
                    logText.append(", ");
                }
                logText.append("\u0001");

            }

            listOfArguments.append(")Ljava/lang/String;");
            logText.append(")Ljava/lang/String;");

            mv.visitInvokeDynamicInsn("makeConcatWithConstants", listOfArguments.toString(), handle, logText.toString());

            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

        }
    }

}
