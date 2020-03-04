package com.webApp.addPack;

import java.io.FileInputStream;
import java.io.IOException;

public class SuperArray {
    private byte[][] data;
    private int startPointerToWrite = 0;
    private static final int MAX_ARRAY_POINTER = Integer.MAX_VALUE - 1;
    public byte[][] getData() {
        return data;
    }
    public SuperArray addBytesFromFileInput(FileInputStream fis) {
        int numberOfBytesToRead = 0;
        byte[] singleReadBytes = null;
        int sizeOfRead = 0;
        try {
            while ((numberOfBytesToRead = fis.available()) != 0) {
                singleReadBytes = new byte[numberOfBytesToRead];
                sizeOfRead = fis.read(singleReadBytes);
                if (sizeOfRead != 0) fillArrayDataWithNewArray(singleReadBytes);
            }
            return this;
        } catch (IOException e) {
            System.out.println("*** writeBytesFromFileInputToOutput: " + e.toString());
            return null;
        }
    }

    private SuperArray fillArrayDataWithNewArray(byte[] array) {
        if (data == null) {
            data = new byte[1][];
            data[0] = array;
            return this;
        }
        int arrayIndex = 0;
        byte[] newArr = new byte[startPointerToWrite + array.length - 1];
        System.arraycopy(data[data.length - 1], 0, newArr, 0, data[data.length - 1].length);
        data[data.length - 1] = newArr;
        while (startPointerToWrite <= MAX_ARRAY_POINTER) {
            data[data.length - 1][startPointerToWrite] = array[arrayIndex];
            arrayIndex++;
            startPointerToWrite++;
        }
        if (array.length == arrayIndex + 1) {
            System.out.println("*** fillArrayDataWithNewArray: Array successfully written");
            return this;
        }
        System.out.println("*** fillArrayDataWithNewArray: Extending array with new data");
        byte[][] newData = new byte[data.length][];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
        // add new array
        byte[] arrToAppend = new byte[array.length - arrayIndex];
        System.arraycopy(array, arrayIndex, arrToAppend, 0, array.length - arrayIndex);
        startPointerToWrite = arrToAppend.length;
        data[data.length - 1] = arrToAppend;
        return this;
    }

    public long getTotalDataSize() {
        long totalSize = 0;
        for (byte[] bytes : data) {
            totalSize += (long) bytes.length;
        }
        return totalSize;
    }
}
