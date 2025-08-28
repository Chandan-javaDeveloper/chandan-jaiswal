package com.jne.command_orchestrator.dispatch;

import com.jne.command_orchestrator.command.DeviceCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
@Service
public class CommandDispatcher {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    public void distributeCommands(List<DeviceCommand> commands) {
        List<ServiceInstance> instances = discoveryClient.getInstances("device-service");
        if (instances.isEmpty()) throw new IllegalStateException("No device-service instances found");

        List<List<DeviceCommand>> partitions = partition(commands, instances.size());

        for (int i = 0; i < instances.size(); i++) {
            URI uri = instances.get(i).getUri();
            List<DeviceCommand> chunk = partitions.get(i);
            restTemplate.postForObject(uri + "/execute", chunk, Void.class);
        }
    }

    private List<List<DeviceCommand>> partition(List<DeviceCommand> commands, int parts) {
        List<List<DeviceCommand>> partitions = new ArrayList<>();
        int chunkSize = (int) Math.ceil((double) commands.size() / parts);

        for (int i = 0; i < commands.size(); i += chunkSize) {
            partitions.add(commands.subList(i, Math.min(i + chunkSize, commands.size())));
        }
        return partitions;
    }
}

