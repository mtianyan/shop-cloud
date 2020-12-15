## 商品中心测试

http://localhost:10001/items/info/bingan-1001

## 用户中心测试
localhost:10002/center-user-api/profile?userId=1908017YR51G1XWH

localhost:10002/address-api/addressList?userId=200715G19PPGZ72W

localhost:10002/address/list?userId=200715G19PPGZ72W

## 订单中心测试

http://localhost:10003/mycomments/query?userId=200715G19PPGZ72W&page=1&pageSize=10

>INFO  ChainedDynamicProperty:115 - Flipping property: shop-cloud-ITEM-SERVICE

curl --location --request POST 'http://localhost:10003/mycomments/saveList?orderId=190827F2R9A6ZT2W&userId=1908189H7TNWDTXP' \
--header 'Content-Type: application/json' \
--data-raw '[
    {"itemId": "12",
     "content": "mtianyan",
     "commentLevel": 2
    }
]'

## 购物中心测试

http://localhost:10004/shopcart/add?userId=200715G19PPGZ72W

# 服务容错测试

POST http://localhost:10003/mycomments/query?page=0&pageSize=10&userId=1908017YR51G1XWH

![](http://cdn.pic.mtianyan.cn/blog_img/20201206060716.png)

# 配置中心验证

http://localhost:10002/address-api/addressList?userId=1908189H7TNWDTXP

# gateWay 验证

![](http://cdn.pic.mtianyan.cn/blog_img/20201208211512.png)

http://localhost:20004/address/list?userld=1908189H7TNWDTXP

# zipkin 启动

http://127.0.0.1:20005/zipkin/

# elk日志

![](http://cdn.pic.mtianyan.cn/blog_img/20201210224045.png)



# 403 log测试
![](http://cdn.pic.mtianyan.cn/blog_img/20201210233119.png)

curl --location --request POST 'http://localhost:20004/address/list?userld=191226GT3NH40WSW' \
--header 'Authorization: 123123' \
--header 'imooc-user-id: 191226GT3NH40WSW'

![](http://cdn.pic.mtianyan.cn/blog_img/20201210225400.png)

# 获取auth token

curl --location --request POST 'http://localhost:10006/auth-service/token?userId=200715G19PPGZ72W'

curl --location --request POST 'http://localhost:20004/address/list?userId=200715G19PPGZ72W' \
--header 'Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ5YW8iLCJleHAiOjE2MDc2OTg4NzEsImlhdCI6MTYwNzYxMjQ3MSwidXNlcmlkIjoiMjAwNzE1RzE5UFBHWjcyVyJ9.98mALfuI7cHdh6VuZjWZ38M5Mz2l6rhu8Wrkgg8MDIg' \
--header 'imooc-user-id: 200715G19PPGZ72W'

# 批量强制退出，stream ，降级流程

curl --location --request POST 'localhost:10002/passport/forceLogout' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'userIds=1908189H7TNWDTXP,123'

curl --location --request POST 'localhost:10002/passport/forceLogout?userIds=1908189H7TNWDTXP,123'

[15:01:10.865] INFO  c.m.u.s.UserMessageConsumer - Force logout user, uid=1908189H7TNWDTXP
[15:01:10.872] INFO  c.m.u.s.UserMessageConsumer - Force logout failed
[15:01:10.875] INFO  c.m.u.s.UserMessageConsumer - Force logout user, uid=123
[15:01:11.883] INFO  c.m.u.s.UserMessageConsumer - Force logout user, uid=123
[15:01:11.886] INFO  c.m.u.s.UserMessageConsumer - Force logout failed

# docker 启动

![](http://cdn.pic.mtianyan.cn/blog_img/20201213231643.png)

