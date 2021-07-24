console.log("Add Quiz");
// on_off date
document.getElementById('allowstartdate').onclick = function () {
    var check = document.getElementById('allowstartdate').checked;
    if (check) {
        document.getElementsByName('start_date')[0].disabled = !check;
        document.getElementsByName('start_date')[0].style.backgroundColor = '#f5f8fa'
    } else {
        document.getElementsByName('start_date')[0].disabled = !check;
        document.getElementsByName('start_date')[0].style.backgroundColor = '#3a3b3c'
    }
}
document.getElementById('allowenddate').onclick = function () {
    var check = document.getElementById('allowenddate').checked;
    if (check) {
        document.getElementsByName('end_date')[0].disabled = !check;
        document.getElementsByName('end_date')[0].style.backgroundColor = '#f5f8fa'
    } else {
        document.getElementsByName('end_date')[0].disabled = !check;
        document.getElementsByName('end_date')[0].style.backgroundColor = '#3a3b3c'
    }
}

function toDate(value) {
    if(value.length === 0) return null;
    let dateTime = value.split(' ');
    let dateObj = new Date();
    let date = dateTime[0].split('-');
    let time = dateTime[1].split(':');
    dateObj.setDate(date[0]);
    dateObj.setMonth(date[1]);
    dateObj.setFullYear(date[2]);
    dateObj.setHours(time[0]);
    dateObj.setMinutes(time[1]);
    return dateObj;
}
"use strict";
var KTCareersApply = function () {
    var t, e, i, file;
    return {
        init: function () {
            var valObj = {
                fields: {
                    name: {validators: {notEmpty: {message: "Vui lòng nhập tên Assignment"}}},
                    "typeSubmit[]": {validators: {notEmpty: {message: "Vui lòng chọn một loại nộp bài"}}},
                    maxGrade: {
                        validators: {
                            notEmpty: {message: "Bạn phải nhập điểm cao nhất"}
                            , numeric: {message: "Vui lòng nhập số"}
                        }
                    },
                    start_date: {validators: {notEmpty: {message: "Ngày bắt đầu là bắt buộc"}}},
                    end_date: {
                        validators: {
                            notEmpty: {
                                message: "Ngày kết thúc là bắt buộc"
                            },
                            identical: {
                                compare: function () {
                                    var startDate = toDate(document.querySelector('[name=start_date]').value);
                                    var endDate =toDate(document.querySelector('[name=end_date]').value);
                                    if (endDate.getTime() - startDate.getTime() >= 0)
                                        return document.querySelector('[name=end_date]').value;
                                    return -1;
                                },
                                message: 'Ngày kết thúc phải lớn hơn ngày bắt đầu'
                            }
                        }
                    }
                },
                plugins: {
                    trigger: new FormValidation.plugins.Trigger({
                        event: {
                            start_date: !1
                        }
                    }),
                    bootstrap: new FormValidation.plugins.Bootstrap5({
                        rowSelector: ".fv-row",
                        eleInvalidClass: "",
                        eleValidClass: ""
                    })
                }
            }

            i = document.querySelector("#kt_careers_form"), t = document.getElementById("kt_module_submit_button"), e = FormValidation.formValidation(i, valObj),
                $(i.querySelector('#allowenddate')).on("click", (function () {
                    console.log('click');
                    if (document.getElementById('allowenddate').checked) {
                        e.addField("end_date", {
                            validators: {
                                notEmpty: {message: "Ngày kết thúc là bắt buộc"}
                                , identical: {
                                    compare: function () {
                                        // return e.querySelector('[name="start_date"]').value
                                        var startDate = new Date(document.querySelector('[name=start_date]').value);
                                        var endDate = new Date(document.querySelector('[name=end_date]').value);
                                        if (endDate.getTime() - startDate.getTime() >= 0)
                                            return document.querySelector('[name=end_date]').value;
                                        return -1;
                                    },
                                    message: 'Ngày kết thúc phải lớn hơn ngày bắt đầu'
                                }
                            }
                        });
                    } else {
                        e.removeField('end_date');
                    }
                })),
                $(i.querySelector('#allowstartdate')).on("click", (function () {
                    console.log('click');
                    if (document.getElementById('allowstartdate').checked) {
                        e.addField("start_date", {validators: {notEmpty: {message: "Ngày bắt đầu là bắt buộc"}}});
                    } else {
                        e.removeField('start_date');
                    }
                })),

                 t.addEventListener("click", (function (i) {
                i.preventDefault(), e && e.validate().then((function (e) {
                    if ('Valid' === e) {
                        t.setAttribute('data-kt-indicator', 'on');
                        t.disabled = !0;
                        setTimeout(function () {
                            let courseID = document.getElementById('courseID').value;
                            let sectionID = document.getElementById('sectionID').value;
                            let name = document.querySelector('[name=name]').value;
                            let description = document.querySelector('[name=description]').value;
                            let showDescription = document.getElementById('showDes').checked;
                            let startDate = toDate(document.querySelector('[name=start_date]').value);
                            let endDate = toDate(document.querySelector('[name=end_date]').value);
                            let typeName = document.getElementById('typeName').value;
                            let textSubmission = document.getElementsByName('typeSubmit[]')[0].checked;
                            let fileSubmission = document.getElementsByName('typeSubmit[]')[1].checked;
                            let maximumGrade = document.getElementsByName('maxGrade')[0].value;
                            var objDT = {
                                "courseModules": {
                                    "name": name,
                                    "description": description,
                                    "typeName": typeName,
                                    "showDescription": showDescription,
                                    "visible": true,
                                    "courseSections": {
                                        "courseSectionId": sectionID
                                    }
                                },
                                "assignment": {
                                    "file": file,
                                    "startDate": startDate,
                                    "dueDate": endDate,
                                    "fileSubmission": fileSubmission,
                                    "textSubmission": textSubmission,
                                    "maximumGrade": maximumGrade
                                }
                            };
                            if(!document.getElementById('allowstartdate').checked){
                                delete objDT.assignment.startDate;
                            }
                            if(!document.getElementById('allowenddate').checked){
                                delete  objDT.assignment.dueDate;
                            }
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
    KTCareersApply.init()
}));

