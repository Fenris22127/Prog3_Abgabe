package de.medieninformatik.server.tempclient;

import java.util.List;

public record Filter(String textSearch, List<Integer> subfields) {
}
