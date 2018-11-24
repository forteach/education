#### `education`
本地在线文档 http://localhost:8080/swagger-ui.html

##### `docker` 安装
参考（https://www.cnblogs.com/baolong/p/5743420.html）

##### 安装　`mongodb`
```shell
# ~~**docker run --name mongodb -p 27017:27017 -v /data/mongodb/data/custom:/etc/mongo -v /data/mongodb/config/mongod.conf:/etc/mongo/mongod.conf -d --restart always --privileged=true --config /etc/mongo/mongod.conf mongo:3.1**~~
# 1. 拉取　docker mongo:3.2
docker pull mongo:3.2
# 2. 运行
# 现在使用
docker run -p 27017:27017 -v /data/mongodb/data:/data/db　--restart=always -d  mongo:3.2　
# ３． 修改账号信息

``` 


##### 安装 `docker` -> `fastdfs`
(https://www.imooc.com/article/66981?block_id=tuijian_wz)

##### 搭建私有`docker` 仓库

> 下载镜像

```shell
docker pull registry
```

> 启动镜像

```shell
docker run -d -p 5000:5000 -v /data/myregistry:/var/lib/registry registry
# 验证
curl -X GET http://127.0.0.1:5000/v2/_catalog
```

> 修改配置文件开启http支持，默认是开启https

```shell
# 修改服务端配置文件
vim /etc/sysconfig/docker
------------------------------
other_args="--exec-driver=lxc --selinux-enabled --insecure-registry 100.90.61.14:5000"
DOCKER_CERT_PATH=/etc/docker
------------------------------
# 说明　--insecure-registry 100.90.61.14:5000，表示开启5000端口的非安全模式，也就是http模式

# 客户端和服务端修改添加这句话
vim /etc/docker/daemon.json
## 添加下面这句话没有话新建对应的文件，　registriy 为docker 仓库所在服务器ip
{
    "insecure-registries": ["registriy:5000"]
}
#　重启服务　
service docker restart
```

> 本地镜像上传到私有仓库

[参考](https://www.cnblogs.com/kangoroo/p/7994801.html)[https://www.cnblogs.com/kangoroo/p/7994801.html]

