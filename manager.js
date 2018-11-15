const user = localStorage.getItem("username")
const userid = localStorage.getItem("userId")
let lastId;
let cells;
let nRow;
let itemCount = 0;
let allData;
let userData = {

};
let userN = {
    username: user
}
fetch('http://villianpub-env.f2uxinfeen.us-east-2.elasticbeanstalk.com/ERS/users/profile', {
    method: "POST", // *GET, POST, PUT, DELETE, etc.
    credentials: "include", // include, *same-origin, omit
    body: JSON.stringify(userN), // data can be `string` or {object}!
    headers: {
        'Content-Type': 'application/json'
    }
}).then(res => res.json())
    .then(response => {
        userData = response;
        document.getElementById('fnameLab').innerHTML = userData.firstName + "  ";
        document.getElementById("lnameLab").innerHTML = userData.lastName;
        console.log(userData);
    })
    .catch(error => {
        console.log(error)
    });



function profile() {
    $('#profileModal').modal('show');
    document.getElementById('firstName').innerHTML = userData.firstName
    document.getElementById('lastName').innerHTML = userData.lastName
    document.getElementById('email').innerHTML = userData.email

    if (userData.roleId === 1) {
        document.getElementById('role').innerHTML = "Employee"
    }
    else if (userData.roleId === 2) {
        document.getElementById('role').innerHTML = "Manager"
    }


}


function logout(event) {
    event.preventDefault();
    fetch('http://villianpub-env.f2uxinfeen.us-east-2.elasticbeanstalk.com/ERS/users/logout', {
        method: "POST", // *GET, POST, PUT, DELETE, etc.
        credentials: "include", // include, *same-origin, omit
        // body: JSON.stringify(data), // data can be `string` or {object}!
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => {
        window.location.replace("index.html");
    })
        .catch(error => {
            console.log(error)
        });

}

function getDateTime() {
    now = new Date();
    year = "" + now.getFullYear();
    month = "" + (now.getMonth() + 1); if (month.length == 1) { month = "0" + month; }
    day = "" + now.getDate(); if (day.length == 1) { day = "0" + day; }
    hour = "" + now.getHours(); if (hour.length == 1) { hour = "0" + hour; }
    minute = "" + now.getMinutes(); if (minute.length == 1) { minute = "0" + minute; }
    second = "" + now.getSeconds(); if (second.length == 1) { second = "0" + second; }
    return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
}

function appendToTable(row, item) {
    row.style.cursor = 'pointer';

    let cell1 = row.insertCell(0);
    cell1.innerText = item.id

    let cell2 = row.insertCell(1);
    cell2.innerText = item.amount

    let cell3 = row.insertCell(2);
    cell3.innerText = item.submittedTime

    let cell4 = row.insertCell(3);
    cell4.innerText = item.resolved

    let cell5 = row.insertCell(4);
    cell5.innerText = item.description

    let cell6 = row.insertCell(5);
    cell6.innerText = item.receipt

    let cell7 = row.insertCell(6);
    cell7.innerText = item.authorName

    let cell8 = row.insertCell(7);
    cell8.innerText = item.resolverName

    let cell9 = row.insertCell(8);
    cell9.innerText = item.reimbStatus

    let cell10 = row.insertCell(9);
    cell10.innerText = item.reimbType

    switch (item.reimbStatus) {
        case 'Pending':
            row.style.backgroundColor = '#f4cb42';
            break;
        case 'Approved':
            row.style.backgroundColor = "#baefa2";
            break;
        case 'Denied':
            row.style.backgroundColor = '#ff6666';
        default:
            return;
    }
}

fetch('http://villianpub-env.f2uxinfeen.us-east-2.elasticbeanstalk.com/ERS/reimbursements/getMan', {
    method: "GET", // *GET, POST, PUT, DELETE, etc.
    credentials: "include", // include, *same-origin, omit
    // body: JSON.stringify(data), // data can be `string` or {object}!
    headers: {
        'Content-Type': 'application/json'
    }
}).then(res => res.json())
    .then(response => {
        console.log(response)
        let tab = document.getElementById("dataTable")
        allData = response;
        response.forEach(async (item) => {
            let row = tab.insertRow(1);

            appendToTable(row, item);
            if (itemCount === 0) {
                lastId = item.id;
                itemCount = itemCount + 1;
            }

        })
        $('#dataTable tr').click(function () {
            cells = $(this).find("td")
            // console.log(href)
            $('#rowModal').modal('show');

            updateId = cells[0].innerText;
            document.getElementById('editAmount').value = cells[1].innerText;
            document.getElementById('editDescrip').value = cells[4].innerText;
            document.getElementById('editReceipt').value = cells[5].innerText;
            document.getElementById('editStatus').innerHTML = cells[8].innerText;
            document.getElementById('editDropSelect').value = cells[9].innerText;



            switch (cells[8].innerText) {
                case 'Pending':
                    document.getElementById('editAmount').disabled = false;
                    document.getElementById('editDescrip').disabled = false;
                    document.getElementById('editReceipt').disabled = false;
                    document.getElementById('editDropSelect').disabled = false;
                    document.getElementById('editSubBtn').disabled = false;
                    document.getElementById("rowWarning").innerHTML = ""
                    break;
                default:
                    document.getElementById('editAmount').disabled = true;
                    document.getElementById('editDescrip').disabled = true;
                    document.getElementById('editReceipt').disabled = true;
                    document.getElementById('editDropSelect').disabled = true;
                    document.getElementById('editSubBtn').disabled = true;
                    document.getElementById("rowWarning").innerHTML = "Cannot edit an already resolved reimbursement"
                    break;
            }
        });
    })

    .catch(error => {
        document.getElementById('status').style.color = 'red';
        document.getElementById('status').innerText = "Incorrect username or password";
    });

function updateReq() {
    let amountInput = document.getElementById("editAmount").value;
    let desc = document.getElementById("editDescrip").value;
    let receiptInput = document.getElementById("editReceipt").value;
    let warn = document.getElementById("warning");
    let sel = document.getElementById('editDropSelect');
    let approval = document.getElementById('editApprovalSelect');
    console.log(cells);
    let newStatus;
    cells[3].innerHTML = getDateTime();
    switch (approval.value) {
        case 'Approve':
            cells[8].innerHTML = "Approved"
            cells[0].style.backgroundColor = "#baefa2";
            cells[1].style.backgroundColor = "#baefa2";
            cells[2].style.backgroundColor = "#baefa2";
            cells[3].style.backgroundColor = "#baefa2";
            cells[4].style.backgroundColor = "#baefa2";
            cells[5].style.backgroundColor = "#baefa2";
            cells[6].style.backgroundColor = "#baefa2";
            cells[7].style.backgroundColor = "#baefa2";
            cells[8].style.backgroundColor = "#baefa2";
            cells[9].style.backgroundColor = "#baefa2";
            newStatus = 1;
            break;
        case 'Deny':
            cells[8].innerHTML = "Denied"
            cells[0].style.backgroundColor = "#ff6666";
            cells[1].style.backgroundColor = "#ff6666";
            cells[2].style.backgroundColor = "#ff6666";
            cells[3].style.backgroundColor = "#ff6666";
            cells[4].style.backgroundColor = "#ff6666";
            cells[5].style.backgroundColor = "#ff6666";
            cells[6].style.backgroundColor = "#ff6666";
            cells[7].style.backgroundColor = "#ff6666";
            cells[8].style.backgroundColor = "#ff6666";
            cells[9].style.backgroundColor = "#ff6666";
            newStatus = 3;
            break;
        default:
            return;
    }
    console.log(sel);

    if (isNaN(amountInput)) {
        warn.innerHTML = " Please enter numbers only in Amount";
        return;
    }

    if (amount === "" || desc === "" || receiptInput === "") {
        warn.innerHTML = "Please fill in all the boxes"
        return;
    }

    let data = {
        id: updateId,
        amount: amountInput,
        submittedTime: getDateTime(),
        resolved: getDateTime(),
        description: desc,
        receipt: receiptInput,
        authorName: "",
        resolverName: "",
        reimbStatus: "",
        reimbType: "",
        author: userid,
        resolver: 1,
        reimbStatusId: newStatus,
        reimbTypeId: sel.selectedIndex + 1
    };
    console.log(JSON.stringify(data))
    fetch('http://villianpub-env.f2uxinfeen.us-east-2.elasticbeanstalk.com/ERS/reimbursement/update', {
        method: "POST", // *GET, POST, PUT, DELETE, etc.
        credentials: "include", // include, *same-origin, omit
        body: JSON.stringify(data), // data can be `string` or {object}!
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.json())
        .then(response => {
            console.log(response)
        })

        .catch(error => {
            console.log(error)
        });

    $('#rowModal').modal('hide');
}

function submitReq() {
    let amountInput = document.getElementById("amount").value;
    let desc = document.getElementById("descrip").value;
    let receiptInput = document.getElementById("reciept").value;
    let warn = document.getElementById("warning");
    let sel = document.getElementById('dropSelect');
    console.log(sel);

    if (isNaN(amountInput)) {
        warn.innerHTML = " Please enter numbers only in Amount";
        return;
    }

    if (amount === "" || desc === "" || receiptInput === "") {
        warn.innerHTML = "Please fill in all the boxes"
        return;
    }

    let data = {
        id: lastId + 1,
        amount: amountInput,
        submittedTime: getDateTime(),
        resolved: null,
        description: desc,
        receipt: receiptInput,
        authorName: user,
        resolverName: "",
        reimbStatus: "Pending",
        reimbType: sel.value,
        author: userid,
        resolver: 1,
        reimbStatusId: 2,
        reimbTypeId: sel.selectedIndex + 1
    };
    lastId = lastId + 1;

    let tab = document.getElementById("dataTable")
    let row = tab.insertRow(1);

    appendToTable(row, data);




    fetch('http://villianpub-env.f2uxinfeen.us-east-2.elasticbeanstalk.com/ERS/reimbursement/save', {
        method: "POST", // *GET, POST, PUT, DELETE, etc.
        credentials: "include", // include, *same-origin, omit
        body: JSON.stringify(data), // data can be `string` or {object}!
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.json())
        .then(response => {
            console.log(response)
        })

        .catch(error => {
            console.log(error)
        });

    $('#reimbModal').modal('hide');
}

$('#reimbModal').on('hidden.bs.modal', function (e) {
    $('#reimb-form').find('input, textarea').val("");
});

function filter(){
    let tbl = document.getElementById('dataTable');
    var rowCount = tbl.rows.length;
        for (var i = rowCount - 1; i > 0; i--) {
            tbl.deleteRow(i);
        }
    allData.sort(dynamicSort("reimbStatus"))
    allData.forEach(async (item) => {
        let row = tbl.insertRow(1);

        appendToTable(row, item);
        if (itemCount === 0) {
            lastId = item.id;
            itemCount = itemCount + 1;
        }

    })
    $('#dataTable tr').click(function () {
        cells = $(this).find("td")
        // console.log(href)
        $('#rowModal').modal('show');

        updateId = cells[0].innerText;
        document.getElementById('editAmount').value = cells[1].innerText;
        document.getElementById('editDescrip').value = cells[4].innerText;
        document.getElementById('editReceipt').value = cells[5].innerText;
        document.getElementById('editStatus').innerHTML = cells[8].innerText;
        document.getElementById('editDropSelect').value = cells[9].innerText;



        switch (cells[8].innerText) {
            case 'Pending':
                document.getElementById('editAmount').disabled = false;
                document.getElementById('editDescrip').disabled = false;
                document.getElementById('editReceipt').disabled = false;
                document.getElementById('editDropSelect').disabled = false;
                document.getElementById('editSubBtn').disabled = false;
                document.getElementById("rowWarning").innerHTML = ""
                break;
            default:
                document.getElementById('editAmount').disabled = true;
                document.getElementById('editDescrip').disabled = true;
                document.getElementById('editReceipt').disabled = true;
                document.getElementById('editDropSelect').disabled = true;
                document.getElementById('editSubBtn').disabled = true;
                document.getElementById("rowWarning").innerHTML = "Cannot edit an already resolved reimbursement"
                break;
        }
    });
    
}

function dynamicSort(property) {
    var sortOrder = 1;
    if (property[0] === "-") {
        sortOrder = -1;
        property = property.substr(1);
    }
    return function (a, b) {
        var result = (a[property] < b[property]) ? -1 : (a[property] > b[property]) ? 1 : 0;
        return result * sortOrder;
    }
}
