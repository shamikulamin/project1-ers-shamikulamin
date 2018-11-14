const user = localStorage.getItem("username")
const userid = localStorage.getItem("userId")
console.log(userid);



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


fetch('http://localhost:8089/ERS/reimbursements/'+user, {
        method: "GET", // *GET, POST, PUT, DELETE, etc.
        credentials: "include", // include, *same-origin, omit
       // body: JSON.stringify(data), // data can be `string` or {object}!
        headers:{
            'Content-Type': 'application/json'
        }
        }).then(res => res.json()) 
        .then(response => {
            console.log(response)
            let tab = document.getElementById("dataTable")
            response.forEach(async(item)=>{
                let row = tab.insertRow(1);
            
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
            })
        })
            
        .catch(error => {
            document.getElementById('status').style.color = 'red';
            document.getElementById('status').innerText = "Incorrect username or password";
        });

function submitReq(){
    let amountInput = document.getElementById("amount").value;
    let desc = document.getElementById("descrip").value;
    let receiptInput = document.getElementById("reciept").value;
    let warn = document.getElementById("warning");
    

    if(amount === "" || desc === "" || receiptInput ===""){
        warn.innerHTML = "Please fill in all the boxes"
        return;
    }

    let data = {
        id: 0,
        amount: amountInput,
        submittedTime: getDateTime(),
        resolved: null,
        description: desc,
        receipt: receiptInput,
        authorName: "",
        resolverName: "",
        reimbStatus: "",
        reimbType: "",
        author: userid,
        resolver: 1,
        reimbStatusId: 2,
        reimbTypeId: 1
    };

    fetch('http://localhost:8089/ERS/reimbursement/save', {
        method: "POST", // *GET, POST, PUT, DELETE, etc.
        credentials: "include", // include, *same-origin, omit
        body: JSON.stringify(data), // data can be `string` or {object}!
        headers:{
            'Content-Type': 'application/json'
        }
        }).then(res => res.json()) 
        .then(response => {
            console.log(response)
        })
            
        .catch(error => {
           console.log(error)
        });
}

$('#exampleModal').on('hidden.bs.modal', function(e)
    { 
        $('#reimb-form').find('input, textarea').val("");
    }) ;