#! /bin/bash

if [ $# -ne 4 ]
then
    echo "#### 参数有误，\$1:项目名，\$2:tomcat路径，\$3:ip地址, \$4:war path"
exit 1
fi
scp $4 root@$3:$2"/temp/"
ssh -t -t root@$3  << eeooff
        cd $2
        ##### 查找tomcat进程并杀死
        ps -ef | grep '$2' | grep -v grep | awk '{print \$2}' | xargs kill -9

        ##### 拷贝文件
        mv "webapps/"$1".war" "temp/"$1"_"$(date +%Y%m%d%H%M%S)".war"

        ##### 删除文件
        rm -rf webapps/$1
        rm -rf webapps/ROOT* $1

        ##### 部署文件
        mv "temp/"$1".war" webapps/
        rm -rf temp/$1".war"

        ##### 启动tomcat
        bin/startup.sh &
        exit
eeooff