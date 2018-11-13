# education
本地在线文档 http://localhost:8080/swagger-ui.html

#### docker 安装
参考（https://www.cnblogs.com/baolong/p/5743420.html）

### 安装　mongodb
```shell
# ~~**docker run --name mongodb -p 27017:27017 -v /data/mongodb/data/custom:/etc/mongo -v /data/mongodb/config/mongod.conf:/etc/mongo/mongod.conf -d --restart always --privileged=true --config /etc/mongo/mongod.conf mongo:3.1**~~
# 1. 拉取　docker mongo:3.2
docker pull mongo:3.2
# 2. 运行
# 现在使用
docker run -p 27017:27017 -v /data/mongodb/data:/data/db　--restart=always -d  mongo:3.2　
# ３． 修改账号信息

``` 


### 安装 docker -> fastdfs
(https://www.imooc.com/article/66981?block_id=tuijian_wz)