# create by mtianyan
FROM debian
MAINTAINER mtianyan
RUN mkdir test1
RUN touch test2
# 和当前环境交互
COPY test3 .
RUN true
# 附带解压功能，下载功能
ADD test4.tar.gz .
# 暴露端口,可启动时才指定
# EXPOSE 80
# 可启动时-v指定
# VOLUME /data
# 只会执行最后一条entry_point, 必然被执行到
ENTRYPOINT ["/bin/sh"]
# docker run start 外部不指定命令会执行，指定命令就不会执行。执行到没有entry_ponit,直接执行， 如果有entrypoint 就会把这个做完参数传递给entry_point
CMD ["-c", "ls -l"]
