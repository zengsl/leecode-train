package com.leecode.hot100.stack.minStack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

public class MinStack {
    private Deque<Integer> stack;
    private Deque<Integer> minStack;

    public MinStack() {
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
    }

    public void push(int val) {
        stack.push(val);
        minStack.push(Math.min(Objects.requireNonNullElse(minStack.peek(), Integer.MAX_VALUE), val));
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
