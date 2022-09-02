import json

import BaasUtil


def StoreRecord(UserId: str, ProposalId: str, RunId: str, jsonofMeta: str = ''):
    result = BaasUtil.invokeSmartContractCode(
        SmartContractCodeName="DataRecord",
        functionName="StoreRecord",
        args="%s,%s,%s,%s" % (UserId, ProposalId, RunId, BaasUtil.StringToEncodedJsonFun(jsonofMeta))
    )
    print("-----result:%s" % result)
    jsonObject = json.loads(result)  # result是json字符串，转为json结构体
    resData = jsonObject.get('transactionID')
    return resData  # 返回交易id


# 查询数据
def QueryRecord(UserId: str, ProposalId: str, RunId: str):
    res = BaasUtil.querySmartContractCode(
        SmartContractCodeName="DataRecord",
        functionName="QueryRecord",
        args=UserId + "%" + ProposalId + "%" + RunId
    )
    print(res)
    jsonObject = json.loads(res)
    result = jsonObject.get('value')
    print(result)
    return result


# 数据上链，数据json字符串格式
def StoreRecord2(jsonOfMeta: str):
    result = BaasUtil.invokeSmartContractCode(
        SmartContractCodeName="DataRecord2",
        functionName="StoreRecord",
        args=BaasUtil.StringToEncodedJsonFun(jsonOfMeta)
    )
    print("-----result:%s" % result)
    jsonObject = json.loads(result)
    resData = jsonObject.get('transactionID')
    return resData  # 返回交易id


def QueryRecord2(jsonOfMeta: str):
    res = BaasUtil.querySmartContractCode(
        SmartContractCodeName="DataRecord2",
        functionName="QueryRecord",
        args=BaasUtil.StringToEncodedJsonFun(jsonOfMeta)
    )
    jsonObject = json.loads(res)
    result = jsonObject.get('data')
    print(result)
    return result


if __name__ == "__main__":
    StoreRecord2("{\"id\":\"iii\",\"runId\":\"rrr\",\"proposalId\":\"ppp\",\"characteristicsHash\":\"jio1j2903j12093j1203\",\"creator\":\"abc@ihep.ia.ac\",\"certTime\":168919820198300,\"uploadTime\":160910283019900,\"filename\":\"22222.nexus\"}")
    # QueryRecord2("{\"id\":\"iii\",\"runId\":\"rrr\",\"proposalId\":\"ppp\"}")
