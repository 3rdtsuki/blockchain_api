import requests


# 发送get请求，将参数拼接在url后面
def doGet(token: str, url: str, params: dict) -> str:
    """
    :param token:
    :param url:
    :param params: json字符串
    :return:
    """
    headers = {"token": token
               }
    reqParams = mapToGetString(params)
    url = url + "?" + reqParams
    print(url)
    params["token"]=token
    # data:json字符串，json：字典
    result = requests.get(url=url,json=params)
    return result.text


def doPost(token: str, url: str, params: dict):
    headers = {"token": token
               }
    print("-----Token2:" + token)
    print("-----params:", params)
    params["token"]=token
    result = requests.post(url=url, json=params)
    return result.text

# map转为 args=3&SmartContractCodeName=1&functionName=2 格式
def mapToGetString(paramsMap: dict) -> str:
    paramStr = ''
    for key in paramsMap.keys():
        paramStr += "%s=%s&" % (key, paramsMap[key])
    paramStr = paramStr[:-1]
    return paramStr
