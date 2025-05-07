package org.eternity.call.reader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.eternity.call.calls.AbstractReader;
import org.eternity.call.calls.Call;
import org.eternity.call.calls.TimeInterval;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class JsonReader extends AbstractReader {
    public JsonReader(String path) {
        super(path);
    }

    @Override
    protected List<Call> parse(List<String> lines) {
        CallHistoryRecord history = parseJson(lines);
        return history.calls().stream().map(call -> new Call(call.from, call.to, TimeInterval.of(call.start, call.end))).collect(Collectors.toList());
    }

    private CallHistoryRecord parseJson(List<String> lines) {
        try {
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
            String json = lines.stream().collect(Collectors.joining());
            JsonNode node = mapper.readTree(json.getBytes());

            return mapper.treeToValue(node, CallHistoryRecord.class);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    record CallHistoryRecord(List<CallRecord> calls) {
        record CallRecord(String from, String to, LocalDateTime start, LocalDateTime end) {}
    }
}
