package org.eternity.call.calls;

import org.eternity.call.reader.CsvReader;
import org.eternity.call.reader.JsonReader;

import java.util.ArrayList;
import java.util.List;

public class CallCollector implements Collector {
    private Reader reader;

    public CallCollector(Reader reader) {
        this.reader = reader;
    }

    @Override
    public CallHistory collect(String phone) {
        CallHistory history = new CallHistory(phone);

        List<Call> calls = new ArrayList<>();

        if (reader instanceof CsvReader) {
            calls = reader.read();
            System.out.println("CSV 포맷을 처리했습니다.");
        } else if (reader instanceof JsonReader) {
            calls = reader.read();
            System.out.println("JSON 포맷을 처리했습니다.");
        } else {
            throw new IllegalArgumentException(Reader.class + "는 처리할 수 없습니다.");
        }

        for(Call call : calls) {
            if (call.from().equals(phone)) {
                history.append(call);
            }
        }

        return history;
    }
}
