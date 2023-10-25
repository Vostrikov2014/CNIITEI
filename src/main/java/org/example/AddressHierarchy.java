package org.example;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class AddressHierarchy {
    private final int objectId;
    private final int parentId;
    private final Date startDate;
    private final Date endDate;
}
