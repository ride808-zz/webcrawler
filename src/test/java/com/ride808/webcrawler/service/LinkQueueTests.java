package com.ride808.webcrawler.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LinkQueueTests {

    @Test
    public void test_QueueOrdering_PopItemsAsExpected() {
        LinkQueue linkQueue = new LinkQueue();

        linkQueue.push("FirstElement");
        linkQueue.push("SecondElement");

        assertEquals("FirstElement", linkQueue.pop());
        assertEquals("SecondElement", linkQueue.pop());
    }
    @Test
    public void test_EmptyQueue_IsEmpty() {
        LinkQueue linkQueue = new LinkQueue();
        linkQueue.push("FirstElement");
        String item = linkQueue.pop();

        assertTrue(linkQueue.isEmpty());
    }

    @Test
    public void test_QueueofOne_HasSizeOfOne() {
        LinkQueue linkQueue = new LinkQueue();
        linkQueue.push("FirstElement");

        assertEquals(1, linkQueue.getQueueSize());
    }

    @Test
    public void test_ClearedQueue_IsEmpty() {
        LinkQueue linkQueue = new LinkQueue();
        linkQueue.push("FirstElement");

        linkQueue.clear();

        assertTrue(linkQueue.isEmpty());
    }
}
