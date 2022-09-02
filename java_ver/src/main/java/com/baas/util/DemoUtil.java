package com.baas.util;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DemoUtil {
    public static void main(String[] args){
        StoreRecord2("{\"id\":\"iii\",\"runId\":\"rrr\",\"proposalId\":\"ppp\",\"characteristicsHash\":\"jio1j2903j12093j1203\",\"creator\":\"abc@ihep.ia.ac\",\"certTime\":168919820198300,\"uploadTime\":160910283019900,\"filename\":\"22222.nexus\"}");
//        QueryRecord2("{\"id\":\"iii\",\"runId\":\"rrr\",\"proposalId\":\"ppp\"}");
    }

    /**
     * @description: ����ָ�Ʊ���
     * @param UserId �û�ΨһID
     * @param ProposalId �᰸��
     * @param RunId RUN��
     * @param jsonofMeta ����������
     * @return {String} Json�ṹ��Ϣ��������ID
     */
    public static String StoreRecord(String UserId, String ProposalId, String RunId, String jsonofMeta) {
        String result = "";
        if (jsonofMeta == null || jsonofMeta.isEmpty()) {
            //���ܺ�ԼΪDataRecord�����������StoreRecord����
            result = BaasUtil.invokeSmartContractCode("DataRecord", "StoreRecord",
                    UserId + "," + ProposalId + "," + RunId);
        } else {
            result = BaasUtil.invokeSmartContractCode("DataRecord", "StoreRecord",
                    UserId + "," + ProposalId + "," + RunId + "," + BaasUtil.StringToEncodedJsonFun(jsonofMeta));
        }
        System.out.println("-----result:"+result);
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(result);
        //JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();
        try {
            String resData = jsonObject.get("transactionID").getAsString();
            return resData;
        } catch (Exception e) {
            //TODO: handle exception
            System.err.println(e);
            return "";
        }
    }

    /**
     * @description: ����ָ�Ʊ���
     * @param jsonofMeta ����������
     * @return {String} Json�ṹ��Ϣ��������ID
     * 11->2
     */
    public static String StoreRecord2(String jsonofMeta) {
        String result = "";
        if (jsonofMeta == null || jsonofMeta.isEmpty()) {
            result = BaasUtil.invokeSmartContractCode("DataRecord2", "StoreRecord");
        } else {
            result = BaasUtil.invokeSmartContractCode("DataRecord2", "StoreRecord",
                    BaasUtil.StringToEncodedJsonFun(jsonofMeta));
        }
        System.out.println("-----result:"+result);
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(result);
        //JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();
        try {
            String resData = jsonObject.get("transactionID").getAsString();
            return resData;
        } catch (Exception e) {
            //TODO: handle exception
            System.err.println(e);
            return "";
        }
    }

    /**
     * @description: ���ϱ�����Ϣ��ѯ
     * @param UserId �û�ΨһID
     * @param ProposalId �᰸��
     * @param RunId RUN��
     * @return {String} Json�ṹ��Ϣ���������ϱ�����Ϣ
     */
    public static String QueryRecord(String UserId, String ProposalId, String RunId) {
        String result = "";
        String res = BaasUtil.querySmartContractCode("DataRecord", "QueryRecord",
                UserId + "," + ProposalId + "," + RunId);
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(res);
        try {
            System.out.println(jsonObject);
            result = jsonObject.get("data").getAsString();
        } catch (Exception e) {
            //TODO: handle exception
            System.err.println(e);
        }
        System.out.println(result);
        return result;
    }

    /**
     * @description: ���ϱ�����Ϣ��ѯ
     * @param  jsonofMeta
     * @return {String} Json�ṹ��Ϣ���������ϱ�����Ϣ
     * 11->2
     */
    public static String QueryRecord2(String jsonofMeta) {
        String result = "";
        String res = BaasUtil.querySmartContractCode("DataRecord2", "QueryRecord",
                BaasUtil.StringToEncodedJsonFun(jsonofMeta));
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(res);
        try {
            result = jsonObject.get("data").getAsString();
        } catch (Exception e) {
            //TODO: handle exception
            System.err.println(e);
        }
        System.out.println(result);
        return result;
    }

    /**
     * @description: ���������϶�
     * @param UserId �û�ΨһID
     * @param ProposalId �᰸��
     * @param RunId RUN��
     * @param jsonofMeta ����������
     * @return {String} Json�ṹ��Ϣ��������ID
     */
    public static String StoreQuality(String UserId, String ProposalId, String RunId, String jsonofMeta) {
        String result = "";
        if (jsonofMeta == null || jsonofMeta.isEmpty()) {
            result = BaasUtil.invokeSmartContractCode("QualityCertification", "StoreQuality", UserId + "," + ProposalId + "," + RunId);
        } else {
            result = BaasUtil.invokeSmartContractCode("QualityCertification", "StoreQuality", UserId + "," + ProposalId + "," + RunId + "," + BaasUtil.StringToEncodedJsonFun(jsonofMeta));
        }
        System.out.println("-----result:"+result);
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(result);
        try {
            String resData = jsonObject.get("transactionID").getAsString();
            return resData;
        } catch (Exception e) {
            //TODO: handle exception
            System.err.println(e);
            return "";
        }
    }

    /**
     * @description: ���������϶�
     * @param jsonofMeta ����������
     * @return {String} Json�ṹ��Ϣ��������ID
     */
    public static String StoreQuality2(String jsonofMeta) {
        String result = "";
        if (jsonofMeta == null || jsonofMeta.isEmpty()) {
            result = BaasUtil.invokeSmartContractCode("QualityCertification2", "StoreQuality");
        } else {
            result = BaasUtil.invokeSmartContractCode("QualityCertification2", "StoreQuality", BaasUtil.StringToEncodedJsonFun(jsonofMeta));
        }
        System.out.println("-----result:"+result);
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(result);
        //JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();
        try {
            String resData = jsonObject.get("transactionID").getAsString();
            return resData;
        } catch (Exception e) {
            //TODO: handle exception
            System.err.println(e);
            return "";
        }
    }



    /**
     * @description: ���������϶���Ϣ��ѯ
     * @param UserId �û�ΨһID
     * @param ProposalId �᰸��
     * @param RunId RUN��
     * @return {String} Json�ṹ��Ϣ��������ID
     */
    public static String QueryQuality(String UserId, String ProposalId, String RunId) {
        String result = "";
        String res = BaasUtil.querySmartContractCode("QualityCertification", "QueryQuality", UserId + "," + ProposalId + "," + RunId);
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(res);
        try {
            result = jsonObject.get("data").getAsString();
        } catch (Exception e) {
            //TODO: handle exception
            System.err.println(e);
        }
        System.out.println(result);
        return result;
    }

    /**
     * @description: ���������϶���Ϣ��ѯ
     * @param jsonofMeta
     * @return {String} Json�ṹ��Ϣ��������ID
     */
    public static String QueryQuality2(String jsonofMeta) {
        String result = "";
        String res = BaasUtil.querySmartContractCode("QualityCertification2", "QueryQuality", BaasUtil.StringToEncodedJsonFun(jsonofMeta));
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(res);
        try {
            result = jsonObject.get("data").getAsString();
        } catch (Exception e) {
            //TODO: handle exception
            System.err.println(e);
        }
        System.out.println(result);
        return result;
    }

    /**
     * @description: �ɹ���������
     * @param UserId �û�ΨһID
     * @param ProposalId �᰸��
     * @param RunId RUN��
     * @param jsonofMeta ����������
     * @return {String} Json�ṹ��Ϣ��������ID
     */
    public static String StoreResult(String UserId, String ProposalId, String RunId, String jsonofMeta) {
        String result = "";
        if (jsonofMeta == null || jsonofMeta.isEmpty()) {
            result = BaasUtil.invokeSmartContractCode("ResultFeedback", "StoreResult", UserId + "," + ProposalId + "," + RunId);
        } else {
            result = BaasUtil.invokeSmartContractCode("ResultFeedback", "StoreResult", UserId + "," + ProposalId + "," + RunId + "," + BaasUtil.StringToEncodedJsonFun(jsonofMeta));
        }
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(result);
        try {
            String resData = jsonObject.get("transactionID").getAsString();
            return resData;
        } catch (Exception e) {
            //TODO: handle exception
            System.err.println(e);
            return "";
        }
    }

    /**
     * @description: �ɹ���������
     * @param jsonofMeta ����������
     * @return {String} Json�ṹ��Ϣ��������ID
     */
    public static String StoreResult2(String jsonofMeta) {
        String result = "";
        if (jsonofMeta == null || jsonofMeta.isEmpty()) {
            result = BaasUtil.invokeSmartContractCode("ResultFeedback2", "StoreResult");
        } else {
            result = BaasUtil.invokeSmartContractCode("ResultFeedback2", "StoreResult", BaasUtil.StringToEncodedJsonFun(jsonofMeta));
        }
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(result);
        try {
            String resData = jsonObject.get("transactionID").getAsString();
            return resData;
        } catch (Exception e) {
            //TODO: handle exception
            System.err.println(e);
            return "";
        }
    }

    /**
     * @description: �ɹ�����������Ϣ��ѯ
     * @param UserId �û�ΨһID
     * @param ProposalId �᰸��
     * @param RunId RUN��
     * @return {String} Json�ṹ��Ϣ�������ɹ�������Ϣ
     */
    public static String QueryResult(String UserId, String ProposalId, String RunId) {
        String result = "";
        String res = BaasUtil.querySmartContractCode("ResultFeedback", "QueryResult", UserId + "," + ProposalId + "," + RunId);
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(res);
        try {
            result = jsonObject.get("data").getAsString();
        } catch (Exception e) {
            //TODO: handle exception
            System.err.println(e);
        }
        System.out.println(result);
        return result;
    }

    /**
     * @description: �ɹ�����������Ϣ��ѯ
     * @param jsonofMeta
     * @return {String} Json�ṹ��Ϣ�������ɹ�������Ϣ
     */
    public static String QueryResult2(String jsonofMeta) {
        String result = "";
        String res = BaasUtil.querySmartContractCode("ResultFeedback2", "QueryResult",
                BaasUtil.StringToEncodedJsonFun(jsonofMeta));
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(res);
        try {
            result = jsonObject.get("data").getAsString();
        } catch (Exception e) {
            //TODO: handle exception
            System.err.println(e);
        }
        System.out.println(result);
        return result;
    }
}

