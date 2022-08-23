/*
 * 数据备案
 */

 package main

 import (

	 "encoding/json"
	 "encoding/base64"
	 "fmt"

	 "github.com/hyperledger/fabric/core/chaincode/shim"
	 pb "github.com/hyperledger/fabric/protos/peer"
 )

 // SimpleChaincode example simple Chaincode implementation
 type SimpleChaincode struct {
 }

 type Chaincode struct {
 }
//要打包的结构体
 type jsonofMetaformat struct {
	 Id string
	 RunId string
	 ProposalId string
	 CharacteristicsHash string
	 Creator string
	 CreateTime int64
	 UploadTime int64
	 Filename string
 }

 //err
 var err error

 //SimpleChaincode Init方法
 func (t *SimpleChaincode) Init(stub shim.ChaincodeStubInterface) pb.Response {
	 fmt.Println("----- chaincode Init")
	 return shim.Success(nil)
 }

 //invoke,func,
 func (t *SimpleChaincode) Invoke(stub shim.ChaincodeStubInterface) pb.Response {
	 fmt.Println("----- chaincode Invoke")
	 function, args := stub.GetFunctionAndParameters()

	 if len(args) < 1 {
		 return shim.Error("Incorrect number of arguments. Expecting at least 1")
	 }
	 //不同的函数调用
	 switch {
	 case function == "StoreRecord":
		 return t.StoreRecord(stub, args)//存数据
	 case function == "QueryRecord":
		 return t.QueryRecord(stub, args)//查询数据
	 default:
		 fmt.Printf("function is not exist\n")
	 }

	 return shim.Error("InvalId invoke function name. Expecting \"submit_data\" \"query\"")
 }

 /*
	  args:"JsonofMeta"
	  JsonofMeta:RUN号对应的特征指纹
	  {
			"Id": "ksoj091u3h123",
			"RunId": "a9123jb",
			"ProposalId": "ksdjafj10293123",
			"CharacteristicsHash": "jio1j2903j12093j1203",
			"Creator": "abc@ihep.ia.ac",
			"CreateTime": 168919820198300,
			"UploadTime": 160910283019900,
			"Filename": "xxx_run001_pop001.nexus"
		}
	 输出：
	 {
		 success: bool,
		 tranID: string
	 }
  */
  //存数据
 func (t *SimpleChaincode) StoreRecord(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	 fmt.Println("StoreRecord")

	 if len(args) != 1 {
		 return shim.Error("Incorrect number of arguments. Expecting 1")
	 }
	 // creatorByte, _ := stub.GetCreator()
	 // creatorName := getUserWithCert(creatorByte)
	 // fmt.Println("creatorName: ", creatorName)
	 jsonofMetaBytes, err := base64.StdEncoding.DecodeString(args[0]) //JsonofMeta是对json（Key,value）编码后的字符串
	 if err != nil {
		 fmt.Println("base64.StdEncoding.DecodeString err:", err)
		 fmt.Println("jsonofMeta 解码失败")
		 return shim.Error("jsonofMeta 解码失败")
	 }
	 _jsonofMetaformat := jsonofMetaformat{}
	 json.Unmarshal(jsonofMetaBytes, &_jsonofMetaformat)//反序列化，把json字符串解析成真正的json结构体
	 keyStr := _jsonofMetaformat.Id + "%" + _jsonofMetaformat.RunId + "%" + _jsonofMetaformat.ProposalId

	 err = stub.PutState(keyStr, []byte(string(jsonofMetaBytes)))//上链
	 if err != nil {
		 return shim.Error(err.Error())
	 }
	 // fmt.Println("submit_data success \n", string(data_value))
	 return shim.Success([]byte(args[0]))
 }


 /*
	 数据比对之前的查询
	 {
			"Id": "ksoj091u3h123",
			"RunId": "a9123jb",
			"ProposalId": "ksdjafj10293123",
		}
	 args: “JsonofMeta”
	 输出：
	 {
		 success: bool,
		 JsonofMeta: string
	 }
 */
 //查数据
 func (t *SimpleChaincode) QueryRecord(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	 fmt.Println("----- QueryRecord  \n")

	 if len(args) != 1 {
		fmt.Printf("args =",args)
		 return shim.Error("Incorrect number of arguments. Expecting 1")
	 }
	 jsonofMetaBytes, err := base64.StdEncoding.DecodeString(args[0])//参数决定了key
	 if err != nil {
		 fmt.Println("base64.StdEncoding.DecodeString err:", err)
		 fmt.Println("jsonofMeta 解码失败")
		 return shim.Error("jsonofMeta 解码失败")
	 }

	 _jsonofMetaformat := jsonofMetaformat{}
	 json.Unmarshal(jsonofMetaBytes, &_jsonofMetaformat)//反序列化
	 keyStr := _jsonofMetaformat.Id + "%" + _jsonofMetaformat.RunId + "%" + _jsonofMetaformat.ProposalId
	 // data_key, err := base64.StdEncoding.DecodeString(args[0])

	 data_value, err := stub.GetState(string(keyStr))//从区块链根据key获取value
	 if err != nil {
		 return shim.Error(err.Error())
	 }
	 if data_value == nil {
		 fmt.Println("{\"Error\":\"Nil amount for\"}" + string(keyStr) + "\"}")
		 return shim.Error("{\"Error\":\"Nil amount for\"}" + string(keyStr) + "\"}")
	 }
	 fmt.Println("{\"data_key\":\"" + string(keyStr) + "\",\"data_value\":\"" + string(data_value) + "\"}")

	 return shim.Success(data_value)

 }

 // 从证书中获取用户名称
 // func getUserWithCert(creatorByte []byte) string {
 // 	certStart := bytes.IndexAny(creatorByte, "-----BEGIN")
 // 	if certStart == -1 {
 // 		fmt.Errorf("No certificate found")
 // 	}
 // 	certText := creatorByte[certStart:]
 // 	fmt.Println(string(certText))
 // 	fmt.Println(certText)
 // 	bl, _ := pem.Decode(certText)
 // 	if bl == nil {
 // 		fmt.Errorf("Could not decode the PEM structure")
 // 	}
 // 	cert, err := x509.ParseCertificate(bl.Bytes)
 // 	if err != nil {
 // 		fmt.Errorf("ParseCertificate failed")
 // 	}
 // 	uname := cert.Subject.CommonName
 // 	fmt.Println("Name:" + uname)
 // 	return uname
 // }

 func main() {
	 err := shim.Start(new(SimpleChaincode))
	 if err != nil {
		 fmt.Printf("Error starting Simple chaincode: %s", err)
	 }
 }
