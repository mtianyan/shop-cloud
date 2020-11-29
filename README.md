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




