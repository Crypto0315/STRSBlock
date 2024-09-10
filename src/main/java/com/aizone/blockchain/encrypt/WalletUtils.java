package com.aizone.blockchain.encrypt;

import STRS.STRSSignature;
import com.aizone.blockchain.utils.ByteUtils;
import com.aizone.blockchain.wallet.Account;
import com.google.common.base.Optional;
import com.mathworks.toolbox.javabuilder.MWCharArray;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;

import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 钱包工具类
 * @since 24-6-6
 */
public class WalletUtils {

    /**
     * 加密字符集合
     */
    private static final String ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";

    public static Map<String, Object[]> generateKeyGen(List<Object> setup) throws Exception {
        Map<String, Object[]> map = new HashMap<>();

        //调用密钥生成方法
        STRSSignature strsSignature = new STRSSignature();

        Long n = (Long) setup.get(0);
        Double q = (Double) setup.get(1);
        double[][] h = (double[][]) setup.get(2);
        double[][] f = (double[][]) setup.get(3);

        /**
         * 生成公私钥  sk1  sk2  pk
         * function[s1, s2, PK] = keygen(n, q, h, f)
         */
        Object[] strsKeyGen = strsSignature.keygen(3, n, q, h, f);
        map.put("strsKeyGen",strsKeyGen);
        return map;
    }

    /**
     * 环签名算法参数定义
     * @return
     * @throws Exception
     */
    public static List<Object> generateSetup() throws Exception {

        List<Object> list = new ArrayList<>();
        /**
         * function [n, q, h, f, N, miu] = setup
         */

        //调用密钥生成方法
        // 创建 STRSSignature 对象（假设 MATLAB 函数在这个类中）
        STRSSignature strsSignature = new STRSSignature();

        // 调用 MATLAB setup 函数，要求返回 6 个参数
        Object[] strsSetup = strsSignature.setup(6);

        // 获取第一个输出 n（长整型）
        Long n = ((MWNumericArray) strsSetup[0]).getLong();
        System.out.println("Value of n: " + n);

        // 获取第二个输出 q
        Double q = ((MWNumericArray) strsSetup[1]).getDouble();
        System.out.println("Value of q: " + q);

        // 获取第三个输出 h（double 数组）
        double[][] h = generateMatrix(n, q);
        /*MWNumericArray hArray = (MWNumericArray) strsSetup[2];
        double[][] h = (double[][]) hArray.toDoubleArray();*/
        System.out.println("Array h: " + java.util.Arrays.toString(h));

        // 获取第四个输出 f（double 数组）
        MWNumericArray fArray = (MWNumericArray) strsSetup[3];
        double[][] f = (double[][]) fArray.toDoubleArray();
        System.out.println("Array f: " + java.util.Arrays.toString(f));

        // 获取第五个输出 N（长整型）
        Double N = ((MWNumericArray) strsSetup[4]).getDouble();
        System.out.println("Value of N: " + N);

        // 获取第六个输出 miu（字符串）
        MWCharArray miuArray = (MWCharArray) strsSetup[5];
        String miu = miuArray.toString();
        System.out.println("Value of miu: " + miu);

        list.add(n);
        list.add(q);
        list.add(h);
        list.add(f);
        list.add(N);
        list.add(miu);
        return list;
    }

    public static Map<String, Object> setStep(Optional<Account> sender) {
        Map<String, Object> ntrsSetup = new HashMap<>();
        ntrsSetup.put("n", sender.get().getN());
        ntrsSetup.put("q", sender.get().getQ());
        ntrsSetup.put("h", sender.get().getH());
        ntrsSetup.put("f", sender.get().getF());
        ntrsSetup.put("N", sender.get().getN1());
        ntrsSetup.put("miu", sender.get().getMiu());

        return ntrsSetup;
    }

    public static double[][] generateMatrix(Long n, Double q) {
        // Convert Long and Double types to primitive int and double
        int nInt = n.intValue();
        int qInt = q.intValue();

        // Initialize the result array of size 1 x n
        double[][] h = new double[1][nInt];

        // Create a Random object for generating random numbers
        Random rand = new Random();

        // Calculate the range for random values
        int range = qInt; // As per your style, qInt is used directly as the range

        // Fill the array with random values between -q and q
        for (int i = 0; i < nInt; i++) {
            // Generate a random integer value between -q and q
            h[0][i] = rand.nextInt(range * 2 + 1) - range;
        }

        return h;
    }





    public static String encodeObjectToBase58WithCompression(Object obj) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gzipOut = new GZIPOutputStream(bos);
            ObjectOutputStream oos = new ObjectOutputStream(gzipOut);
            oos.writeObject(obj);
            oos.flush();
            oos.close();
            gzipOut.close();
            byte[] compressedBytes = bos.toByteArray();
            return Base58.encode(compressedBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 将对象编码为 Base58 字符串
    public static String encodeObjectToBase58(Object obj) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            byte[] bytes = bos.toByteArray();
            return Base58.encode(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 将 Base58 字符串解码为对象
    public static double[][] decodeBase58ToObject(String base58String) {
        byte[] bytes = Base58.decode(base58String);  // Decode Base58 string back to byte array
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);

        try {
            // Decompress the byte array using GZIPInputStream
            GZIPInputStream gzipIn = new GZIPInputStream(bis);
            ObjectInputStream ois = new ObjectInputStream(gzipIn);

            // Deserialize the object
            Object obj = ois.readObject();

            // Close streams
            ois.close();
            gzipIn.close();

            // Check if the deserialized object is a double[][]
            if (obj instanceof double[][]) {
                return (double[][]) obj;
            } else {
                System.err.println("Decoded object is not a double[][] array.");
                return null;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将 byte[] 公钥转成字符串
     * @param publicKey
     * @return
     */
    public static String publicKeyEncode(byte[] publicKey) {
        return Base58.encode(publicKey);
    }

    /**
     * 将字符串转成 byte[]
     * @param publicKey
     * @return
     */
    public static byte[] publicKeyDecode(String publicKey) {
        return Base58.decode(publicKey);
    }

    /**
     * 根据公钥生成钱包地址
     * @param publicKeyObject
     * @return
     */
    public static String generateAddress(double[][] publicKeyObject) {
        byte[] publicKeyBytes = convertToBytes(publicKeyObject);
        //1. 计算公钥的 SHA-256 哈希值
        byte[] sha256Bytes = HashUtils.sha256(publicKeyBytes);
        //2. 取上一步结果，计算 RIPEMD-160 哈希值
        RIPEMD160Digest digest = new RIPEMD160Digest();
        digest.update(sha256Bytes, 0, sha256Bytes.length);
        byte[] ripemd160Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(ripemd160Bytes, 0);
        //3. 取上一步结果，前面加入地址版本号（主网版本号“0x00”）
        byte[] networkID = new BigInteger("00", 16).toByteArray();
        byte[] extendedRipemd160Bytes = ByteUtils.add(networkID, ripemd160Bytes);
        //4. 取上一步结果，计算 SHA-256 哈希值
        byte[] oneceSha256Bytes = HashUtils.sha256(extendedRipemd160Bytes);
        //5. 取上一步结果，再计算一下 SHA-256 哈希值
        byte[] twiceSha256Bytes = HashUtils.sha256(oneceSha256Bytes);
        //6. 取上一步结果的前4个字节（8位十六进制）
        byte[] checksum = new byte[4];
        System.arraycopy(twiceSha256Bytes, 0, checksum, 0, 4);
        //7. 把这4个字节加在第5步的结果后面，作为校验
        byte[] binaryAddressBytes = ByteUtils.add(extendedRipemd160Bytes, checksum);
        //8. 把结果用 Base58 编码算法进行一次编码
        return Base58.encode(binaryAddressBytes);
    }

    private static byte[] convertToBytes(double[][] publicKey) {
        // Calculate the total number of bytes needed
        int totalBytes = 0;
        for (double[] row : publicKey) {
            totalBytes += row.length;
        }

        // Create a byte array of the required length
        byte[] publicKeyBytes = new byte[totalBytes];

        // Copy the double values into the byte array
        int index = 0;
        for (double[] row : publicKey) {
            for (double value : row) {
                publicKeyBytes[index++] = (byte) value;
            }
        }

        return publicKeyBytes;
    }


    /**
     * 验证地址是否合法
     * @param address
     * @return
     */
    public static boolean verifyAddress(String address) {

        if (address.length() < 26 || address.length() > 35) {
            return false;
        }
        byte[] decoded = decodeBase58To25Bytes(address);
        if (null == decoded) {
            return false;
        }
        // 验证校验码
        byte[] hash1 = HashUtils.sha256(Arrays.copyOfRange(decoded, 0, 21));
        byte[] hash2 = HashUtils.sha256(hash1);

        return Arrays.equals(Arrays.copyOfRange(hash2, 0, 4), Arrays.copyOfRange(decoded, 21, 25));
    }

    /**
     * 使用 Base58 把地址解码成 25 字节
     * @param input
     * @return
     */
    private static byte[] decodeBase58To25Bytes(String input) {

        BigInteger num = BigInteger.ZERO;
        for (char t : input.toCharArray()) {
            int p = ALPHABET.indexOf(t);
            if (p == -1) {
                return null;
            }
            num = num.multiply(BigInteger.valueOf(58)).add(BigInteger.valueOf(p));
        }

        byte[] result = new byte[25];
        byte[] numBytes = num.toByteArray();
        System.arraycopy(numBytes, 0, result, result.length - numBytes.length, numBytes.length);
        return result;
    }

    // Method to print a 2D matrix
    public static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t"); // Print each element followed by a tab
            }
            System.out.println(); // New line after each row
        }
    }
}
