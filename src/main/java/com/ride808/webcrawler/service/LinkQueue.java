package com.ride808.webcrawler.service;

import org.springframework.stereotype.Service;

import java.util.LinkedList;

/**
 * LinkQueue for providing queue funtionality.  Queues are utilized by the PageCrawler for managing the links that have
 * not yet been processed
 *
 * @Author Ben Somogyi <benjamin.somogyi@gmail.com> 10/3/18
 */

@Service
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

