package com.ant.shop.admin.utils;

import cfca.sm2rsa.common.Mechanism;
import cfca.sm2rsa.common.PKIException;
import cfca.util.*;
import cfca.util.cipher.lib.JCrypto;
import cfca.util.cipher.lib.Session;
import cfca.x509.certificate.X509Cert;
import cfca.x509.certificate.X509CertHelper;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

public class SecurityUtil {
    private static Session session = null;

    static {
        //软库初始化
        try {
            JCrypto.getInstance().initialize(JCrypto.JSOFT_LIB, null);
            session = JCrypto.getInstance().openSession(JCrypto.JSOFT_LIB);
        } catch (PKIException e) {
            System.out.println("CFCA软库初始化异常:" + e);
        }

    }

    /*
     * 将base64证书信息转换为证书
     */
    public X509Cert parseCert(String certStr) throws PKIException, UnsupportedEncodingException {
        return X509CertHelper.parse(certStr.getBytes("UTF-8"));
    }

    /**
     * 6.1 RSA消息签名(PKCS#1)
     *
     * @throws Exception
     */
    public static String rsaP1Sign(String srcData, String pfxPath, String pwd) throws Exception {
        PrivateKey priKey = (PrivateKey) getRSAPriKey(pfxPath, pwd);
        String alg = Mechanism.SHA256_RSA;
        byte[] signature = new SignatureUtil2().p1SignMessage(alg, srcData.getBytes("UTF-8"), priKey, session);
        return new String(signature, "UTF-8");
    }

    public String rsaP1Sign(String srcData, PrivateKey priKey) throws Exception {
        String alg = Mechanism.SHA256_RSA;
        byte[] signature = new SignatureUtil2().p1SignMessage(alg, srcData.getBytes("UTF-8"), priKey, session);
        return new String(signature, "UTF-8");
    }

    private static Key getRSAPriKey(String pfxPath, String pwd) throws Exception {
        return KeyUtil.getPrivateKeyFromPFX(pfxPath, pwd);
    }

    /**
     * RSA消息验签(PKCS#1)--通过路径获取公钥信息
     *
     * @param signData
     * @param srcData
     * @param cerPath
     * @return
     * @throws Exception
     */
    public boolean rsaP1Verify(String signData, String srcData, String cerPath) throws Exception {
        String alg = Mechanism.SHA256_RSA;
        PublicKey pubKey = (PublicKey) getRSAPubKey(cerPath);
        return new SignatureUtil2().p1VerifyMessage(alg, srcData.getBytes("UTF-8"), signData.getBytes(), pubKey, session);
    }

    /**
     * RSA消息验签(PKCS#1)--直接获取公钥信息
     *
     * @param signData
     * @param srcData
     * @param cert
     * @return
     * @throws Exception
     */
    public static boolean rsaP1Verify(String signData, String srcData, byte[] cert) throws Exception {
        String alg = Mechanism.SHA256_RSA;
        PublicKey pubKey = (PublicKey) getRSAPubKey(cert);
        return new SignatureUtil2().p1VerifyMessage(alg, srcData.getBytes("UTF-8"), signData.getBytes(), pubKey, session);
    }

    public boolean rsaP1Verify(String signData, String srcData, PublicKey pubKey) throws Exception {
        String alg = Mechanism.SHA256_RSA;
        return new SignatureUtil2().p1VerifyMessage(alg, srcData.getBytes("UTF-8"), signData.getBytes(), pubKey, session);
    }

    private Key getRSAPubKey(String cerPath) throws Exception {
        X509Cert cert = X509CertHelper.parse(cerPath);
        return cert.getPublicKey();
    }

    private static Key getRSAPubKey(byte[] cert) throws Exception {
        X509Cert x509cert = X509CertHelper.parse(cert);
        return x509cert.getPublicKey();
    }

    /**
     * 7.1 PKCS#7消息加密(数字信封)
     *
     * @throws Exception
     */
    public static byte[] envelopMessage(String srcData, String cerPath) throws Exception {
        FileInputStream is = null;
        try {
            X509Cert[] certs = new X509Cert[1];
            is = new FileInputStream(cerPath);
            X509Cert cert = new X509Cert(is);
            certs[0] = cert;
            return EnvelopeUtil.envelopeMessage(srcData.getBytes("UTF-8"), Mechanism.DES3_CBC, certs);
        } finally {
            if (is != null) try {
                is.close();
            } catch (Exception e) {
            }
        }
    }

    public byte[] envelopMessageByCert(String srcData, String cer) throws Exception {
        X509Cert[] certs = new X509Cert[1];
        X509Cert cert = new X509Cert(cer.getBytes("UTF-8"));
        certs[0] = cert;
        return EnvelopeUtil.envelopeMessage(srcData.getBytes("UTF-8"), Mechanism.DES3_CBC, certs);
    }

    /**
     * 7.1.1 消息解密(数字信封)
     *
     * @throws Exception
     */
    public static byte[] openMessage(String encryptedData, String pfxPath, String pfxPwd) throws Exception {
        PrivateKey priKey = KeyUtil.getPrivateKeyFromPFX(pfxPath, pfxPwd);
        X509Cert cert = CertUtil.getCertFromPfx(pfxPath, pfxPwd);
        return EnvelopeUtil.openEvelopedMessage(encryptedData.getBytes(), priKey, cert, session);
    }

    public String getCertFromPfxByPath(String pfxPath, String pfxPwd) throws PKIException, UnsupportedEncodingException {
        X509Cert cert = CertUtil.getCertFromPfx(pfxPath, pfxPwd);
        return new String(Base64.encode(cert.getEncoded()), "UTF-8");
    }

    public String getCertFromPfxByInputStream(InputStream inputStream, String pfxPwd) throws PKIException, UnsupportedEncodingException {
        X509Cert cert = CertUtil.getCertFromPfx(inputStream, pfxPwd);
        return new String(Base64.encode(cert.getEncoded()), "UTF-8");
    }

    public static void main(String args[]) throws Exception {
        String srcData = "asdfsadfasfasdf";
        String merchantPfxFile = "/home/zj/Documents/as_payment/CF2000729720/merchant.pfx";
        String merchantCerFile = "/home/zj/Documents/as_payment/CF2000729720/merchant.cer";
        String umbpayPfxFile = "/home/zj/Documents/as_payment/CF2000729720/platform.pfx";
        String umbpayCerFile = "/home/zj/Documents/as_payment/CF2000729720/platform.cer";
        String cert = "-----BEGIN CERTIFICATE-----MIIDhTCCAm2gAwIBAgIFECFhIFUwDQYJKoZIhvcNAQEFBQAwITELMAkGA1UEBhMCQ04xEjAQBgNVBAoTCUNGQ0EgT0NBMTAeFw0xNDExMjYwNTEwMjVaFw0xNzExMjYwNTEwMjVaMH8xCzAJBgNVBAYTAmNuMRIwEAYDVQQKEwlDRkNBIE9DQTExEzARBgNVBAsTCkJKWkhPTkdUT1UxFDASBgNVBAsTC0VudGVycHJpc2VzMTEwLwYDVQQDFCgwNDFAMzExMDEwODAxNTQ1NTgwOEBCSlpIT05HVE9VQDAwMDAwMDAxMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCrZGdvj0PEYVMKoxBlr5YnET4RfdyMeqZ7u9So0Ru+AXUKPTQeXqV6MYUJVPybOZMP/oKOxqiNcj+Zj/rOf4hnpmfw/o0eUmeeaMydSZm0RQLRQtZ400opeyJDxpNJJpDWiPCurV6S4ex2PwBAuChB4ys17fCn17uJCeBqDGEbSwIDAQABo4HpMIHmMB8GA1UdIwQYMBaAFNHb6YiC5d0aj0yqAIy+fPKrG/bZMEgGA1UdIARBMD8wPQYIYIEchu8qAQEwMTAvBggrBgEFBQcCARYjaHR0cDovL3d3dy5jZmNhLmNvbS5jbi91cy91cy0xNC5odG0wOAYDVR0fBDEwLzAtoCugKYYnaHR0cDovL2NybC5jZmNhLmNvbS5jbi9SU0EvY3JsMjc1MTQuY3JsMAsGA1UdDwQEAwID6DAdBgNVHQ4EFgQUa9O4JW4rI8s+rpSOH3SgqWoxpRIwEwYDVR0lBAwwCgYIKwYBBQUHAwIwDQYJKoZIhvcNAQEFBQADggEBAD8Q1irdxID0iCkQAH2MLCcBZaS7jfMiLdBmALGT0q4KhG8BtTaXkUadjoA4eIViuh+5KRxbhac4VWQjhOAtTvwHpVWPJkK4P5OB97pux7tT7jjpzOcJ6HDSApAJQQbgyacsuuZF3DAZ4MvZmktnSzQY/+GgY3eGnPMFcVCFKNByt/4TzZjbMYV8sz2xUGFUE5a6kbQKnN7T01V8raJ3K+0i41vQ9z8NUaEKJK5wPjd6jQU13nNVZkUS569+OKIaSSDJ2O6m6eFSYUelFeYG7xOYNW4LEzsvau4jygcCs4YQzY0Qo2kZXiDOB2gXfWDQL1oEzUjr1zVHTanuX1MANPo=-----END CERTIFICATE-----";

        String sign = SecurityUtil.rsaP1Sign(srcData, umbpayPfxFile, "umbpay");
        System.out.println("签名：" + sign);

        String envelopdata = new String(SecurityUtil.envelopMessage(srcData, merchantCerFile));
        System.out.println("加密：" + envelopdata);

        String opendata = new String(SecurityUtil.openMessage(envelopdata, merchantPfxFile, "PKd8*Wu#@JUSa#gn7z"));
        System.out.println("解密：" + opendata);

        boolean verify = SecurityUtil.rsaP1Verify(sign, srcData, cert.getBytes());
        System.out.println("验签：" + verify);
    }
}
