/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fpm.beloushk
 */
import java.io.*;
import java.math.*;
import java.util.Scanner;
class PrWr
{ 
	public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input x and k");
        double x = scan.nextDouble();
        int k = scan.nextInt();
        double E = Math.pow(10.0, (-k));
        double sum = 1, chlen = 1;
        for (int i = 1; chlen > E; i++) {
            chlen = chlen * x / i;
            sum += chlen;
        }
        double e = Math.pow(2.73, Math.abs(x));
        System.out.println("Sum=" + sum + " e=" + e);
        double dd = 3.56767678989;


        BigDecimal e1 = new BigDecimal(e);
        BigDecimal x1 = new BigDecimal(x);
        BigInteger k1 = new BigInteger(String.valueOf(k));
        BigDecimal sum1 = new BigDecimal(1);
        BigDecimal chlen1 = new BigDecimal(chlen);
        BigDecimal E1 = new BigDecimal(Math.pow(10.0, (-k)));
        for (BigInteger i = new BigInteger(String.valueOf(1)); (chlen1.abs().compareTo(E1)<0); i.add(BigInteger.valueOf(1)))
        {
            chlen1 = chlen1.multiply(x1);
            chlen1 = chlen1.divide(new BigDecimal(i), 15, RoundingMode.HALF_EVEN);
            sum1 = sum1.add(chlen1);
        }
        System.out.println("Sum=" + sum + " e=" + e);
    }
} 

