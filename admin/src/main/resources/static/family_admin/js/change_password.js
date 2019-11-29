layui.use('jquery',function(){
    let $ = layui.jquery;
    //修改密码
    $('.update').click(function () {
        var password = $('#old_password').val();
        var newPassword = $('#new_password').val();
        var renewPassword = $('#renew_password').val();
        if(password.length<=6 || password.length>20){
            layer.msg("原密码输入不合法",{icon: 5, shift: 6});
            return false;
        }
        if(newPassword.length<=6 || newPassword.length>20){
            layer.msg("新密码输入不合法",{icon: 5, shift: 6});
            return false;
        }
        if(renewPassword.length<=6 || renewPassword.length>20){
            layer.msg("确认密码输入不合法",{icon: 5, shift: 6});
            return false;
        }
        if(password === newPassword){
            layer.msg("不能跟原密码相同",{icon: 5, shift: 6});
            return false;
        }
        if(newPassword !== renewPassword){
            layer.msg("确认密码不同",{icon: 5, shift: 6});
            return false;
        }

        var data = {
            password: password,
            newPassword: newPassword,
            renewPassword: renewPassword
        };

        $.ajax({
            type: "POST",
            data: data,
            url: ctx + "index/alterPassword",
            success: function (result) {
                if(result.code == SUCCESS_CODE){
                    layer.msg(result.data);
                    //暂停2秒关闭窗口
                    setTimeout(function () {
                        xadmin.close();
                    }, 2000);
                }
            }
        })

    })
});