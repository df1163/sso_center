/**
 * @author : zga
 * @date : 2016-01-12
 *
 * SSO系统Js常量
 *
 **/
var Constant = {
    'phonePattern' : /^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])\d{8}$/,
    'emailPattern' : /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
}

var ErrorMessage = {
    "100001" : "参数为空",
    "100002" : "验证码错误",
    "100003" : "注册成功",
    "100004" : "注册失败",
    "100005" : "发送Email失败",
    "100006" : "发送Email成功",
    "100007" : "未登录",
    "S500000" : "服务忙",
    "100008" : "原密码错误",
    "100009" : "修改密码失败",
    "100010" : "修改密码成功",
    "100011" : "邀请码不存在",
    "100012" : "绑定邀请码失败",
    "100013" : "绑定邀请码成功",
    "100014" : "手机号已绑定",
    "100015" : "手机验证码错误",
    "100016" : "绑定手机号失败",
    "100017" : "绑定手机号成功",
    "100018" : "用户名已存在",
    "100019" : "该邮箱已绑定",
    "100020" : "邀请码已绑定",
    "100021" : "邀请码生成失败",
    "100022" : "邀请码生成成功",
    "100023" : "管理员重置密码成功",
    "100024" : "管理员修改状态成功",
    "100025" : "管理员删除组织机构成功",

    "200000" : "同步成功",
    "200001" : "错误的client_id",
    "200002" : "错误的sso访问密码",
    "200003" : "没有要同步的数据",
    "200004" : "服务端忙",

    "300001" : "验证码不能为空",
    "300002" : "验证码已过期",
    "300003" : "验证码错误",
    "300004" : "验证码正确",
    "300005" : "每天限定发送验证码6次,你已超过"
};