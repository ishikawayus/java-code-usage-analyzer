package com.example.codeusageanalyzer.visitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.example.codeusageanalyzer.entity.CalleeMethod;
import com.example.codeusageanalyzer.entity.MethodDefinition;

import lombok.Getter;

@Getter
public class CalleeClassVisitor extends ClassVisitor {

    private String name;

    private String superName;

    private List<String> interfaces = new ArrayList<>();

    private final List<MethodDefinition> methodDefinitions = new ArrayList<>();

    public CalleeClassVisitor() {
        super(Opcodes.ASM5);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.name = name;
        this.superName = superName;
        this.interfaces = interfaces != null ? Arrays.asList(interfaces) : Collections.emptyList();
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
            String[] exceptions) {
        List<CalleeMethod> calleeMethods = new ArrayList<>();
        methodDefinitions
                .add(MethodDefinition.builder().name(name).descriptor(descriptor).calleeMethods(calleeMethods).build());
        return new CalleeMethodVisitor(calleeMethods);
    }
}
