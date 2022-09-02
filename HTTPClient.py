import requests


# 发送get请求，将参数拼接在url后面
def doGet(token: str, url: str, params: str) -> str:
    """
    :param token:
    :param url:
    :param params: json字符串
    :return:
    """
    headers = {"token": token,
               "Content-Type": "application/json"
               }
    url = url + "?" + params
    print(url)
    # data:json字符串，json：字典
    result = requests.get(url=url,data=params,headers=headers).text
    return result


# post一个json结构体
def doPost(token: str, url: str, params: str):
    headers = {"token": token,
               "Content-Type": "application/json"
               }
    print("-----Token2:" + token)
    print("-----params:", params)
    result = requests.post(url=url, data=params, headers=headers)
    return result.text

# map转为 args=3&SmartContractCodeName=1&functionName=2 格式
def mapToGetString(paramsMap: dict) -> str:
    paramStr = ''
    for key in paramsMap.keys():
        paramStr += "%s=%s&" % (key, paramsMap[key])
    paramStr = paramStr[:-1]
    return paramStr

if __name__=="__main__":
    doGet("","https://www.baidu.com","")