    $(function () {
        to_page(1);
    });

var totalRecord;

function to_page(pn) {
    $.ajax({
        url:"/emps",
        data:"pn="+pn,
        type:"GET",
        success:function (result) {
            //console.log(result);
            build_emps_table(result);
            build_page_info(result);
            build_page_nav(result);
        }
    });
}

function build_emps_table(result) {
    $("#emps_table tbody").empty();
    var emps = result.extend.pageInfo.list;
    $.each(emps, function (index,item) {
        var empIdTd = $("<td></td>").append(item.empId);
        var empNameTd = $("<td></td>").append(item.empName);
        var genderTd = $("<td></td>").append(item.gender == 'M'?"男":"女");
        var emailTd = $("<td></td>").append(item.email);
        var deptNameTd = $("<td></td>").append(item.department.deptName);
        var editBtn = $("<button></button>").addClass("btn btn-primary edit_btn")
            .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
        editBtn.attr("edit-id", item.empId);
        var delBtn = $("<button></button>").addClass("btn btn-danger delete_btn")
            .append($("<span></span>").addClass("glyphicon glyphicon-remove")).append("删除");
        delBtn.attr("del-id", item.empId);
        var btnTd = $("<td></td>").append(editBtn).append("  ").append(delBtn);
        $("<tr></tr>").append(empIdTd)
            .append(empNameTd)
            .append(genderTd)
            .append(emailTd)
            .append(deptNameTd)
            .append(btnTd)
            .appendTo("#emps_table tbody");
    });
}

function build_page_info(result) {
    $("#page_info_area").empty();
    $("#page_info_area").append("当前第"
        + result.extend.pageInfo.pageNum
        + "页,共"+result.extend.pageInfo.pages
        + "页,共"+result.extend.pageInfo.total
        + "条记录");
    totalRecord = result.extend.pageInfo.total;
}

function build_page_nav(result) {
    $("#page_nav_area").empty();
    var ul = $("<ul></ul>").addClass("pagination");
    var firstPageLi=$("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
    var prePageLi=$("<li></li>").append($("<a></a>").append("&laquo;"));
    if(!result.extend.pageInfo.hasPreviousPage){
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    }else{
        firstPageLi.click(function () {
            to_page(1);
        });
        prePageLi.click(function () {
            to_page(result.extend.pageInfo.pageNum - 1);
        });
    }

    var nextPageLi=$("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastPageLi=$("<li></li>").append($("<a></a>").append("尾页").attr("href","#"));
    if(!result.extend.pageInfo.hasNextPage){
        nextPageLi.addClass("disabled");
        lastPageLi.addClass("disabled");
    }else{
        nextPageLi.click(function () {
            to_page(result.extend.pageInfo.pageNum + 1);
        });
        lastPageLi.click(function () {
            to_page(result.extend.pageInfo.pages);
        });
    }

    ul.append(firstPageLi).append(prePageLi);
    $.each(result.extend.pageInfo.navigatepageNums, function (index, item) {
        var numLi=$("<li></li>").append($("<a></a>").append(item));
        if(result.extend.pageInfo.pageNum == item){
            numLi.addClass("active");
        }
        numLi.click(function () {
            to_page(item)
        });
        ul.append(numLi);
    });
    ul.append(nextPageLi).append(lastPageLi);
    var navEle=$("<nav></nav>").append(ul);
    navEle.appendTo("#page_nav_area");
}

// 点击新增按钮 弹出模态框
$("#emp_add_modal_btn").click(function () {
    // 清空
    $("#emp_department").empty();
    // 发送ajax请求查出所有部门显示在下来列表中
    getDepts("#emp_department");
    // 弹出模态框
    $("#empAddModal").modal({
        backdrop:"static"
    });
});

// 得到所有部门
function getDepts(ele) {
    $.ajax({
        url: "/getDepts",
        type: "GET",
        success: function(result) {
            //console.log(result);
            $.each(result.extend.depts, function () {
                var optionEle = $("<option></option>").append(this.deptName).attr("value", this.deptId);
                optionEle.appendTo(ele);
            });
        }
    });
}

// 校验用户名 邮箱格式
function validate_add_form(){
    var empName = $("#emp_name").val();
    var regName = /(^[a-zA-Z0-9_-]{6,10}$)|(^[\u2E80-\u9FFF]{2,5})/;
    if(!regName.test(empName)){
        //alert("用户名可以是2-5个汉字或者6-10个英文字母和数字的组合");
        $("#emp_name").parent().addClass("has-error");
        $("#emp_name").next("span").text("用户名可以是2-5个汉字或者6-10个英文字母和数字的组合");
        return false;
    }else{
        $("#emp_name").parent().addClass("has-success");
        $("#emp_name").next("span").text("");
    }

    // var empEmail = $("#emp_email");
    // var regEmail = /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,})$/;
    // if(!regEmail.test(empEmail)){
    //     //alert("邮箱格式不正确");
    //     $("#emp_email").parent().addClass("has-error");
    //     $("#emp_email").next("span").text("邮箱格式不正确");
    //     return false;
    // }else{
    //     $("#emp_email").parent().addClass("has-success");
    //     $("#emp_email").next("span").text("");
    // }
}

// 点击保存按钮事件
$("#emp_save_btn").click(function () {
    //对姓名邮箱进行校验
    // if(!validate_add_form()){
    //     return false;
    // }
    $.ajax({
        url:"/emps",
        type:"POST",
        data:$("#empAddModal form").serialize(),
        success:function (result) {
            // 关闭模态框
            $("#empAddModal").modal('hide');
            // 跳转到最后一页
            to_page(totalRecord);
        }
    });

});


// 点击编辑按钮修改
$(document).on("click", ".edit_btn", function () {
    $("#emp_department1").empty();
    getDepts("#emp_department1");
    getOneEmp($(this).attr("edit-id"));
    $("#emp_save_btn1").attr("edit-id", $(this).attr("edit-id"));
    $("#empUpdateModal").modal({
        backdrop:"static"
    });
});


// 查询单个员工信息
function getOneEmp(id){
    $.ajax({
        url:"/emps/"+id,
        type: "GET",
        success:function (result) {
            var empData = result.extend.emp;
            $("#emp_name1").val(empData.empName);
            $("#emp_email1").val(empData.email);
            $("#empUpdateModal input[name=gender]").val([empData.gender]);
            $("#empUpdateModal select").val([empData.dId]);
        }
    });
}

// 点击更新进行更新信息
$("#emp_save_btn1").click(function() {
    $.ajax({
        url:"/emps/"+$(this).attr("edit-id"),
        type:"PUT",
        data:$("#empUpdateModal form").serialize(),
        success:function (result) {
            $("#empUpdateModal").modal('hide');
        }
    });
});

// 删除员工
$(document).on("click", ".delete_btn", function () {
    var empId = $(this).attr("del-id");
    $.ajax({
        url:"/emps/" + empId,
        type:"DELETE",
        success:function (result) {
            alert(result.msg);
        }
    });

});