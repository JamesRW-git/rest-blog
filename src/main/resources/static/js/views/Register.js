import createView from "../createView.js";

export default function Register() {
    //language=HTML
    return `<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Register</title>
</head>
<body>
<h1>Register</h1>

<form id="register-form">
    <label for="username">Username</label>
    <input id="username" name="username" type="text"/>
    <label for="password">Password</label>
    <input id="password" name="password" type="password"/>
    <label for="email">Email</label>
    <input id="email" name="email" type="email"/>
    <input id="register-btn" type="button" value="Register"/>
</form>
</body>
</html>`;

}

export function RegisterEvent(){
    createSubmitRegisterUserListener();
}

function createSubmitRegisterUserListener(){
    $(document).on('click', '#register-btn', function(e) {
        e.preventDefault();
        const newUser = {
            username: $("#username").val(),
            email: $("#email").val(),
            password: $("#password").val()
        }

        console.log(newUser);

        const options = {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(newUser)
        };

        fetch("http://localhost:8080/api/users/create", options)
            .then((response) => {
                console.log(response.status)
                createView("/register");
            });
    })

}