package com.aizone.blockchain.encrypt;

import STRS.STRSSignature;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWNumericArray;

import java.util.Map;

/**
 * 签名工具类
 * @since 24-6-6
 */
public class SignUtils {

    /**
     * 使用环签名进行签名
     * @return
     */
    public static Object[] strsSign(Map<String, Object> step, double[][] PK1, String privateKey1,String privateKey2) throws Exception {
        MWNumericArray PK2 = new MWNumericArray(PK1, MWClassID.DOUBLE);
        double[][] PK = (double[][]) PK2.toDoubleArray();
        //double[][] SK = WalletUtils.decodeBase58ToObject(privateKey);
        MWNumericArray sk1 = new MWNumericArray(WalletUtils.decodeBase58ToObject(privateKey1), MWClassID.DOUBLE);
        MWNumericArray sk2 = new MWNumericArray(WalletUtils.decodeBase58ToObject(privateKey2), MWClassID.DOUBLE);
        double[][] SK1 = (double[][]) sk1.toDoubleArray();
        double[][] SK2 = (double[][]) sk2.toDoubleArray();

        //调用密钥生成方法
        STRSSignature strsSignature = new STRSSignature();
        /**
         * 调用签名算法   PK  SK1 SK2
         * function [C, z1, z2, theta, t0, h0, Lpk] = signature(n, q, h, f, PK, s1, s2, N, miu)
         */
        Object[] ntrsSign = strsSignature.signature(7, step.get("n"), step.get("q"),step.get("h") ,step.get("f") , PK, SK1,SK2,step.get("N"),step.get("miu"));
        return ntrsSign;
    }



    /**
     * 验证签名是否有效
     * @return
     */
    public static String strsVerify(Map<String, Object> step,Object[] sign) throws Exception {
        Object C1 = sign[0];
        Object z11 = sign[1];
        Object z22 = sign[2];
        Object theta1 = sign[3];
        Object t01 = sign[4];
        Object h01 = sign[5];
        Object Lpk1 = sign[6];
        //调用方法
        STRSSignature strsSignature = new STRSSignature();
        /**
         * 调用验证算法，看验证是否通过
         * function [result] = verify(n, q, h, f, Lpk, C, z1, z2, theta, t0, h0, miu)
         */

        MWNumericArray C2 = new MWNumericArray(C1, MWClassID.DOUBLE);
        double[][] C = (double[][]) C2.toDoubleArray();

        MWNumericArray z111 = new MWNumericArray(z11, MWClassID.DOUBLE);
        double[][] z1 = (double[][]) z111.toDoubleArray();

        MWNumericArray z222 = new MWNumericArray(z22, MWClassID.DOUBLE);
        double[][] z2 = (double[][]) z222.toDoubleArray();

        MWNumericArray theta2 = new MWNumericArray(theta1, MWClassID.DOUBLE);
        double[][] theta = (double[][]) theta2.toDoubleArray();

        MWNumericArray t02 = new MWNumericArray(t01, MWClassID.DOUBLE);
        double[][] t0 = (double[][]) t02.toDoubleArray();

        MWNumericArray h02 = new MWNumericArray(h01, MWClassID.DOUBLE);
        double[][] h0 = (double[][]) h02.toDoubleArray();

        MWNumericArray Lpk2 = new MWNumericArray(Lpk1, MWClassID.DOUBLE);
        double[][] Lpk = (double[][]) Lpk2.toDoubleArray();
        Object[] objects3 = strsSignature.verify(1, step.get("n"), step.get("q"),step.get("h") ,step.get("f") ,  Lpk,C,z1,z2,theta, t0, h0,step.get("miu"));
        return objects3[0].toString();
    }
}
