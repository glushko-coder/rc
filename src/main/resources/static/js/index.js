let fileds = {
    a: null,
    b: null,
    c: null,
    d: null,
    y: null
};

let fieldsData = {
    a: [],
    b: [],
    c: [],
    d: [],
    y: []
};

let data = [];

$(document).ready(function() {
    fieldsData.a = getA();
    fieldsData.c = getC();
    fieldsData.y = getY();

    updateData();
    drawData();

    $("#col_a").on('change', function () {
        fileds.a = $(this).val();
        fieldsData.b = getB(fileds.a);
        updateData();
        drawData();
    });

    $("#col_b").on('change', function () {
        fileds.b = $(this).val();
        updateData();
        drawData();
    });

    $("#col_c").on('change', function () {
        fileds.c = $(this).val();
        fieldsData.d = getD(fileds.c);
        updateData();
        drawData();
    });

    $("#col_d").on('change', function () {
        fileds.d = $(this).val();
        updateData();
        drawData();
    });

    $("#col_y").on('change', function () {
        fileds.y = $(this).val();
        updateData();
        drawData();
    });
});

function drawData() {
    const tbody  = $('#tbody');
    const aFiled = $('#col_a');
    const bFiled = $('#col_b');
    const cFiled = $('#col_c');
    const dFiled = $('#col_d');
    const yFiled = $('#col_y');
    tbody.empty();
    aFiled.empty();
    bFiled.empty();
    cFiled.empty();
    dFiled.empty();
    yFiled.empty();
    data.forEach(el => {
        tbody.append(
            `<tr>
                <td>${el[0]}</td>
                <td>${el[1]}</td>
                <td>${el[2]}</td>
                <td>${el[3]}</td>
                <td>${el[4]}</td>
                <td>${el[5]}</td>
            </tr>`)
    });
    aFiled.append(`<option value="${null}">НЕ ВЫБРАНО</option>`);
    fieldsData.a.forEach(el => {
        aFiled.append(
            `<option value="${el}">${el}</option>`
        )
    });
    bFiled.append(`<option value="${null}">НЕ ВЫБРАНО</option>`);
    fieldsData.b.forEach(el => {
        bFiled.append(
            `<option value="${el}">${el}</option>`
        )
    });
    cFiled.append(`<option value="${null}">НЕ ВЫБРАНО</option>`);
    fieldsData.c.forEach(el => {
        cFiled.append(
            `<option value="${el}">${el}</option>`
        )
    });
    dFiled.append(`<option value="${null}">НЕ ВЫБРАНО</option>`);
    fieldsData.d.forEach(el => {
        dFiled.append(
            `<option value="${el}">${el}</option>`
        )
    });
    yFiled.append(`<option value="${null}">НЕ ВЫБРАНО</option>`);
    fieldsData.y.forEach(el => {
        yFiled.append(
            `<option value="${el}">${el}</option>`
        )
    });
    $(`#col_a option[value="${fileds.a}"]`).prop('selected', true);
    $(`#col_b option[value="${fileds.b}"]`).prop('selected', true);
    $(`#col_c option[value="${fileds.c}"]`).prop('selected', true);
    $(`#col_d option[value="${fileds.d}"]`).prop('selected', true);
    $(`#col_y option[value="${fileds.y}"]`).prop('selected', true);
}

function getA() {
    let result = [];
    $.ajax({
        url: "/api/a",
        async: false,
        success: data => result = data
    });
    return result;
}

function getB(valueA) {
    let result = [];
    $.ajax({
        url: "/api/b/" + valueA,
        async: false,
        success: data => result = data
    });
    return result;
}

function getC() {
    let result = [];
    $.ajax({
        url: "/api/c/",
        async: false,
        success: data => result = data
    });
    return result;
}

function getD(valueC) {
    let result = [];
    $.ajax({
        url: "/api/d/" + valueC,
        async: false,
        success: data => result = data
    });
    return result;
}

function getY() {
    let result = [];
    $.ajax({
        url: "/api/y",
        async: false,
        success: data => result = data
    });
    return result;
}

function updateData() {
    data = get(fileds)
}

function get(params) {
    let result = [];
    $.ajax({
        url: "http://localhost:7070/api/taxes",
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(params),
        contentType: "application/json",
        async: false,
        success: data => result = data,
        error: e => console.log(e)
    });
    return result;
}
