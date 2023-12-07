$(function () {

    //jqValidate验证
    $("#search").validate({
        errorClass : "text-danger",
        errorElement : "span",
        rules : {
            questionId: {
                digits:true
            }
        },
        messages : {
            questionId: {
                digits:"只能输入纯数字的整数"
            }
        }
    });

});