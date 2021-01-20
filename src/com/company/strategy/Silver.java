package com.company.strategy;

import com.company.CodeGenerator;

public class Silver extends Strategy {
    private final CodeGenerator generator = new CodeGenerator();

    @Override
    public String getStatus(String id) {
        return generator.makeCode("Silver-" + id);
    }
}
