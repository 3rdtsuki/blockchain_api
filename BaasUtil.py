import json
import base64
import HTTPClient
import config

BASE_URL = config.BASE_URL
TOKEN = config.TOKEN


# 使用post提交
def invokeSmartContractCode(SmartContractCodeName: str, functionName: str, args: str) -> str:
    api = "/api/v1/invokeSmartContractCode"
    url = "%s%s" % (BASE_URL, api)
    data = {"SmartContractCodeName": SmartContractCodeName,
            "functionName": functionName,
            "args": args
            }
    params = json.dumps(data)  # 把data转为json字符串
    print("-----params:"+params)
    print("-----Token1:"+TOKEN)
    print("-----url:"+url)
    res = HTTPClient.doPost(TOKEN,url,params)
    # print(res)
    return res

# 使用get查询
def querySmartContractCode(SmartContractCodeName: str, functionName: str, args: str) -> str:
    api = "/api/v1/querySmartContractCode"
    url = "%s%s" % (BASE_URL, api)
    paramsMap = {"args": args,
                 "functionName": functionName,
                 "SmartContractCodeName": SmartContractCodeName
                 }
    reqParams = HTTPClient.mapToGetString(paramsMap)
    print(reqParams)
    res = HTTPClient.doGet(TOKEN, url, reqParams)
    # print(res)# 然后存到数据库
    return res


# json字符串转为base64编码
def StringToEncodedJsonFun(param: str) -> str:
    paramBytes = param.encode('utf-8')  # 将json字符串先转为bytes
    encodedParam = str(base64.b64encode(paramBytes), encoding='utf-8')  # 再用base64编码，并化为str，去掉前b
    return encodedParam

def base64ToString(base64Str: str)->str:
    return base64.b64decode(base64Str).decode('utf-8')
if __name__ == '__main__':
    # invokeSmartContractCode('scc', 'f', 'a')
    # querySmartContractCode('scc', 'f', 'a')
    # print(StringToEncodedJsonFun('{"Id":"uuuuuu","RunId": "rrrrrr", "ProposalId": "pppppp","CharacteristicsHash": "cccccc","Creator": "abc@ihep.ia.ac","CreateTime": 168919820198300,"UploadTime": 160910283019900,"Filename": "xxx_run001_pop001.nexus"}'))
    print(StringToEncodedJsonFun('{"Id":"uuuuuu","RunId": "rrrrrr", "ProposalId": "pppppp"}'))