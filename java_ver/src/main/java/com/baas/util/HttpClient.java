package com.baas.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClient {

    /**
     * @description: ����Я��������get����
     * @param {String} token
     * @param {String} url
     * @param {String} params
     * @return {*}
     */
    public static String doGet(String token, String url, String params) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            // ͨ��ַĬ�����ô���һ��httpClientʵ��
            httpClient = HttpClients.createDefault();
            // ����httpGetԶ������ʵ��
            url = url + "?" + params;
            System.out.println(url);
            HttpGet httpGet = new HttpGet(url);
            // ��������ͷ��Ϣ����Ȩ
            httpGet.setHeader("Authorization", token);
            // ���������������
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// ������������ʱʱ��
                    .setConnectionRequestTimeout(35000)// ����ʱʱ��
                    .setSocketTimeout(60000)// ���ݶ�ȡ��ʱʱ��
                    .build();
            // ΪhttpGetʵ����������
            httpGet.setConfig(requestConfig);
            // ִ��get����õ����ض���
            response = httpClient.execute(httpGet);
            // ͨ�����ض����ȡ��������
            HttpEntity entity = response.getEntity();
            // ͨ��EntityUtils�е�toString���������ת��Ϊ�ַ���
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // �ر���Դ
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * @description: �����޲�����get����
     * @param {String} token
     * @param {String} url
     * @return {*}
     */
    public static String doGet(String token, String url) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            // ͨ��ַĬ�����ô���һ��httpClientʵ��
            httpClient = HttpClients.createDefault();
            // ����httpGetԶ������ʵ��
            HttpGet httpGet = new HttpGet(url);
            // ��������ͷ��Ϣ����Ȩ
            httpGet.setHeader("Authorization", token);
            // ���������������
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// ������������ʱʱ��
                    .setConnectionRequestTimeout(35000)// ����ʱʱ��
                    .setSocketTimeout(60000)// ���ݶ�ȡ��ʱʱ��
                    .build();
            // ΪhttpGetʵ����������
            httpGet.setConfig(requestConfig);
            // ִ��get����õ����ض���
            response = httpClient.execute(httpGet);
            // ͨ�����ض����ȡ��������
            HttpEntity entity = response.getEntity();
            // ͨ��EntityUtils�е�toString���������ת��Ϊ�ַ���
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // �ر���Դ
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * @description: ����Я��������post����
     * @param {String} token
     * @param {String} url
     * @param {String} params
     * @return {*}
     */
    public static String doPost(String token, String url, String params) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        // ����httpClientʵ��
        httpClient = HttpClients.createDefault();
        // ����httpPostԶ������ʵ��
        HttpPost httpPost = new HttpPost(url);
        // �����������ʵ��
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(150000)// ����������������ʱʱ��
                .setConnectionRequestTimeout(150000)// ������������ʱʱ��
                .setSocketTimeout(150000)// ���ö�ȡ�������ӳ�ʱʱ��
                .build();
        // ΪhttpPostʵ����������
        httpPost.setHeader("Authorization", token);
        System.out.println("-----Token2:"+token);
        httpPost.setConfig(requestConfig);
        // ��������ͷ
        httpPost.addHeader("Content-Type", "application/json");
        // ��װpost�������
        if (null != params) {
            // ΪhttpPost���÷�װ�õ��������
            httpPost.setEntity(new StringEntity(params, "UTF-8"));
            System.out.println("-----params:"+params);
        }
        try {
            // httpClient����ִ��post����,��������Ӧ��������
            httpResponse = httpClient.execute(httpPost);
            // ����Ӧ�����л�ȡ��Ӧ����
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
            System.out.println("-----result2:"+result);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // �ر���Դ
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static String doPut(String token, String url, Map<String, String> params,String filePath) {

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        // ����httpClientʵ��
        httpClient = HttpClients.createDefault();
        // ����httpPostԶ������ʵ��
        HttpPut httpPut = new HttpPut(url);
        // �����������ʵ��
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// ����������������ʱʱ��
                .setConnectionRequestTimeout(35000)// ������������ʱʱ��
                .setSocketTimeout(60000)// ���ö�ȡ�������ӳ�ʱʱ��
                .build();
        // ΪhttpPostʵ����������
        httpPut.setHeader("Authorization", token);
        httpPut.setConfig(requestConfig);
        // ��������ͷ
        // httpPut.addHeader("Content-Type", "application/x-zip-compressed");

        File file = new File(filePath);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        //�������������ģʽ
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        //��������ı����ʽ
        builder.setCharset(Consts.UTF_8);
        builder.setContentType(ContentType.MULTIPART_FORM_DATA);
        //����ļ�
        builder.addBinaryBody("code", file);
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.addTextBody(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity reqEntity = builder.build();
        httpPut.setEntity(reqEntity);
        try {
            // httpClient����ִ��post����,��������Ӧ��������
            // System.out.println(httpPost);
            httpResponse = httpClient.execute(httpPut);
            // ����Ӧ�����л�ȡ��Ӧ����
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // �ر���Դ
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * @description: �����޲�����delete����
     * @param {String} token
     * @param {String} url
     * @return {*}
     */
    public static String doDelete(String token, String url) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        // ����httpClientʵ��
        httpClient = HttpClients.createDefault();
        // ����httpPostԶ������ʵ��
        HttpDelete httpDelete = new HttpDelete(url);
        // �����������ʵ��
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// ����������������ʱʱ��
                .setConnectionRequestTimeout(35000)// ������������ʱʱ��
                .setSocketTimeout(60000)// ���ö�ȡ�������ӳ�ʱʱ��
                .build();
        // ΪhttpPostʵ����������
        httpDelete.setHeader("Authorization", token);
        httpDelete.setConfig(requestConfig);
        // ��������ͷ
        httpDelete.addHeader("Content-Type", "application/json");
        try {
            // httpClient����ִ��post����,��������Ӧ��������
            httpResponse = httpClient.execute(httpDelete);
            // ����Ӧ�����л�ȡ��Ӧ����
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // �ر���Դ
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    public static String mapToGetString(Map<String, String> paramsMap){
        if (paramsMap == null) {
            return null;
        }
        List<BasicNameValuePair> paramsTmp = new ArrayList<BasicNameValuePair>();
        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            paramsTmp.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        String paramsStr = null;
        try {
            paramsStr = EntityUtils.toString(new UrlEncodedFormEntity(paramsTmp, Consts.UTF_8));
            System.out.println(paramsStr);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return paramsStr;
    }

    public static String resolveResponsString(String response){
        String res = null;
        return res;
    }

    public static void main(String[] args) {

    }
}
