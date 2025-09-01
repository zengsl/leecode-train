package juc;

import org.openjdk.jol.info.ClassLayout;
// 省略import
public class ObjectLock
{
    /*private Integer amount = 0; //整型字段占用4字节

    public void increase()
    {
        synchronized (this)
        {
            amount++;
        }
    }

    *//**
     * 输出十六进制、小端模式的hashCode
     *//*
    public String hexHash()
    {
        //对象的原始 hashCode, Java默认为大端模式
        int hashCode = this.hashCode();

        //转成小端模式的字节数组
        byte[] hashCode_LE = ByteUtil.int2Bytes_LE(hashCode);

        //转成十六进制形式的字符串
        return ByteUtil.byteToHex(hashCode_LE);
    }

    *//**
     * 输出二进制、小端模式的hashCode
     *//*
    public String binaryHash()
    {
        //对象的原始 hashCode, Java默认为大端模式
        int hashCode = this.hashCode();

        //转成小端模式的字节数组
        byte[] hashCode_LE = ByteUtil.int2Bytes_LE(hashCode);

        StringBuffer buffer=new StringBuffer();
        for (byte b:hashCode_LE)
        {
            //转成二进制形式的字符串
            buffer.append( ByteUtil.byte2BinaryString(b));
            buffer.append(" ");
        }
        return buffer.toString();
    }

    *//**
     * 输出十六进制、小端模式的ThreadId
     *//*
    public String hexThreadId()
    {
        //当前线程的 threadID, Java默认为大端模式
        long threadID = Thread.currentThread().getId();

        //转成小端模式的字节数组
        byte[] threadID_LE = ByteUtil.long2bytes_LE(threadID);

        //转成十六进制形式的字符串
        return ByteUtil.byteToHex(threadID_LE);
    }

    *//**
     * 输出二进制、小端模式的ThreadId
     *//*
    public String binaryThreadId()
    {
        //当前线程的 threadID, Java默认为大端模式
        long threadID = Thread.currentThread().getId();
        //转成小端模式的字节数组
        byte[] threadID_LE = ByteUtil.long2bytes_LE(threadID);

        StringBuffer buffer=new StringBuffer();
        for (byte b:threadID_LE)
        {
            //转成二进制形式的字符串
            buffer.append( ByteUtil.byte2BinaryString(b));
            buffer.append(" ");
        }
        return buffer.toString();
    }

    public void printSelf()
    {
        // 输出十六进制、小端模式的hashCode
        Print.fo("lock hexHash= " + hexHash());

        // 输出二进制、小端模式的hashCode
        Print.fo("lock binaryHash= " + binaryHash());

        //通过JOL工具获取this的对象布局
        String printable = ClassLayout.parseInstance(this).toPrintable();

        //输出对象布局
        Print.fo("lock = " + printable);
    }*/
    // 省略其他
}