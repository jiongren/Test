package com.tree.jinbu.net;

/**
 * 描述：
 *
 * @author LIUQIANG928
 * @date 2017-09-28
 */

public class HttpConstant {
    public static String IP_PRODUCT = "product";
    public static String IP_TEST = "test";
    public static String baseIp = IP_PRODUCT;
    public static String http_ip = baseIp + "";



    public static final String HOST_NAME      = "*.test.com";
    public static final String CERT = "-----BEGIN CERTIFICATE-----\n" +
            "\" +\n" +
            "            \"MIIF3zCCBMegAwIBAgIQAtpbI8tWMwkAIVL+vRpewzANBgkqhkiG9w0BAQsFADBEMQswCQYDVQQG\\n\" +\n" +
            "            \"EwJDTjEaMBgGA1UECgwRV29TaWduIENBIExpbWl0ZWQxGTAXBgNVBAMMEFdvU2lnbiBPViBTU0wg\\n\" +\n" +
            "            \"Q0EwHhcNMTcwNDExMTA0NzIzWhcNMTkwNDExMTA0NzIzWjCBiTELMAkGA1UEBhMCQ04xOTA3BgNV\\n\" +\n" +
            "            \"BAoMMOW5s+WuieS4h+WutuWMu+eWl+aKlei1hOeuoeeQhuaciemZkOi0o+S7u+WFrOWPuDESMBAG\\n\" +\n" +
            "            \"A1UEBwwJ5rex5Zyz5biCMRIwEAYDVQQIDAnlub/kuJznnIExFzAVBgNVBAMMDioucGluZ2Fud2ou\\n\" +\n" +
            "            \"Y29tMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsii14wVZQThnfiGbtgQEkO3aiC+E\\n\" +\n" +
            "            \"mSCjOEwMkQZc+g7Bi8DSBf8L/hJWnymViUw2PRzKUcBuWy5djkpbHAB00siE/n5+1Zg7Eu8gkMhf\\n\" +\n" +
            "            \"08aPtTJzWtxlsZhDxqvDjEVubtsvuSp6K6toidpL6+BQ1uVMt3uxyRsrLFiuuExqr6azQkoO6gW7\\n\" +\n" +
            "            \"ITAOlIGouJlZrYTO+biZjD+3GWOd4UsxMse3qfsMjFq6S3z9fXVx7hlP+cZXjcYfQ/ZXKa2CFhKt\\n\" +\n" +
            "            \"yCfrV/W5f+b7hL1pk2fSLktop/v/ycpQAAXLmn4oTArRv1d8pxzBIaUqzyOHjg3sZAludDR8t8D5\\n\" +\n" +
            "            \"U9+WKULf7QIDAQABo4IChTCCAoEwDAYDVR0TAQH/BAIwADA8BgNVHR8ENTAzMDGgL6AthitodHRw\\n\" +\n" +
            "            \"Oi8vd29zaWduLmNybC5jZXJ0dW0ucGwvd29zaWduLW92Y2EuY3JsMHcGCCsGAQUFBwEBBGswaTAu\\n\" +\n" +
            "            \"BggrBgEFBQcwAYYiaHR0cDovL3dvc2lnbi1vdmNhLm9jc3AtY2VydHVtLmNvbTA3BggrBgEFBQcw\\n\" +\n" +
            "            \"AoYraHR0cDovL3JlcG9zaXRvcnkuY2VydHVtLnBsL3dvc2lnbi1vdmNhLmNlcjAfBgNVHSMEGDAW\\n\" +\n" +
            "            \"gBShE1TcVnMsJ4LKyITv7r8A/V+rVjAdBgNVHQ4EFgQUroiiFHIJ1vqY5FoyIaMz0MVPG1MwDgYD\\n\" +\n" +
            "            \"VR0PAQH/BAQDAgWgMIIBIAYDVR0gBIIBFzCCARMwCAYGZ4EMAQICMIIBBQYMKoRoAYb2dwIFAQwC\\n\" +\n" +
            "            \"MIH0MIHxBggrBgEFBQcCAjCB5DAfFhhBc3NlY28gRGF0YSBTeXN0ZW1zIFMuQS4wAwIBARqBwFVz\\n\" +\n" +
            "            \"YWdlIG9mIHRoaXMgY2VydGlmaWNhdGUgaXMgc3RyaWN0bHkgc3ViamVjdGVkIHRvIHRoZSBDRVJU\\n\" +\n" +
            "            \"VU0gQ2VydGlmaWNhdGlvbiBQcmFjdGljZSBTdGF0ZW1lbnQgKENQUykgaW5jb3Jwb3JhdGVkIGJ5\\n\" +\n" +
            "            \"IHJlZmVyZW5jZSBoZXJlaW4gYW5kIGluIHRoZSByZXBvc2l0b3J5IGF0IGh0dHBzOi8vd3d3LmNl\\n\" +\n" +
            "            \"cnR1bS5wbC9yZXBvc2l0b3J5LjAdBgNVHSUEFjAUBggrBgEFBQcDAQYIKwYBBQUHAwIwJwYDVR0R\\n\" +\n" +
            "            \"BCAwHoIOKi5waW5nYW53ai5jb22CDHBpbmdhbndqLmNvbTANBgkqhkiG9w0BAQsFAAOCAQEAZ4lx\\n\" +\n" +
            "            \"XUw0GRS6RZS7R20t2GXDa4G8w4I4MshwUNxUmdWYep/Njini41O4z3Gts8+T/1ymj1xTJdnQatYR\\n\" +\n" +
            "            \"7ghbogIGD2OjKwVODJKZ7N/hFga5kzm4DpWaCdKLnAivq8e1eHTewlmth4/oiFbgSM/+/5RSb5ge\\n\" +\n" +
            "            \"txORpHWwbN7wA3VBoXovP5Aj233YIribw9VZkn4PcWUxs57bd/mi0FwPSyfgZl/3Wzz7ddkc+Jl3\\n\" +\n" +
            "            \"nWZdos63hyxK4Z6Mtqzf/4PvuXcpqi//HjAnitdTSpOxJYeBDaZm4CHiLnCEyhm58DKfme9rxDdr\\n\" +\n" +
            "            \"XugZFPcNf2d3Ck49b27DMBziuG+02HKwog==\\n\" +\n" +
            "            \"-----END CERTIFICATE-----";

}
