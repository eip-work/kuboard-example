# 安装 Dashboard

## 前提

安装 eip-service-dashboard 时，必须保证：

* 已经有一个 kubernetes 集群
* 拥有对该 kubernetes 集群执行 kubectl 命令时的所有权限

如果没有 kubernetes 集群，可以有如下选项：

* 通过 阿里云 创建 kubernetes 容器服务，并获得和配置 kubectl 的访问参数
* 参考 [安装 kubernetes 1.14.3](../install-kubernetes/index.md)

## 安装

**获取并修改yaml文件**

```bash
sudo -i
su - gitlab-runner
wget https://raw.githubusercontent.com/eip-work/eip-monitor-repository/master/dashboard/eip-service-dashboard.yaml
```

修改文件 eip-service-dashboard.yaml 中 Ingress 的 host 为 dashboard.yourclustername.yourdomain.com

**执行安装**

```bash
kubectl apply -f eip-service-dashboard.yaml 
```

## 访问

**获取 dashboard token**

```bash
 kubectl -n kube-system describe secret $(kubectl -n kube-system get secret | grep eip-admin-user | awk '{print $1}')   
```

执行完该命令后，可获得类似如下的输出：

> Name: admin-user-token-g8hxb
> Namespace: kube-system
> Labels: <none>
> Annotations: [kubernetes.io/service-account.name](http://kubernetes.io/service-account.name): admin-user
> [kubernetes.io/service-account.uid](http://kubernetes.io/service-account.uid): 948bb5e6-8cdc-11e9-b67e-fa163e5f7a0f
>
> Type: [kubernetes.io/service-account-token](http://kubernetes.io/service-account-token)
>
> Data
> ====
> ca.crt: 1025 bytes
> namespace: 11 bytes
> token: eyJhbGciOiJSUzI1NiIsImtpZCI6IiJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlLXN5c3RlbSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJhZG1pbi11c2VyLXRva2VuLWc4aHhiIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQubmFtZSI6ImFkbWluLXVzZXIiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC51aWQiOiI5NDhiYjVlNi04Y2RjLTExZTktYjY3ZS1mYTE2M2U1ZjdhMGYiLCJzdWIiOiJzeXN0ZW06c2VydmljZWFjY291bnQ6a3ViZS1zeXN0ZW06YWRtaW4tdXNlciJ9.DZ6dMTr8GExo5IH_vCWdB_MDfQaNognjfZKl0E5VW8vUFMVvALwo0BS-6Qsqpfxrlz87oE9yGVCpBYV0D00811bLhHIg-IR_MiBneadcqdQ_TGm_a0Pz0RbIzqJlRPiyMSxk1eXhmayfPn01upPdVCQj6D3vAY77dpcGplu3p5wE6vsNWAvrQ2d_V1KhR03IB1jJZkYwrI8FHCq_5YuzkPfHsgZ9MBQgH-jqqNXs6r8aoUZIbLsYcMHkin2vzRsMy_tjMCI9yXGiOqI-E5efTb-_KbDVwV5cbdqEIegdtYZ2J3mlrFQlmPGYTwFI8Ba9LleSYbCi4o0k74568KcN_w



**访问 Dashboard**

在浏览器打开链接 http://dashboard.yourclustername.yourdomain.com（使用前面已修改的域名）

输入前一步骤中获得的 token，可进入控制台界面



> 处于安全考虑，也可以通过 kube proxy 访问 dashboard