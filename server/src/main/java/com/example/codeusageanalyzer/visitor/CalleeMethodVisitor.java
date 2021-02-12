package com.example.codeusageanalyzer.visitor;

import java.util.List;

import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.example.codeusageanalyzer.entity.CalleeMethod;

import lombok.Getter;

@Getter
public class CalleeMethodVisitor extends MethodVisitor {

    private final List<CalleeMethod> calleeMethods;

    public CalleeMethodVisitor(List<CalleeMethod> calleeMethods) {
        super(Opcodes.ASM5);
        this.calleeMethods = calleeMethods;
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
        if (bsmArgs.length == 3 && bsmArgs[1] instanceof Handle) {
            Handle handle = (Handle) bsmArgs[1];
            calleeMethods.add(CalleeMethod.builder().owner(handle.getOwner()).name(handle.getName())
                    .descriptor(handle.getDesc()).isInvokeDynamic(true).build());
        }
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        calleeMethods.add(
                CalleeMethod.builder().owner(owner).name(name).descriptor(descriptor).isInvokeDynamic(false).build());
    }
}
