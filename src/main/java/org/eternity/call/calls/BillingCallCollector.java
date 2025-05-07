package org.eternity.call.calls;

import java.time.Duration;

public class BillingCallCollector implements Collector {
    private CallCollector collector;

    public BillingCallCollector(CallCollector collector) {
        this.collector = collector;
    }

    @Override
    public CallHistory collect(String phone) {
        CallHistory history = collector.collect(phone);

        CallHistory result = new CallHistory(phone);

        for(Call call : history.calls()) {
            if (call.duration().compareTo(Duration.ofSeconds(10)) >= 0) {
                result.append(call);
            }
        }

        return result;
    }
}

