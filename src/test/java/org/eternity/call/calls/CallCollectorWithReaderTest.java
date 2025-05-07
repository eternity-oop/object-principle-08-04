package org.eternity.call.calls;

import org.eternity.call.reader.CsvReader;
import org.eternity.call.reader.JsonReader;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CallCollectorWithReaderTest {
    public static Stream<Arguments> readers() {
        return Stream.of(
                Arguments.of(new CsvReader("calls.csv")),
                Arguments.of(new JsonReader("calls.json")));
    }

    @ParameterizedTest
    @MethodSource("readers")
    public void collect(Reader reader) {
        CallCollector callCollector = new CallCollector(reader);
        CallHistory history = callCollector.collect("010-1111-2222");
        assertThat(history.callDuration()).isEqualTo(Duration.ofSeconds(159));
    }
}
