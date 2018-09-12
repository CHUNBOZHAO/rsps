$(function(){

    // 选择地址簿地址
    $(".address-div").click(function () {
        var name = $(this).attr("name");
        var tel = $(this).attr("tel");
        var areaProvince = $(this).attr("areaProvince");
        var areaCity = $(this).attr("areaCity");
        var areaCounty = $(this).attr("areaCounty");
        var detailAddress = $(this).attr("detailAddress");

        $("input[name='name']").val(name);
        $("input[name='tel']").val(tel);
        $("textarea[name='detailAddress']").val(detailAddress);

        $("select[name='areaProvince']").val(areaProvince);
        $("select[name='areaProvince']").change();
        $("select[name='areaCity']").val(areaCity);
        $("select[name='areaCity']").change();
        $("select[name='areaCounty']").val(areaCounty);
    });

    // 确定
    $(".btn-address-select").click(function () {
        $("#btn_address_ok").click();
    });

    // 选择地址簿
    var submit = true;
    $("#address_sure_form").validate({
        rules: {
            name: {
                required:true,
            },
            tel: {
                required: true,
                digits:true
            },
            areaProvince:{
                required: true,
            },
            areaCity:{
                required: true,
            },
            areaCounty:{
                required: true,
            },
            detailAddress:{
                required: true,
            }
        },
        messages:{
            name: {
                required:"姓名不能为空",
            },
            tel: {
                required: "手机号码不能为空",
                minlength:"手机号码不正确",
                maxlength:"手机号码不正确"
            },
            areaProvince:{
                required: "不能为空",
            },
            areaCity:{
                required: "不能为空",
            },
            areaCounty:{
                required: "不能为空",
            },
            detailAddress:{
                required: "详细地址不能为空",
            },
        },
        errorElement: "em",
        errorPlacement: function ( error, element ) {
            // Add the `help-block` class to the error element
            error.addClass( "help-block" );

            if ( element.prop( "type" ) === "checkbox" ) {
                error.insertAfter( element.parent( "label" ) );
            } else {
                error.insertAfter( element );
            }
        },
        highlight: function ( element, errorClass, validClass ) {
            $( element ).parents( ".input-parent" ).addClass( "has-error" ).removeClass( "has-success" );
        },
        unhighlight: function (element, errorClass, validClass) {
            $( element ).parents( ".input-parent" ).addClass( "has-success" ).removeClass( "has-error" );
        },
        submitHandler: function(form) {
            if (!submit) {
                return;
            }
            $(form).ajaxSubmit({
                dataType:"text",
                success:function (msg) {
                    window.location.href = "/custom/order/showOrder";
                }
            });
        }
    });



});