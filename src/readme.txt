登录
接口：/user/person/login
数据类型：json
提交类型：post
数据格式：{"username": "username", "password": "password"}
数据正确返回内容：       flag = true
    用户正确：{"msg":{"state":"SUCCESS"},"flag":{"flag":"true"},"user":{"id":"1","username":"hz"}}
    用户或密码错误：{"msg":{"state":"FALSE","error":"AORP ERROR"},"flag":{"flag":"true"}}
数据错误返回内容        flag = false
    {"msg":{"state":"FALSE","error":"DATA FORMATTING ERROR"},"flag":{"flag":"false"}}

注册
接口：/user/person/regist
数据类型：json
提交类型：post
数据格式：{"username": "username", "password": "password"}
数据正确返回内容：       flag = true
    插入成功：{"msg":{"state":"SUCCESS"},"flag":{"flag":"true"},"user":{"id":"19","username":"hzandy3"}}
    插入失败：{"msg":{"state":"FALSE","error":"DB INSERT FALSE"},"flag":{"flag":"true"}}
    用户重复：{"msg":{"state":"FALSE","error":"DB INSERT REPETDATA"},"flag":{"flag":"true"}}
数据错误返回内容        flag = false
    {"msg":{"state":"FALSE","error":"DATA FORMATTING ERROR"},"flag":{"flag":"false"}}

查词语
接口：/word/mean
数据类型：json
提交类型：post
数据格式：{"word": "word"}
数据正确返回内容：       flag = true
    有词语：{"msg":{"state":"SUCCESS"},"flag":{"flag":"true"},"word":{"wid":"2","speech":"adj.","mean":"my abc","word":"my"}}
    无词语：{"msg":{"state":"FALSE","error":"null word error"},"flag":{"flag":"true"}}
数据错误返回内容
    {"msg":{"state":"FALSE","error":"DATA FORMATTING ERROR"},"flag":{"flag":"false"}}

查询用户历史词语记录
接口：/user/history/select
数据类型：json
提交类型：post
数据格式：{"uid":x}  x为数字
数据正确返回内容：       flag = true
    历史记录：{"flag":{"flag":"true"},
                    "wordhistory":[
                    {"uid":1,"wid":1,"addTime":"2017-11-11 11:05:11.0","speech":"v.","mean":"hello abc","word":"hello"},
                    {"uid":1,"wid":3,"addTime":"2017-11-11 11:11:11.0","speech":"n.","mean":"name abc","word":"name"}
                    ]
                  }
数据类型错误      flag = false
    {"msg":{"state":"FALSE","error":"NULL USER ERROR OR DATA FORMATTING ERROR"},"flag":{"flag":"true"}}

插入历史记录
接口：/user/history/insert
数据类型：json
提交类型：post
数据格式：{"uid":x, "wid" : x}       x为数字
数据正确返回内容：       flag = true
    插入成功：{"msg":{"state":"SUCCESS"},"flag":{"flag":"true"}}
    插入失败：{"msg":{"state":"FALSE","error":"DB INSERT FALSE"},"flag":{"flag":"true"}}
数据类型错误      flag = false
    {"msg":{"state":"FALSE","error":"DATA FORMATTING ERROR"},"flag":{"flag":"false"}}

删除历史记录
接口：/user/history/delete
数据类型：json
提交类型：post
数据格式：{"uid":x, "wid" : x}       x为数字
数据正确返回内容：       flag = true
    删除成功：{"msg":{"state":"SUCCESS"},"flag":{"flag":"true"}}
    删除失败：{"msg":{"state":"FALSE","error":"DB DELETE FALSE"},"flag":{"flag":"true"}}
数据类型错误      flag = flase
    {"msg":{"state":"FALSE","error":"DATA FORMATTING ERROR"},"flag":{"flag":"false"}}

修改历史（主要是修改查询时间， 先查了插入了历史记录，然后，后面再查了 因为是同一词语 所以就是更新查询时间）
接口：/user/history/update
数据类型：json
提交类型：post
数据格式：{"uid":x, "wid" : x}       x为数字
数据正确返回内容：       flag = true
    修改成功：{"msg":{"state":"SUCCESS"},"flag":{"flag":"true"}}
    修改失败：{"msg":{"state":"FALSE","error":"DB UPDATE FALSE"},"flag":{"flag":"true"}}
数据类型错误：        flag = flase
    {"msg":{"state":"FALSE","error":"DATA FORMATTING ERROR"},"flag":{"flag":"false"}}

