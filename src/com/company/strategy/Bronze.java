package com.company.strategy;

import com.company.CodeGenerator;

public class Bronze extends Strategy {
    private final CodeGenerator generator = new CodeGenerator();

    @Override
    public String getStatus(String id) {
        return generator.makeCode("Bronze-" + id);
    }
}
