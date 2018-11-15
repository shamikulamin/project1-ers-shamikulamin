function loginReq(event){
    event.preventDefault();
    var data = {username: document.getElementById('login').value,
                password: document.getElementById('password').value
            };

    console.log(data);

    fetch('http://VillianPub-env.f2uxinfeen.us-east-2.elasticbeanstalk.com/ERS/users/login', {
        method: "POST", // *GET, POST, PUT, DELETE, etc.
        credentials: "include", // include, *same-origin, omit
        body: JSON.stringify(data), // data can be `string` or {object}!
        headers:{
            'Content-Type': 'application/json'
        }
        }).then(res => res.json()) 
        .then(response => {
            if(response.roleId===1){
                window.location.replace("empDash.html"); 
                localStorage.setItem("username", response.username);
                localStorage.setItem("userId",response.id);
            }
            if(response.roleId===2){
                window.location.replace("manDash.html"); 
                localStorage.setItem("username", response.username);
                localStorage.setItem("userId",response.id);
            }
        })
            
        .catch(error => {
            document.getElementById('status').style.color = 'red';
            document.getElementById('status').innerText = "Incorrect username or password";
        });
}