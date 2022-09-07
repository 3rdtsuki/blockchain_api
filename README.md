#### 文件说明

主目录下是python编写的区块链平台客户端程序

java_ver目录下是java编写的区块链平台客户端程序

DataRecord2.go是智能合约，部署在区块链平台baas上

#### baas平台运行方法

开启虚拟机k8s-master-worker

```sh
k8s开机自动启动内部的容器
```

开启虚拟机baas

```sh
root@ubuntu:~/baas-iie20180910# configtxlator_14 start

root@ubuntu:~/baas-iie20180910/user-dashboard/src# npm run start

root@ubuntu:~/baas-iie20180910/src/# python dashboard.py 
```

