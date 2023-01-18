package de.medieninformatik.common;

import java.io.Serializable;

public record Subfield(
        Integer id,
        String name
) implements Serializable {}
