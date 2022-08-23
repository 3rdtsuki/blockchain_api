import json

import BaasUtil

def StoreRecord(UserId: str, ProposalId: str, RunId: str, jsonofMeta: str=''):
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
        SmartContractCodeName="demo",
        functionName="QueryRecord",
        args=UserId + "%" + ProposalId + "%" + RunId
    )
    print(res)
    jsonObject = json.loads(res)
    result = jsonObject.get('value')
    print(result)
    return result

if __name__=="__main__":
    QueryRecord(UserId="uuuuuu",ProposalId="pppppp",RunId="rrrrrr")