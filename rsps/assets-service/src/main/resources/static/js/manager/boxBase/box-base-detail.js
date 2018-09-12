$(function(){

      toastr.options = {
           "positionClass": "toast-top-center", //弹出窗的位置
      }
       // 新增用户表单验证及提交
       $("#box_detail_save").validate({
           rules: {
               brcycle: {
                   required:true,
                   maxlength:10000
               },
               brpower: {
                   required: true,
                    maxlength:10000
               },
               tempcycle:{
                   required: true,
                    maxlength:10000
               },
               volcycle:{
                   required: true,
                   maxlength:10000
               },
               rfidcycle:{
                   required: true,
                   maxlength:10000
               },
               opencheckdelay:{
                   required: true,
                   maxlength:10000
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
               $( element ).parents( ".col-sm-10" ).addClass( "has-error" ).removeClass( "has-success" );
           },
           unhighlight: function (element, errorClass, validClass) {
               $( element ).parents( ".col-sm-10" ).addClass( "has-success" ).removeClass( "has-error" );
           },
           submitHandler: function(form) {
               $(form).ajaxSubmit({
                   dataType:"json",
                   success:function (msg) {
                    alert(msg)
                       if (msg.result == "success") {
                           toastr.success("保存成功");
                           setTimeout(function () {
                               window.location.href = "/manager/boxBase/list/show";
                           }, 2000);

                       } else {
//                           if (msg.msgCode == 101) {
//                               toastr.warning("该电子标签已经存在");
//                               $("#rfid").focus();
//                           }
                            toastr.error("保存失败，请联系管理员")
                        }
                       }
               });
           }
       });

})
