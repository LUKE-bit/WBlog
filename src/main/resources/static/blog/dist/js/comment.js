function isNull(obj) {
    if (obj == null || obj == undefined || obj == ""){
        return true;
    }
    return false;
}
function isLegal(obj){
    var reg = /^[\u4e00-\u9fa5\w]+$/;
    return reg.test(obj);
}
//发布评论
$('#confirmButton').click(function () {
    var commentName;
    var comment = $('#comment').val();
    var articleId = $('#articleId').val();
    if ($('#username').length > 0){
        commentName = $('#username').html();
    }
    if (isNull(comment)){
        swal("评论不能为空",{
         icon: "error",
        });
        return;
    }
    var data = {"commentName":commentName,"comment":comment,"articleId":articleId}
    $.ajax({
        type: "POST",
        url: "/saveComment",
        data:data,
        success:function (data) {
            if (data.code == 200){
                swal(data.message,{
                    icon:"success"
                });
                $('#commentName').val('');
                $('#comment').val('');
                $('#commentul').find('li').remove();
                pagination(0);
            }
        }
    })
});
//获得评论
$(function () {
    $('#commentul').find('li').remove();
    pagination(0)
});
//评论分页
function pagination (pageNum) {
    $('#commentul').find('li').remove();
    var articleId = $('#articleId').val();
    var data = {"articleId":articleId,"pageNum":pageNum};
    $.ajax({
        type: "POST",
        data: data,
        url: "/getComments",
        success: function (data) {
            $('#pageNum').val(data.pageNum);
            $.each(data.list,function (index,element) {
                var date = new Date(element.createBy).toLocaleString();
                var comment = '<li class="am-comment">\n' +
                    '            <a href="#">\n' +
                    '              <img src="https://mywblog.oss-cn-beijing.aliyuncs.com/blog/2022-03-19/baha.jpg" alt="" class="am-comment-avatar" width="48" height="48">\n' +
                    '            </a>\n' +
                    '            <div class="am-comment-main">\n' +
                    '              <header class="am-comment-hd">\n' +
                    '                <div class="am-comment-meta">\n' +
                    '                  <span class="am-comment-author">'+element.commentName+'</span> 评论于 <span>'+date+'</span>\n' +
                    '                </div>\n' +
                    '              </header>\n' +
                    '              <div class="am-comment-bd">\n' +
                    '                <p>'+element.comment+'</p>\n' +
                    '              </div>\n' +
                    '<div class="am-comment-hd">\n'+
                         '<div class="am-comment-meta " style="text-align: right;">\n'+
                             '<a style="color:#065279;" href="javascript:void(0)" id="replyA" onclick=replyModal("'+element.comment+'","'+element.id+'") data-am-modal="{target: \'#doc-modal-1\'}"><span>回复</span></a>\n'+
                         '</div>\n'+
                   '</div>\n'+
                    '            </div>\n' +
                    '          </li>';
                $('#commentul').append(comment);
                if (element.reply != null){
                    var replyDate = new Date(element.replyCreateBy).toLocaleString();
                    var reply = '          <li class="am-comment am-comment-flip">\n' +
                        '            <a href="#link-to-user-home">\n' +
                        '              <img src="https://lpepsi.oss-cn-shenzhen.aliyuncs.com/dgeqoj.jpg" alt="" class="am-comment-avatar" width="48" height="48">\n' +
                        '            </a>\n' +
                        '            <div class="am-comment-main">\n' +
                        '              <header class="am-comment-hd">\n' +
                        '                <div class="am-comment-meta">\n' +
                        '                   <span class="am-comment-author">回复:&nbsp;'+element.commentName+'</span> 于 <span>'+replyDate+'</span>\n' +
                        '                </div>\n' +
                        '              </header>\n' +
                        '              <div class="am-comment-bd">\n' +
                        '                <p>'+element.reply+'</p>\n' +
                        '              </div>\n' +
                        '            </div>\n' +
                        '          </li>\n' +
                        ' '
                    $('#commentul').append(reply);
                }
            });
            if (data.pages > 1){
                $('#pagination').find('li').remove();
                if (data.hasPreviousPage){
                    var pre_li = '<li  class="am-pagination-prev"><a onclick="pagination('+data.prePage+')">上一页</a></li>'
                    $('#pagination').append(pre_li);
                }
                if (data.hasNextPage) {
                    var next_li =  ' <li  class="am-pagination-next"><a onclick="pagination('+data.nextPage+')">下一页</a></li>';
                    $('#pagination').append(next_li);
                }
            }
        }
    })
}
function replyModal(obj,id) {
    if ($('#username').length > 0){
        $('#commentId').val(id);
        $('#reply').attr('placeholder','回复：'+obj);
    }else {
        swal("请先登录在回复",{
            icon: "warning",
            confirmButtonText: "登录"
        });
        return;
    }
}

$('#replyConfirm').click(function () {
    var commentId = $('#commentId').val();
    var reply = $('#reply').val();
    var pageNum = $('#pageNum').val();
    if (isNull(reply)){
        swal("回复不能为空",{
            icon: "error",
        });
        return;
    }
    var data = {"replyComment":reply,"id":commentId}
    $.ajax({
        type: 'POST',
        url: '/saveReply',
        data: data,
        success: function (data) {
            console.log(data);
            if (data.code == 200){
                $('#reply').val("");
                swal( {
                    title: data.message,
                    icon: "success",
                    showCancelButton: false,
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: '返回',
                    confirmButtonClass: 'btn btn-success',
                    buttonsStyling: false
                }).then(function () {
                     pagination(pageNum);
                });
            } else{
                $('#reply').val("");
                swal(data.message,{
                    icon: "error"
                })
            }
        },
        error: function () {
            swal("操作失败",{
                icon: "error"
            })
        }
    })
})