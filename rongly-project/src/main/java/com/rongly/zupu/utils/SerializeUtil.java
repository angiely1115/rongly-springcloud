package com.rongly.zupu.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
@Slf4j
public class SerializeUtil {
    public static byte[] serialize(Object object) {
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);) {
            // 序列化

            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            log.error("JDK序列话异常",e);
        }
        return null;
    }

    public static Object unserialize(byte[] bytes) {
        try(ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);) {
            // 反序列化
            return ois.readObject();
        } catch (Exception e) {
           log.error("JDK返序列话异常",e);
        }
        return null;
    }

}