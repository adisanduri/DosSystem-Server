package com.sanduri.projects.server.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.PriorityQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
public class RestService {

    public static int index=1;
    public static ScheduledExecutorService ses =
            Executors.newScheduledThreadPool(Application.maxConnectionsPerClient * Application.nClients);

    @GetMapping("/")
    public HttpStatus getService(@RequestParam(name="clientId", required = true) int clientId) {
        System.out.println("got request from " + clientId);

        PriorityQueue<Integer> priorityQueue = StateConnections.getInstance().hmStateConnectionPerClient.get(clientId);
        if (priorityQueue.size() < Application.maxConnectionsPerClient) {

            priorityQueue.add(index++);
            ses.schedule(new Runnable() { public void run() {
                priorityQueue.poll();
            }}, 5000, TimeUnit.MILLISECONDS);

            return HttpStatus.OK;
        }
        else {
            return HttpStatus.SERVICE_UNAVAILABLE;
        }
    }

}
