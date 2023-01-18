package de.medieninformatik.common;

import java.io.Serializable;

public record Author(
        Integer id,
        String firstName,
        String lastName
) implements Serializable {}
