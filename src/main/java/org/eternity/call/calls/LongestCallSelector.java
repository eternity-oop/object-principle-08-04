package org.eternity.call.calls;

import java.util.Optional;

import static java.util.Comparator.comparing;

public class LongestCallSelector {
    public Optional<Call> select(String phone, Collector collector) {
        return collector.collect(phone).calls().stream().max(comparing(Call::duration));
    }
}
