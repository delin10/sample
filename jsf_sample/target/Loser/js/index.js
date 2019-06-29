function validate(id) {
    var f = document.forms[id];
    var prx = /^[0-9a-zA-Z]{6,8}$/;
    var urx = /^[0-9a-zA-Z]{1,10}$/;
    if (!urx.test(f['name'].value)) {
        alert("用户名不合法");
        return false;
    }

    if (!prx.test(f['password'].value)) {
        alert("密码不合法");
        return false;
    }

    return true;
}