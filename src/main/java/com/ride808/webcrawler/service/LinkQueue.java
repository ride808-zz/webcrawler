package com.ride808.webcrawler.service;

import java.util.LinkedList;
import java.util.Queue;

public class LinkQueue {
    LinkedList linkQueue;

    public LinkQueue() {
        linkQueue = new LinkedList();
    }


    public void getQueueSize() {
        linkQueue.size();
    }

    public boolean isEmpty() {
        return linkQueue.isEmpty();
    }

    public synchronized String pop() {
        if (!linkQueue.isEmpty()) {
            return (String) linkQueue.removeFirst();
        }
        return null;
    }

    public synchronized void push(String url) {
        linkQueue.add(url);
    }

    public synchronized void clear() {
        linkQueue.clear();
    }

}

