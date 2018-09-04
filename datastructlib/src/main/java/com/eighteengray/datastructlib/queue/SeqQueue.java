package com.eighteengray.datastructlib.queue;



public class SeqQueue
{
    // 保存数据
    public Object[] data;

    // 头指针
    public int head;

    // 尾指针
    public int rear;

    // 队列的最大容量
    public int maxSize;

    public SeqQueue(int maxSize) {
        this.maxSize = maxSize;
        data = new Object[maxSize];
    }


    /**
     * 入队
     */
    public void enter(SeqQueue queue, Object obj) {
        // 判断队列是否已经满了
        if (queue.rear >= queue.maxSize) {
            return;
        }
        queue.data[queue.rear] = obj;
        queue.rear++;
    }

    /**
     * 出队
     */
    public Object dequeue(SeqQueue queue) {
        // 判断队列是否为空
        if (queue.head == queue.rear) {
            return null;
        }
        Object obj = queue.data[queue.head];
        queue.data[queue.head] = null;
        queue.head++;
        return obj;
    }

    /**
     * 取队头
     */
    public Object getHead(SeqQueue queue) {
        // 判断队列是否为空
        if (queue.head == queue.rear) {
            return null;
        }
        Object obj = queue.data[queue.head];
        return obj;
    }

    /**
     * 取队长
     */
    public int getLength(SeqQueue queue) {
        return queue.rear - queue.head;
    }

    /**
     * 判断队列是否为空
     */
    public boolean isEmpty(SeqQueue queue) {
        return queue.head == queue.rear;
    }

    /**
     * 判断队列是否已经满了
     */
    public boolean isFull(SeqQueue queue) {
        return queue.rear >= queue.maxSize;
    }

}
