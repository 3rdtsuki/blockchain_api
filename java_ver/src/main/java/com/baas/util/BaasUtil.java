package com.baas.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.yaml.snakeyaml.Yaml;
import org.apache.commons.codec.binary.Base64;

public class BaasUtil {
    static String BASE_URL = String.valueOf(getYamlMap().get("BASE_URL"));
    // static String BASE_URL = getYamlMap().get("BASE_URL");
    static String TOKEN = String.valueOf(getYamlMap().get("TOKEN"));
    // final static String CONFIG_PATH_STRING = "\\target\\classes\\";
    // final static String CONFIG_PATH_STRING = "\\src\\main\\resources\\";
    final static String CONFIG_PATH_STRING = "/src/main/resources/";

    // static String TOKEN =
    // "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IjMzMyIsIm9yZ25hbWUiOiJmMyIsImlhdCI6MTYwODc4MzAyNCwiZXhwIjoxNjQwMzE5MDI0fQ.w1zkRtuEFSUgGSEb6nKGlFR4lkFM7cASlxZ6lxJ1IXQ";
    /**
     * @description: ��ѯ���п��ü�Ⱥ
     * @param {*}
     * @return {res}
     */
    public static String clusterList() {
        String api = "/api/v1/clusterlist";
        String url = String.format("%s%s", BASE_URL, api);
        String res = HttpClient.doGet(TOKEN, url);
        return res;
    }

    /**
     * @description: ����������
     * @param {String} chainame ����������
     * @param {String} clusterName ��Ⱥ����
     * @param {int}    peerNum �ڵ����
     * @param {String} type ��ʶ�㷨
     * @return {res}
     */
    public static String createChain(String chainame, String clusterName, String type, String fabricVersion,
                                     String databaseType) {
        String api = "/api/v1/createChain";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> chainData = new HashMap<String, String>();
        chainData.put("chainame", chainame);
        chainData.put("clustername", clusterName);
        chainData.put("type", type);
        chainData.put("fabricVersion", fabricVersion);
        chainData.put("databaseType", databaseType);
        String params = new Gson().toJson(chainData);
        String res = HttpClient.doPost(TOKEN, url, params);
        return res;
    }

    /**
     * @description: �鿴��ǰ�û�������������Ϣ
     * @param {*}
     * @return {res}
     */
    public static String listChains() {
        String api = "/api/v1/listChains";
        String url = String.format("%s%s", BASE_URL, api);
        String res = HttpClient.doGet(TOKEN, url);
        return res;
    }

    /**
     * @description: �鿴ĳ���������ϵĽڵ�
     * @param {String} chainame ����������
     * @return {res}
     */
    public static String peerList(String chainame) {
        String api = "/api/v1/peerlist";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("chainame", chainame);
        String reqParams = HttpClient.mapToGetString(paramsMap);
        String res = HttpClient.doGet(TOKEN, url, reqParams);
        return res;
    }

    /**
     * @description: �鿴ĳ�������������г�Ա
     * @param {String} chainame ����������
     * @return {res}
     */
    public static String chainMember(String chainame) {
        String api = "/api/v1/chainMember";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("chainame", chainame);
        String reqParams = HttpClient.mapToGetString(paramsMap);
        String res = HttpClient.doGet(TOKEN, url, reqParams);
        return res;
    }

    /**
     * @description: �鿴ĳ�������������нڵ�֤��
     * @param {String} chainame ����������
     * @return {res}
     */
    public static String chainCert(String chainame) {
        String api = "/api/v1/chainCert";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("chainame", chainame);
        String reqParams = HttpClient.mapToGetString(paramsMap);

        String res = HttpClient.doGet(TOKEN, url, reqParams);
        return res;
    }

    /**
     * @description: Ϊָ�����������һ��peer�ڵ�
     * @param {String} chainame
     * @return {*}
     */
    public static String addPeer(String chainame) {
        String api = "/api/v1/addPeer";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> data = new HashMap<String, String>();
        data.put("chainame", chainame);
        String params = new Gson().toJson(data);
        String res = HttpClient.doPost(TOKEN, url, params);
        return res;
    }

    /**
     * @description: �˳�ָ��������
     * @param {String} chainame
     * @return {*}
     */
    public static String deletePhyChain(String chainame) {
        String api = "/api/v1/deletePhyChain";
        String url = String.format("%s%s/%s", BASE_URL, api, chainame);
        String res = HttpClient.doDelete(TOKEN, url);
        return res;
    }

    /**
     * @description: �����߼���
     * @param {String} logicName �߼�������
     * @param {String} chainame ����������
     * @param {String} votepolicy ͶƱ����
     * @param {String} descrption ����
     * @return {*}
     */
    public static String createChannel(String logicName, String chainame, String votepolicy, String description) {
        String api = "/api/v1/createChannel";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> data = new HashMap<String, String>();
        data.put("logicName", logicName);
        data.put("chainame", chainame);
        data.put("votepolicy", votepolicy);
        data.put("description", description);
        String params = new Gson().toJson(data);
        String res = HttpClient.doPost(TOKEN, url, params);
        return res;
    }

    /**
     * @description: �����߼���
     * @param {String} logicName �߼�������
     * @param {String} chainame ����������
     * @param {String} votepolicy ͶƱ����
     * @return {*}
     */
    public static String createChannel(String logicName, String chainame, String votepolicy) {
        String api = "/api/v1/createChannel";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> data = new HashMap<String, String>();
        data.put("logicName", logicName);
        data.put("chainame", chainame);
        data.put("votepolicy", votepolicy);
        String params = new Gson().toJson(data);
        String res = HttpClient.doPost(TOKEN, url, params);
        return res;
    }

    /**
     * @description: ��ѯ��ǰ�û�����������
     * @param {*}
     * @return {res}
     */
    public static String queryAllChannel() {
        String api = "/api/v1/queryAllChannel";
        String url = String.format("%s%s", BASE_URL, api);
        String res = HttpClient.doGet(TOKEN, url);
        return res;
    }

    /**
     * @description: ��ѯ�߼�����Ա��Ϣ
     * @param {String} channelname
     * @return {*}
     */
    public static String queryChannelMember(String channelname) {
        String api = "/api/v1/queryChannelMember";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("channelname", channelname);
        String reqParams = HttpClient.mapToGetString(paramsMap);
        String res = HttpClient.doGet(TOKEN, url, reqParams);
        return res;
    }

    /**
     * @description: ����id��ѯ�߼���
     * @param {String} id
     * @return {*}
     */
    public static String queryChannelById(String id) {
        String api = "/api/v1/queryChannelById";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("id", id);
        String reqParams = HttpClient.mapToGetString(paramsMap);
        String res = HttpClient.doGet(TOKEN, url, reqParams);
        return res;
    }

    /**
     * @description: �������Ʋ�ѯ�߼���
     * @param {String} chainame
     * @return {*}
     */
    public static String queryChannelByName(String channelname) {
        String api = "/api/v1/queryChannelByName";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("channelname", channelname);
        String reqParams = HttpClient.mapToGetString(paramsMap);
        String res = HttpClient.doGet(TOKEN, url, reqParams);
        return res;
    }

    /**
     * @description: ���������Ų���������Ϣ
     * @param {String} channelname
     * @param {int}    blockNumber
     * @return {*}
     */
    public static String queryBlockByNumber(String channelname, int blockNumber) {
        String api = "/api/v1/queryBlockByNumber";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("channelname", channelname);
        paramsMap.put("blockNumber", String.valueOf(blockNumber));
        String reqParams = HttpClient.mapToGetString(paramsMap);
        String res = HttpClient.doGet(TOKEN, url, reqParams);
        return res;
    }

    /**
     * @description: ���������Ų���������Ϣ
     * @param {String} channelname
     * @param {int}    beginNum ������ʼ���
     * @param {int}    endNum ����������
     * @return {*}
     */
    public static String queryBlocksByRange(String channelname, int beginNum, int endNum) {
        String api = "/api/v1/queryBlocksByRange";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("channelname", channelname);
        paramsMap.put("beginNum", String.valueOf(beginNum));
        paramsMap.put("endNum", String.valueOf(endNum));
        String reqParams = HttpClient.mapToGetString(paramsMap);
        String res = HttpClient.doGet(TOKEN, url, reqParams);
        return res;
    }

    /**
     * @description: ʹ������hash��ѯ���������Ϣ
     * @param {String} channelname
     * @param {String} blockHash
     * @return {*}
     */
    public static String queryBlockByHash(String channelname, String blockHash) {
        String api = "/api/v1/queryBlockByHash";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("channelname", channelname);
        paramsMap.put("blockHash", blockHash);
        String reqParams = HttpClient.mapToGetString(paramsMap);
        String res = HttpClient.doGet(TOKEN, url, reqParams);
        return res;
    }

    /**
     * @description: ����ʱ�䷶Χ��ѯ����
     * @param {String} channelname
     * @param {String} startDate ��ʼʱ��
     * @param {String} endDate ����ʱ��
     * @return {*}
     */
    public static String queryBlockByDate(String channelname, String startDate, String endDate) {
        String api = "/api/v1/queryBlockByDate";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("channelname", channelname);
        paramsMap.put("startDate", startDate);
        paramsMap.put("endDate", endDate);
        String reqParams = HttpClient.mapToGetString(paramsMap);
        String res = HttpClient.doGet(TOKEN, url, reqParams);
        return res;
    }

    /**
     * @description: ���������Ų�ѯ�����ڰ��������н�����Ϣ
     * @param {String} channelname
     * @param {int}    blockNumber
     * @return {*}
     */
    public static String queryBlockAllTransaction(String channelname, int blockNumber) {
        String api = "/api/v1/queryBlockAllTransaction";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("channelname", channelname);
        paramsMap.put("blockNumber", String.valueOf(blockNumber));
        String reqParams = HttpClient.mapToGetString(paramsMap);
        String res = HttpClient.doGet(TOKEN, url, reqParams);
        return res;
    }

    /**
     * @description: ���ݽ���id��ѯ������Ϣ
     * @param {String} channelname
     * @param {String} id
     * @return {*}
     */
    public static String queryTransactionById(String channelname, String id) {
        String api = "/api/v1/queryTransactionById";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("channelname", channelname);
        paramsMap.put("id", id);
        String reqParams = HttpClient.mapToGetString(paramsMap);
        String res = HttpClient.doGet(TOKEN, url, reqParams);
        return res;
    }

    /**
     * @description: ���ݽ��׷����߲�ѯ����
     * @param {String} channelname
     * @param {String} orgname ������֯��
     * @return {*}
     */
    public static String queryTxsByCreator(String channelname, String orgname) {
        String api = "/api/v1/queryTxsByCreator";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("channelname", channelname);
        paramsMap.put("orgname", orgname);
        String reqParams = HttpClient.mapToGetString(paramsMap);
        String res = HttpClient.doGet(TOKEN, url, reqParams);
        return res;
    }

    /**
     * @description: ����ʱ�䷶Χ��ѯ���������Ϣ
     * @param {String} channelname
     * @param {String} startDate
     * @param {String} endDate
     * @return {*}
     */
    public static String queryTxsByDate(String channelname, String startDate, String endDate) {
        String api = "/api/v1/queryTxsByDate";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("channelname", channelname);
        paramsMap.put("startDate", startDate);
        paramsMap.put("endDate", endDate);
        String reqParams = HttpClient.mapToGetString(paramsMap);
        String res = HttpClient.doGet(TOKEN, url, reqParams);
        return res;
    }

    /**
     * @description: ���ݽ����漰�ĺ�Լ���Ʋ�ѯ������Ϣ
     * @param {String} channelname
     * @param {String} scname ��Լ����
     * @return {*}
     */
    public static String queryTxsBySCName(String channelname, String scname) {
        String api = "/api/v1/queryTxsBySCName";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("channelname", channelname);
        paramsMap.put("scname", scname);
        String reqParams = HttpClient.mapToGetString(paramsMap);
        String res = HttpClient.doGet(TOKEN, url, reqParams);
        return res;
    }

    /**
     * @description: ��ѯĳ���߼������µ�ʮ��������Ϣ
     * @param {String} channelname
     * @return {*}
     */
    public static String queryLastTenTxs(String channelname) {
        String api = "/api/v1/queryLastTenTxs";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("channelname", channelname);
        String reqParams = HttpClient.mapToGetString(paramsMap);
        String res = HttpClient.doGet(TOKEN, url, reqParams);
        return res;
    }

    /**
     * @description: ��ѯ��ǰ�û����������ܺ�Լ
     * @param {*}
     * @return {*}
     */
    public static String querySmartContract() {
        String api = "/api/v1/querySmartContract";
        String url = String.format("%s%s", BASE_URL, api);
        String res = HttpClient.doGet(TOKEN, url);
        return res;
    }

    /**
     * @description: ��ѯ���ܺ�Լ�������
     * @param {*}
     * @return {*}
     */
    public static String queryDeploySmartContract() {
        String api = "/api/v1/queryDeploySmartContract";
        String url = String.format("%s%s", BASE_URL, api);
        String res = HttpClient.doGet(TOKEN, url);
        return res;
    }

    /**
     * @description: ��ѯĳ��Լ�������
     * @param {String} id
     * @return {*}
     */
    public static String queryDeployById(String id) {
        String api = "/api/v1/queryDeployById";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("id", id);
        String reqParams = HttpClient.mapToGetString(paramsMap);
        String res = HttpClient.doGet(TOKEN, url, reqParams);
        return res;
    }

    /**
     * @description: �ϴ����ܺ�Լ
     * @param {String} logicName �߼�������
     * @param {String} codeName ��Լ����
     * @param {String} description ����
     * @param {String} filePath ��Լ����·��(zip)
     * @return {*}
     */
    public static String smartContractCode(String logicName, String codeName, String description, String filePath) {
        String api = "/api/v1/smartContractCode";
        String url = String.format("%s%s/%s", BASE_URL, api, codeName);
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("logicName", logicName);
        paramsMap.put("description", description);
        String res = HttpClient.doPut(TOKEN, url, paramsMap, filePath);
        return res;
    }

    /**
     * @description: ��װ���ܺ�Լ
     * @param {String} channelname �߼�������
     * @param {String} SmartContractCodeName ��Լ����
     * @return {*}
     */
    public static String installSmartContractCode(String channelname, String SmartContractCodeName) {
        String api = "/api/v1/installSmartContractCode";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> data = new HashMap<String, String>();
        data.put("channelname", channelname);
        data.put("SmartContractCodeName", SmartContractCodeName);
        String params = new Gson().toJson(data);
        String res = HttpClient.doPost(TOKEN, url, params);
        return res;
    }

    /**
     * @description: ʵ������Լ���޲���
     * @param {String} SmartContractCodeName
     * @return {*}
     */
    public static String instantiateSmartContractCode(String SmartContractCodeName) {
        String api = "/api/v1/instantiateSmartContractCode";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> data = new HashMap<String, String>();
        data.put("SmartContractCodeName", SmartContractCodeName);
        String params = new Gson().toJson(data);
        String res = HttpClient.doPost(TOKEN, url, params);
        return res;
    }

    /**
     * @description: ʵ������Լ��������
     * @param {String} SmartContractCodeName
     * @param {String} args
     * @return {*}
     */
    public static String instantiateSmartContractCode(String SmartContractCodeName, String args) {
        String api = "/api/v1/instantiateSmartContractCode";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> data = new HashMap<String, String>();
        data.put("SmartContractCodeName", SmartContractCodeName);
        data.put("args", args);
        String params = new Gson().toJson(data);
        String res = HttpClient.doPost(TOKEN, url, params);
        return res;
    }

    /**
     * @description: �������ܺ�Լ���޲���
     * @param {String} SmartContractCodeName
     * @param {String} functionName
     * @return {*}
     */
    public static String invokeSmartContractCode(String SmartContractCodeName, String functionName) {
        String api = "/api/v1/invokeSmartContractCode";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> data = new HashMap<String, String>();
        data.put("SmartContractCodeName", SmartContractCodeName);
        data.put("functionName", functionName);
        String params = new Gson().toJson(data);
        String res = HttpClient.doPost(TOKEN, url, params);
        return res;
    }

    /**
     * @description: �������ܺ�Լ��Я������
     * @param {String} SmartContractCodeName
     * @param {String} functionName
     * @param {String} args
     * @return {*}
     */
    public static String invokeSmartContractCode(String SmartContractCodeName, String functionName, String args) {
        String api = "/api/v1/invokeSmartContractCode";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> data = new HashMap<String, String>();
        data.put("SmartContractCodeName", SmartContractCodeName);
        data.put("functionName", functionName);
        data.put("args", args);
        String params = new Gson().toJson(data);
        System.out.println("-----params:"+params);
        System.out.println("-----Token1:"+TOKEN);
        System.out.println("-----url:"+url);
        String res = HttpClient.doPost(TOKEN, url, params);
        return res;
    }

    /**
     * @description: ��ѯ���ܺ�Լ���޲���
     * @param {String} SmartContractCodeName
     * @param {String} functionName
     * @return {*}
     */
    public static String querySmartContractCode(String SmartContractCodeName, String functionName) {
        String api = "/api/v1/querySmartContractCode";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("SmartContractCodeName", SmartContractCodeName);
        paramsMap.put("functionName", functionName);
        String reqParams = HttpClient.mapToGetString(paramsMap);
        String res = HttpClient.doGet(TOKEN, url, reqParams);
        return res;
    }

    /**
     * @description: ��ѯ���ܺ�Լ��Я������
     * @param {String} SmartContractCodeName
     * @param {String} functionName
     * @param {String} args
     * @return {*}
     */
    public static String querySmartContractCode(String SmartContractCodeName, String functionName, String args) {
        String api = "/api/v1/querySmartContractCode";
        String url = String.format("%s%s", BASE_URL, api);
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("SmartContractCodeName", SmartContractCodeName);
        paramsMap.put("functionName", functionName);
        paramsMap.put("args", args);
        //mapתΪargs=3&SmartContractCodeName=1&functionName=2��ʽ
        String reqParams = HttpClient.mapToGetString(paramsMap);
        String res = HttpClient.doGet(TOKEN, url, reqParams);
        return res;
    }

    /**
     * @description: ����token����
     * @param {*}
     * @return {*}
     */
    public static boolean updateToken() {
        String api = "/api/v1/updateToken";
        String url = String.format("%s%s", BASE_URL, api);
        String res = HttpClient.doGet(TOKEN, url);
        String configPath = System.getProperty("user.dir") + CONFIG_PATH_STRING + "config.yaml";
        String token = null;
        try {
            System.out.println(res);
            JsonObject jsonObject = (JsonObject) new JsonParser().parse(res);
            token = jsonObject.get("token").getAsString();
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        if (token != null) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("BASE_URL", BASE_URL);
            map.put("TOKEN", token);
            setMapToYaml(map, configPath);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @description: ��ȡ�����ļ��е�����
     * @param {*}
     * @return {*}
     */
    public static Map<String, Object> getYamlMap() {
        Yaml yaml = new Yaml();
        String configPath = System.getProperty("user.dir") + CONFIG_PATH_STRING;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(String.valueOf(Paths.get(configPath, "config.yaml"))));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Map<String, Object> obj = yaml.load(inputStream);
        return obj;
    }

    /**
     * @description: ����map��ֵ�Ե�yaml�ļ���
     * @param {Map<String,Object>} map
     * @param {String} pathStr
     * @return {*}
     */
    public static void setMapToYaml(Map<String, Object> map, String pathStr){
        Yaml yaml = new Yaml();
        StringWriter writer = new StringWriter();
        yaml.dump(map, writer);
        System.out.println(writer.toString());
        writeInFile(pathStr, writer.toString());
    }

    /**
     * @description: д�ļ�
     * @param {String} pathStr
     * @param {String} content
     * @return {*}
     */
    private static void writeInFile(String pathStr, String content) {
        File file = new File(pathStr);
        Writer writer = null;
        StringBuilder outputString = new StringBuilder();
        try {
            outputString.append(content + "\r\n");
            writer = new FileWriter(file, false); // true��ʾ׷��
            writer.write(outputString.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * @FunctionName: StringToEncodedJsonFun
     * @Description: ʹ��base64����
     * @Param: [params]
     * @return: java.lang.String
     */

    public static String StringToEncodedJsonFun(String param){

        String encodedParam = null;
        Base64 base64 = new Base64();
        try{
            byte[] paramBytes = param.getBytes("UTF-8");
            encodedParam = base64.encodeToString(paramBytes);   //��json�ַ�������base64����
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
            return "error";
        }
        return encodedParam;
    }

    public static void main(String[] args) {

    }

}