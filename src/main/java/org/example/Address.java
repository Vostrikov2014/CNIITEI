package org.example;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Address {
    private final int objectId;
    private final String name;
    private final String typeName;

    public String getDescription() {
        return typeName + " " + name;
    }
}
