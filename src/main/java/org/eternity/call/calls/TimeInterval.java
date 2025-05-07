package org.eternity.call.calls;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeInterval {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public static TimeInterval of(LocalDateTime start, LocalDateTime end) {
        return new TimeInterval(start, end);
    }

    private TimeInterval(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public Duration duration() {
        return Duration.between(start, end);
    }
}
