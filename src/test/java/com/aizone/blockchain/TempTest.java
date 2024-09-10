package com.aizone.blockchain;

import STRS.STRSSignature;
import com.mathworks.toolbox.javabuilder.MWCharArray;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * 临时测试文件，测试各种其他测试代码
 *  
 * @since 24-6-6
 */
public class TempTest {


	@Test
	public void run() throws MWException {

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
		double[][] h = generateH(n, q);
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


		/**
		 * 生成公私钥  sk1  sk2  pk
		 * function[s1, s2, PK] = keygen(n, q, h, f)
		 */
		Object[] strsKeyGen = strsSignature.keygen(3, n, q, h, f);

		double[][] SK1 = (double[][]) ((MWNumericArray) strsKeyGen[0]).toDoubleArray();
		double[][] SK2 = (double[][]) ((MWNumericArray) strsKeyGen[1]).toDoubleArray();
		double[][] PK = (double[][]) ((MWNumericArray) strsKeyGen[2]).toDoubleArray();



		/**
		 * 调用签名算法   PK  SK1 SK2
		 * function [C, z1, z2, theta, t0, h0, Lpk] = signature(n, q, h, f, PK, s1, s2, N, miu)
		 */
		Object[] sign = strsSignature.signature(7, n, q,h ,f, PK, SK1,SK2,N,miu);
		System.out.println("sign"+Arrays.toString(sign));


		Object C1 = sign[0];
		Object z11 = sign[1];
		Object z22 = sign[2];
		Object theta1 = sign[3];
		Object t01 = sign[4];
		Object h01 = sign[5];
		Object Lpk1 = sign[6];
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


		Object[] objects3 = strsSignature.verify(1, n, q,h ,f ,  Lpk,C,z1,z2,theta, t0, h0,miu);
		System.out.println("result=="+objects3[0]);
	}

	public static double[][] generateMatrix(Long n, Long d, Long m, Double q) {
		int nInt = n.intValue();
		int dInt = d.intValue();
		int mInt = m.intValue();
		int qInt = q.intValue();

		double[][] A = new double[nInt][dInt * mInt];
		Random rand = new Random();
		int range = (qInt - 1) / 2;
		for (int i = 0; i < nInt; i++) {
			for (int j = 0; j < dInt * mInt; j++) {
				A[i][j] = rand.nextInt(range * 2 + 1) - range;
			}
		}
		return A;
	}

	public static double[][] constructIrreduciblePolynomial(Long d) {
		int dInt = d.intValue();

		// Create a 2D array to represent the polynomial
		double[][] f = new double[dInt + 1][2];

		// Set the coefficients for the terms x^0 and x^d
		f[0][0] = 0; // Power of the variable for the constant term (x^0)
		f[0][1] = 1; // Coefficient value for the constant term (x^0)
		f[dInt][0] = dInt; // Power of the variable for the x^d term
		f[dInt][1] = 1; // Coefficient value for the x^d term

		return f;
	}


	public static double[][] generateH(Long n, Double q) {
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
}
