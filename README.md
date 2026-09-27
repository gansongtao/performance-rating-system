# 政府采购信息化绩效评价系统使用说明书

## JDK的下载与安装

[Microsoft JDK 21.0.7](https://aka.ms/download-jdk/microsoft-jdk-21.0.7-windows-x64.msi)

以管理员用户打开，勾选配置 Java 路径。

## TOMCAT的下载与安装

[Windows Service Installer](https://dlcdn.apache.org/tomcat/tomcat-11/v11.0.8/bin/apache-tomcat-11.0.8.exe)

## 数据库的安装

1. 前置条件：

   1. 自发布之日起，官方为长期支持（LTS）版本提供两年维护，创新（RCx）版本仅半年，可在网站 Planned EOL 一行看到结束支持的月份。SP 是具有新补丁的服务包，建议使用具有最高 SP 号的同版本资源。

   2. 通过 yum（类似pip）安装的 Python 默认版本为 3.11，仅 openEuler 24.03 以上支持。本文采用 VMware Player 17.6.2 + openEuler 24.03 SP1 + openGauss 6.0.1 for openEuler x86_64 22.03 配置。

   3. 博通（Broadcom）近期收购了虚拟机软件 VMware，将付费的 VMware Pro 命名为 VMware 免费发行，但须注册博通账号，国内一些邮箱平台（如网易）已被该网站禁止注册。可下载之前的免费版 VMware Player 17.6.2 作为替代。安装过程按默认进行即可。

   4. 配置过程中多数时候缺乏图形化界面，必须使用键盘操作。若没有安装 VMware Tools，复制粘贴也不可用。

2. [openEuler下载 | openEuler ISO镜像 | openEuler社区](https://www.openeuler.org/zh/download/) 社区发行版-最新版本有正在支持的版本，下载 Offline Standard ISO 安装标准版离线软件包。

3. 打开 VMware，创建新虚拟机，选择“安装程序光盘映像文件（iso）”并选择上述软件包。客户机操作系统选择 Linux – 其他 Linux 4.x 内核 64 位，最大磁盘大小最低为 32G，推荐为 128G（不会立即占用这么多），其他选项保持默认。最后一步点击自定义硬件，（运行）内存最低为 4G，推荐为 8G（运行时会立即占用），不要超过最大建议内存。处理器内核数最低 2 核，推荐 4 核。点击“关闭”配置好硬件。

4. 点击栏目中的虚拟机，播放虚拟机，开机后默认选项为“Test this media & …”测试文件完整性，可向上选择 Install 并回车直接安装。安装程序运行时显示的语言选择中文。

5. 系统-安装位置，选择自动，点击“完成”。系统-网络和主机名，用按钮打开以太网，这里示例输入主机名 host1 应用并完成。软件-软件选择，基本环境选择“虚拟化主机”，附加选项勾选 Linux 的远程管理、开发工具、安全性工具、系统工具。

6. 用户设置- Root 密码，须设定 8 字符以上 3 种组合不含词语的密码，接下来常用。用户设置-创建用户，设定全名（用户名自动同步）和密码，两者不能相同。完成后开始安装。安装后点击重启。

7. 虚拟机启动时等待所有刷屏命令行加载完，出现“Authorized users only…”后即可输入账号密码登录，输入“root”为拥有一切权限的超级账户，密码输入时不会显示。显示 # 即进入系统可输入命令。

8. 输入 ifconfig 查看 ens 开头的网卡情况，这里假设为 ens32 网卡，broadcast 部分须记下，之后要用。

9. VMware 可能提醒要安装虚拟机工具。安装时同意原 CD-ROM（即 ISO 文件）弹出。输入以下命令进行备份和安装，安装后提示会消除：

   `mkdir /mnt/cdrom`

   `ls /mnt/cdrom` 查看 VMwareTools 名称的文件全称并稍后输入

   `cp /mnt/cdrom/VMwareTools….tar.gz /tmp/`

   `cd /tmp` 此时显示为 [root@host1 tmp]

   `tar -zxvf VMwareTools….tar.gz`

   `cd vmware-tools-distrib`

   `./vmware-install.pl`

   `/usr/bin/vmware-toolbox &`

   `cd ~` 还原至根目录 [root@host1 ~]

10. 安装数据库必须关闭防火墙。

    `vi /etc/selinux/config` 用 VIM 文本编辑器进入该文件，调到 `SELINUX=enforcing` 一行，按 I 进入编辑状态，改为 `SELINUX=disabled`，按 Esc 退出，再按 :wq 保存并关闭。然后关闭服务并禁止自启动：

    `systemctl stop firewalld.service`

    `systemctl disable firewalld.service`

11. 设置字符集和环境变量：

    `cat >> /etc/profile << EOF`

    >export LANG=en_US.UTF-8
    >
    >export packagePath=/opt/software/openGauss
    >
    >EOF

    `source /etc/profile`

    `cat >>/etc/profile<<EOF`

    >export LD_LIBRARY_PATH=$packagePath/script/gspylib/clib:
    >
    >EOF

    `echo $LD_LIBRARY_PATH`

    `echo $LANG`

12. 关闭交换内存：`swapoff -a`

13. 查询并增加网卡MTU，不低于1500：

    `ifconfig | grep mtu`

    `cat >> /etc/sysconfig/network-scripts/ifcfg-ens33<<EOF`

    >MTU="8192"
    >
    >EOF

    `nmcli c reload /etc/sysconfig/network-scripts/ifcfg-ens33`

14. 该数据库要求关闭 RemoveIPC：

    `vi /etc/systemd/logind.conf` RemoveIPC=yes 改为 no

    `systemctl daemon-reload`

    `vi /usr/lib/systemd/system/systemd-logind.service` 若没有 RemoveIPC 一行则默认关闭，可按 :q 直接退出（否则保存后 `systemctl restart systemd-logind`）

15. 允许远程登录：

    `vi /etc/ssh/sshd_config` PermitRootLogin no 改为 yes

    #Banner none 去掉#注释，下面的 Banner语句加上#，防止欢迎信息让远程系统误以为未连接上

    `systemctl restart sshd.service`

16. 创建安装目录和配置文件：

    `mkdir -p /opt/software/openGauss`

    `chmod 755 -R /opt/software`

    `cd /opt/software/openGauss`

    `rm -fr  /opt/software/openGauss/clusterconfig.xml`

    `vi  /opt/software/openGauss/clusterconfig.xml`

    写入以下内容，层次须用四个空格，192.168.0.1用之前查到的IP地址代替：

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <ROOT>
        <CLUSTER>
            <PARAM name="clusterName" value="dbCluster"/>
            <PARAM name="nodeNames" value="host1"/>
            <PARAM name="gaussdbAppPath" value="/opt/gaussdb/app"/>
            <PARAM name="gaussdbLogPath" value="/var/log/gaussdb"/>
            <PARAM name="tmpMppdbPath" value="/opt/gaussdb/tmp"/>
            <PARAM name="gaussdbToolPath" value="/opt/gaussdb/om"/>
            <PARAM name="corePath" value="/opt/gaussdb/corefile"/>
            <PARAM name="backIp1s" value="192.168.0.1"/>
        </CLUSTER>
        <DEVICELIST>
            <DEVICE sn=”1000001">
                <PARAM name="name" value="host1"/>
                <PARAM name="azName" value="AZ1"/>
                <PARAM name="azPriority" value="1"/>
                <PARAM name="backIp1" value="192.168.0.1"/>
                <PARAM name="sshIp1" value="192.168.0.1"/>
            <PARAM name="dataNum" value="1"/>
            <PARAM name="dataPortBase" value="15400"/>
            <PARAM name="dataNode1" value="/gaussdb/data/host1"/>
                <PARAM name="dataNode1_syncNum" value="0"/>
            </DEVICE>
        </DEVICELIST>
    </ROOT>
    ```

17. 配置YUM：

    `cd ~`

    `mv /etc/yum.repos.d/openEuler_x86_64.repo /etc/yum.repos.d/openEuler_x86_64.repo.bak`

    `curl -o /etc/yum.repos.d/openEuler_x86_64.repo https://mirrors.huaweicloud.com/repository/conf/openeuler_x86_64.repo`

    `yum clean all`

    `yum install -y libaio* flex bison ncurses-devel glibc-devel patch readline-devel libnsl* net-tools tar`

    `yum install -y bzip2 python3` 如果是更低版本的 openEuler，请输入 python3.6 至 python3.10 中的版本
18. 操作系统和资源限制配置：

    cat >> /etc/sysctl.conf << EOF

    >net.ipv4.tcp_retries1 = 5
    >
    >net.ipv4.tcp_syn_retries = 5
    >
    >net.sctp.path_max_retrans = 10
    >
    >net.sctp.max_init_retransmits = 10
    >
    >EOF

    `lsmod | grep sctp`

    `yum -y install lksctp*`

    `modprobe sctp`

    `echo "* soft stack 3072" >> /etc/security/limits.conf`

    `echo "* hard stack 3072" >> /etc/security/limits.conf`

    `echo "* soft nofile 1000000" >> /etc/security/limits.conf`

    `echo "* hard nofile 1000000" >> /etc/security/limits.conf`

    `echo "* soft nproc unlimited" >> /etc/security/limits.d/90-nproc.conf`

    `ulimit -s 3072`

    `ulimit -n 1000000`

    `ulimit -u unlimited`

    `ulimit -a`

    建议此步之后重启资源服务，这里提供三种有用的命令：

    `shutdown now` 立即关机

    `reboot` 重启

    `logout` 退出账号

19. 时间同步服务配置：

    `yum install -y ntp`

    `systemctl start ntpd`

    `systemctl enable ntpd`

20. 关闭透明大页：

    `echo never > /sys/kernel/mm/transparent_hugepage/enabled`

    `echo never > /sys/kernel/mm/transparent_hugepage/defrag`

21. Python 准备工作（若有确认输入 yes）：

    `python -V` 若不是安装的版本，进行下面两步：

    `mv /usr/bin/python  /usr/bin/python.bak`

    `ln -s /usr/bin/python3 /usr/bin/python`

    若为 Python 3.11：

    `pip umask 0022`

    `pip install psutil netifaces-plus cffi pycparser cryptography pynacl bcrypt paramiko`

22. [openGauss软件 | openGauss下载 | openGauss软件包 | openGauss社区](https://opengauss.org/zh/download/) 选择 openGauss Server，架构 x86_64，操作系统 24.03 可选 22.03，也可选择历史版本。点击企业版下载，无须下载，复制下载链接在命令行中使用。

    准备 openGauss：

    `cd /opt/software/openGauss`

    `wget https://opengauss….tar.gz`

    `ls` 查看解压的文件全名

    `tar -zxvf openGauss-All…….tar.gz`

    `tar -zxvf openGauss-OM…….tar.gz`
23. 预安装：

    `cd /opt/software/openGauss/script`

    `/opt/software/openGauss/script/gs_checkos -i A -h host1 --detail`

    Total numbers 行的 Abnormal numbers 为 0 时能够继续安装。

    `python gs_preinstall -U omm -G dbgrp -X /opt/software/openGauss/clusterconfig.xml`

    会自动创建数据库用户 omm，要设置密码备用。

24. 安装：

    `su – omm`

    `cd /opt/software/openGauss/script`

    `gs_install -X /opt/software/openGauss/clusterconfig.xml`

    `exit` 退出用户

    `cd /opt/software/openGauss/`

    `ll -al` 找出 All 和 OM 分别执行 `rm -rf openGauss…….tar.gz`

## 系统配置

   登录 omm 用户，`gs_om -t start` 启动，在 postgres 模式下注册用户 db_user50，导入 csv 文件。

## 登录首页

   将 war 文件拖入 tomcat\lib 中，启动 tomcat 服务，打开浏览器，在地址栏输入

   http://localhost:8080/debug_war_exploded

   ![网站首页页面](homepage.png)

   登录并选择 user 即可使用评价方用户主界面。