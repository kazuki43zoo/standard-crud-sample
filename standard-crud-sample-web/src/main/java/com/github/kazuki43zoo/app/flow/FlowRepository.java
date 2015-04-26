package com.github.kazuki43zoo.app.flow;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@lombok.extern.slf4j.Slf4j
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class FlowRepository {

    private final Map<String, Flow> flows = new ConcurrentHashMap<>();

    @Value("${flow.allowedMaxCount:50}")
    int allowedMaxCount;

    public int countAll() {
        if (log.isDebugEnabled()) {
            log.debug("countAll : {}", flows.size());
        }
        return flows.size();
    }

    public Flow findOne(String id) {
        Flow flow = flows.get(id);
        log.debug("findOne : {} : {}", id, flow);
        return flow;
    }

    public void save(Flow flow) {
        log.debug("save : {}", flow);
        if (allowedMaxCount <= countAll()) {
            throw new FlowOverflowException(flow);
        }
        flows.put(flow.getId(), flow);
    }

    public void delete(String id) {
        log.debug("delete : {}", id);
        Flow flow = findOne(id);
        if (flow != null) {
            flow.saveModel(null);
            flows.remove(id);
        }
    }

    public void deleteAll() {
        log.debug("deleteAll");
        flows.clear();
    }

}
