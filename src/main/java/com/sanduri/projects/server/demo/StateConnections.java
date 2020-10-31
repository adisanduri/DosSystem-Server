package com.sanduri.projects.server.demo;

import java.util.*;

public class StateConnections {

    private static StateConnections instance = null;
    public static HashMap<Integer, PriorityQueue<Integer>> hmStateConnectionPerClient;

    public StateConnections() {
        initClients();
    }

    public static void initClients() {
        hmStateConnectionPerClient= new HashMap<>(Application.nClients);
        resetAll();
    }

    public static void resetAll() {
        for (int i = 1; i<= Application.nClients; i++) {
            hmStateConnectionPerClient.put(i, new PriorityQueue());
        }
    }

    public static StateConnections getInstance() {
        if (instance == null) {
            instance = new StateConnections();
        }
        return instance;
    }
}
