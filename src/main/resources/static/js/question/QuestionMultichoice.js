console.log("Multichoice Question");
//init number of choice
function initNumberC(){
    document.querySelector('[name=singleAnswer]').onchange = function(){
        var value = document.querySelector('[name=singleAnswer]').value;
        var typeInput = value == 1 ? 'radio' : 'checkbox';
        document.getElementsByName('c_ans[]').forEach(cb =>{
            cb.type = typeInput;
        })
    }
}
initNumberC();
function initClickCheck(e){
    document.getElementsByName('ans[]').forEach(ans=>{
        ans.onkeyup = function () {
            e.revalidateField('ans[]');
        }
    })
}
function blockClick(){
    var question = document.getElementsByName('ans[]');
    var check = document.getElementsByName('c_ans[]')
    for(let i = 0; i<question.length;i++){
        check[i].onclick = function (e){
            if(question[i].value == '') {
                e.preventDefault();
                Swal.fire({
                    text: "Xin lỗi, bạn phải nhập câu hỏi trước khi đánh dấu, vui lòng thử lại.",
                    icon: "error",
                    buttonsStyling: !1,
                    confirmButtonText: "Ok!",
                    customClass: {confirmButton: "btn btn-primary"}
                })
            }
        }
        question[i].onchange = function (){
            if(question[i].value == '') check[i].checked = false;
        }
    }
}
blockClick();
// init number add answer
var numOfAns = document.querySelector('[name=numOfQuestion]').value;
"use strict";
var KTMultichoiceQuestion = function () {
    var t, e, i, file;
    return {
        init: function () {
            var valObj = {
                fields: {
                    name: {validators: {notEmpty: {message: "Vui lòng nhập tên câu hỏi"}}},
                    questionText: {validators: {notEmpty: {message: "Vui lòng nhập nôi dung câu hỏi"}}},
                    defaultMark: {validators: {notEmpty: {message: "Vui lòng nhập số điểm của câu hỏi"},
                        integer: {message: "Số điểm của câu hỏi phải là số nguyên"}}},
                    "c_ans[]": {validators: {notEmpty: {message: "Vui lòng chọn tối thiểu một đáp án đúng"}}},
                    "ans[]": {validators: {
                        callback:{
                            callback: function (){
                             var num = 0;
                             document.getElementsByName('ans[]').forEach(ans=>{
                                 if(ans.value != '') num++;
                             })
                                if(num >= 2){
                                    return {
                                        valid :true
                                    }
                                }else{
                                    return {
                                        valid : false,
                                        message: "Bạn phải nhập tối thiểu 2 đáp án"
                                    }
                                }
                            }
                        }}}
                },
                plugins: {
                    trigger: new FormValidation.plugins.Trigger(),
                    bootstrap: new FormValidation.plugins.Bootstrap5({
                        rowSelector: ".fv-row",
                        eleInvalidClass: "hl-error",
                        eleValidClass: ""
                    })
                }
            }

            i = document.querySelector("#kt_add_question"), t = document.getElementById("kt_question_submit_button"), e = FormValidation.formValidation(i, valObj),
                initClickCheck(e),
                document.getElementById('btn-add-answer').onclick = function (event){
                    event.preventDefault();
                    var ans_container = document.querySelector('.wrapper-choice');
                    numOfAns++;
                    var ans = document.getElementsByClassName('ans')[0].cloneNode(true);
                    var title = ans.childNodes[1].childNodes[1].childNodes[3].childNodes[1];
                    title.innerHTML = `Lựa chọn ${numOfAns}`;
                    var check = ans.childNodes[3].childNodes[1].childNodes[1];
                    check.id = `c_ans${numOfAns}`;
                    var lable = ans.childNodes[3].childNodes[1].childNodes[3];
                    lable.setAttribute('for',`c_ans${numOfAns}`);
                    ans_container.appendChild(ans);
                    e.removeField('c_ans[]');
                    e.removeField('ans[]');
                    e.addField('c_ans[]',{validators: {notEmpty: {message: "Vui lòng chọn tối thiểu một đáp án đúng"}}});
                    e.addField('ans[]',{validators: {
                            callback:{
                                callback: function (){
                                    var num = 0;
                                    document.getElementsByName('ans[]').forEach(ans=>{
                                        if(ans.value != '') num++;
                                    })
                                    if(num >= 2){
                                        return {
                                            valid :true
                                        }
                                    }else{
                                        return {
                                            valid : false,
                                            message: "Bạn phải nhập tối thiểu 2 đáp án"
                                        }
                                    }
                                }
                            }}});
                initNumberC();
                initClickCheck(e);
                }

            t.addEventListener("click", (function (i) {
                i.preventDefault(), e && e.validate().then((function (e) {
                    if ('Valid' === e) {
                        t.setAttribute('data-kt-indicator', 'on');
                        t.disabled = !0;
                        setTimeout(function () {
                            var moduleID = document.getElementById("courseModuleID") ? document.getElementById("courseModuleID").value : null;
                            let courseID = document.getElementById('courseID').value;
                            let sectionID = document.getElementById('sectionID').value;
                            let name = document.querySelector('[name=name]').value;
                            let description = document.querySelector('[name=description]').value;
                            let showDescription = document.getElementById('showDes').checked;

                            let fileName = document.getElementById('fileUp').files.length != 0 ? document.getElementById('fileUp').files[0].name : '';
                            let typeName = document.getElementById('typeName').value;


                            var objDT = {
                                "courseModules": {
                                    "courseModuleId": moduleID,
                                    "name": name,
                                    "description": description,
                                    "typeName": typeName,
                                    "showDescription": showDescription,
                                    "visible": true,
                                    "courseSections": {
                                        "courseSectionId": sectionID
                                    }
                                },
                                "fileModule": {
                                    "fileData": file,
                                    "fileName": fileName
                                }
                            };
                            if(moduleID == null)  delete objDT.courseModules.courseModuleId;
                            var data = JSON.stringify(objDT);

                            var xhr = new XMLHttpRequest();
                            xhr.withCredentials = true;
                            xhr.addEventListener("readystatechange", function () {
                                if (this.readyState === 4) {
                                    t.removeAttribute('data-kt-indicator');
                                    t.disabled = !1;
                                    let statusCode = this.status;
                                    if (statusCode === 200) {
                                        Swal.fire({
                                            text: `${this.responseText}!`,
                                            icon: "success",
                                            buttonsStyling: !1,
                                            confirmButtonText: "Ok!",
                                            customClass: {confirmButton: "btn btn-primary"}
                                        }).then((function (t) {
                                            window.location.href = `/teacher/viewCourse?id=${courseID}`;
                                        }))
                                    } else {
                                        Swal.fire({
                                            text: `Xin lỗi, ${this.responseText}, vui lòng thử lại.`,
                                            icon: 'error',
                                            buttonsStyling: !1,
                                            confirmButtonText: 'Ok!',
                                            customClass: {
                                                confirmButton: 'btn btn-primary'
                                            }
                                        })
                                    }
                                }
                            });
                            xhr.open("POST", "/teacher/addModule");
                            xhr.setRequestHeader("Content-Type", "application/json");
                            xhr.send(data);
                        }, 700);

                    } else {
                        Swal.fire({
                            text: "Xin lỗi, bạn chưa nhập đầy đủ thông tin, vui lòng thử lại.",
                            icon: "error",
                            buttonsStyling: !1,
                            confirmButtonText: "Ok!",
                            customClass: {confirmButton: "btn btn-primary"}
                        }).then((function (t) {
                            KTUtil.scrollTop()
                        }))
                    }


                }))
            }))
        }
    }
}();
KTUtil.onDOMContentLoaded((function () {
    KTMultichoiceQuestion.init()
}));