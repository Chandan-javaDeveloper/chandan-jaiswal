package com.jne.command_orchestrator.controller;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jne.command_orchestrator.command.DeviceCommand;
import com.jne.command_orchestrator.dispatch.CommandDispatcher;

@RestController
@RequestMapping("/commands")
public class CommandController {

    @Autowired
    private CommandDispatcher dispatcher;

    @PostMapping("/distribute")
    public ResponseEntity<String> distribute(@RequestBody List<DeviceCommand> commands) {
        dispatcher.distributeCommands(commands);
        return ResponseEntity.ok("Commands dispatched");
    }
    
    
    @PostMapping("/execute")
    public void executeCommands(@RequestBody List<DeviceCommand> commandList) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (DeviceCommand cmd : commandList) {
            executor.submit(() -> {
                System.out.println("Processing: " + cmd.getCommandType() + " for device " + cmd.getDeviceId());
            });
        }

        executor.shutdown();
    }
}
